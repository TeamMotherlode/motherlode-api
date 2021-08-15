package motherlode.base.api.resource.builder.adapter.arrp.dummy.loottable;

public class ArrpJEntry {
    /**
     * @see ArrpJLootTable#entry()
     */
    public ArrpJEntry() {
    }

    public ArrpJEntry type(String type) {
        return this;
    }

    public ArrpJEntry name(String name) {
        return this;
    }

    public ArrpJEntry child(ArrpJEntry child) {
        if (this == child) {
            throw new IllegalArgumentException("Can't add entry as its own child!");
        }
        return this;
    }

    public ArrpJEntry expand(Boolean expand) {
        return this;
    }

    public ArrpJEntry function(ArrpJFunction function) {
        return this;
    }

    public ArrpJEntry function(String function) {
        return function(ArrpJLootTable.function(function));
    }

    public ArrpJEntry condition(ArrpJCondition condition) {
        return this;
    }

    public ArrpJEntry condition(String condition) {
        return condition(ArrpJLootTable.predicate(condition));
    }

    public ArrpJEntry weight(Integer weight) {
        return this;
    }

    public ArrpJEntry quality(Integer quality) {
        return this;
    }
}
