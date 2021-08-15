package motherlode.base.api.resource.builder.adapter.arrp.dummy.loottable;

import net.minecraft.util.Identifier;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ArrpJCondition {
    public JsonObject parameters = new JsonObject();

    /**
     * @see ArrpJLootTable#predicate(String)
     */
    public ArrpJCondition(String condition) {
        if (condition != null) {
            this.condition(condition);
        }
    }

    public ArrpJCondition() {
    }

    public ArrpJCondition condition(String condition) {
        return this;
    }

    public ArrpJCondition set(JsonObject parameters) {
        return this;
    }

    public ArrpJCondition parameter(String key, Number value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJCondition parameter(String key, JsonElement value) {
        return this;
    }

    public ArrpJCondition parameter(String key, Boolean value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJCondition parameter(String key, Character value) {
        return parameter(key, new JsonPrimitive(value));
    }

    public ArrpJCondition parameter(String key, Identifier value) {
        return parameter(key, value.toString());
    }

    public ArrpJCondition parameter(String key, String value) {
        return parameter(key, new JsonPrimitive(value));
    }

    /**
     * "or"'s the conditions together
     */
    public ArrpJCondition alternative(ArrpJCondition... conditions) {
        return this;
    }
}
