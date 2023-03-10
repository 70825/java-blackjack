package blackjack.domain.game;

import java.util.Objects;

public final class Score {

    private static final int BLACKJACK = 21;

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        return new Score(value);
    }

    public Score minus(final int value) {
        return new Score(this.value - value);
    }

    public boolean isHit() {
        return value <= BLACKJACK;
    }

    public boolean isHit(final int hitScore) {
        return value <= hitScore;
    }

    public boolean isBust() {
        return value > BLACKJACK;
    }

    public boolean isGreaterThan(final Score other) {
        return value > other.value;
    }

    public boolean isEqualTo(final Score other) {
        return value == other.value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
