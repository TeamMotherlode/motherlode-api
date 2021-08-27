package motherlode.base.api.resource.builder;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import org.apache.commons.io.input.ReaderInputStream;

public interface Resource<T> {
    T get();

    String asString();

    default InputStream toInputStream() {
        return new ReaderInputStream(new StringReader(this.asString()), Charset.defaultCharset());
    }
}
