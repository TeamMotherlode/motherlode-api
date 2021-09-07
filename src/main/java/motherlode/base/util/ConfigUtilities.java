package motherlode.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.function.Function;
import motherlode.base.impl.LogFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import org.apache.logging.log4j.Level;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class ConfigUtilities {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private ConfigUtilities() {
    }

    public static <T> T loadConfig(Codec<T> codec, Path configPath, T alternative, LogFunction logger) {
        return loadConfig(codec, configPath, Optional.of(alternative), logger).orElse(alternative);
    }

    public static <T> Optional<T> loadConfig(Codec<T> codec, Path configPath, LogFunction logger) {
        return loadConfig(codec, configPath, Optional.empty(), logger);
    }

    private static <T> Optional<T> loadConfig(Codec<T> codec, Path configPath, Optional<T> alternative, LogFunction logger) {
        Optional<T> config = Optional.empty();

        if (Files.exists(configPath) && Files.isRegularFile(configPath)) {
            try (InputStream input = Files.newInputStream(configPath)) {
                logger.log(Level.INFO, "Reading config.");

                config = readConfig(codec, input, alternative, logger);
            } catch (IOException e) {
                logger.log(Level.ERROR, "IO exception while trying to read config: " + e.getLocalizedMessage());

                config = alternative;
            }
        } else {
            alternative.ifPresent(input -> {
                try (OutputStream output = Files.newOutputStream(configPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
                     OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(output))) {
                    logger.log(Level.INFO, "Writing default config.");

                    writeConfig(codec, input, writer, logger);
                } catch (IOException e) {
                    logger.log(Level.ERROR, "IO exception while trying to write default config: " + e.getLocalizedMessage());
                }
            });
        }

        return config;
    }

    public static <T> Optional<T> readConfig(Codec<T> codec, InputStream input, Optional<T> alternative, LogFunction logger) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(new BufferedInputStream(input))) {
            JsonElement element = new JsonParser().parse(reader);

            Either<T, DataResult.PartialResult<T>> result = codec.parse(JsonOps.INSTANCE, element).get();

            try {
                return Optional.of(result.map(Function.identity(), partialResult -> {
                    throw new RuntimeException("Error decoding config: " + partialResult.message());
                }));
            } catch (RuntimeException e) {
                logger.log(Level.ERROR, e.getLocalizedMessage());

                return alternative;
            }
        }
    }

    public static <T> void writeConfig(Codec<T> codec, T input, Writer writer, LogFunction logger) throws IOException {
        DataResult<JsonElement> result = codec.encodeStart(JsonOps.INSTANCE, input);
        JsonElement element;

        try {
            element = result.get().map(Function.identity(), partialResult -> {
                throw new RuntimeException("Error encoding config: " + partialResult.message());
            });
        } catch (RuntimeException e) {
            logger.log(Level.ERROR, e.getLocalizedMessage());

            return;
        }

        String json = GSON.toJson(element);

        writer.append(json);
    }
}
