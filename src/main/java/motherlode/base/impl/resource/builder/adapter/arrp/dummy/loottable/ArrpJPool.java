package motherlode.base.impl.resource.builder.adapter.arrp.dummy.loottable;

public class ArrpJPool {
    /**
     * @see ArrpJLootTable#pool()
     */
    public ArrpJPool() {
    }

    public ArrpJPool entry(ArrpJEntry entry) {
        return this;
    }

    public ArrpJPool condition(ArrpJCondition condition) {
        return this;
    }

    public ArrpJPool function(ArrpJFunction function) {
        return this;
    }

    public ArrpJPool rolls(Integer rolls) {
        return this;
    }

    public ArrpJPool rolls(ArrpJRoll roll) {
        return this;
    }

    public ArrpJPool bonus(Integer bonusRolls) {
        return this;
    }

    public ArrpJPool bonus(ArrpJRoll bonusRoll) {
        return this;
    }
}
