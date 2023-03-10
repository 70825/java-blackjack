package blackjack.domain.betting;

public class Money {

    private static final double BLACKJACK_BONUS = 1.5;

    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money updateProfit(int resultBonus) {
        return new Money(value * resultBonus);
    }

    public Money blackjack() {
        return new Money((int) (value * BLACKJACK_BONUS));
    }

    public int getValue() {
        return value;
    }
}
