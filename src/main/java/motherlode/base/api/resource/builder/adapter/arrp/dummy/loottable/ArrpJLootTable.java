package motherlode.base.api.resource.builder.adapter.arrp.dummy.loottable;

public record ArrpJLootTable(String type) {
    /**
     * @see #loot(String)
     */
    public ArrpJLootTable {
    }

    public static ArrpJLootTable loot(String type) {
        return new ArrpJLootTable(type);
    }

    public static ArrpJEntry entry() {
        return new ArrpJEntry();
    }

    /**
     * @param condition the predicate's condition identifier
     */
    public static ArrpJCondition predicate(String condition) {
        return new ArrpJCondition(condition);
    }

    public static ArrpJFunction function(String function) {
        return new ArrpJFunction(function);
    }

    public static ArrpJPool pool() {
        return new ArrpJPool();
    }

    public static ArrpJRoll roll(int min, int max) {
        return new ArrpJRoll(min, max);
    }

    public ArrpJLootTable pool(ArrpJPool pool) {
        return this;
    }
}
