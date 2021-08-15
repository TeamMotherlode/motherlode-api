package motherlode.base.api.resource.builder.adapter.arrp.dummy.loottable;

import net.minecraft.util.Identifier;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ArrpJFunction {
    public JsonObject properties = new JsonObject();

    /**
     * @see ArrpJLootTable#function(String)
     */
    public ArrpJFunction(String function) {
        this.function(function);
    }

    public ArrpJFunction function(String function) {
        return this;
    }

    public ArrpJFunction set(JsonObject properties) {
        return this;
    }

    public ArrpJFunction parameter(String key, JsonElement value) {
        return this;
    }

    public ArrpJFunction parameter(String key, String value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJFunction parameter(String key, Number value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJFunction parameter(String key, Boolean value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJFunction parameter(String key, Identifier value) {
        return parameter(key, value.toString());
    }

    public ArrpJFunction parameter(String key, Character value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJFunction condition(ArrpJCondition condition) {
        return this;
    }
}
