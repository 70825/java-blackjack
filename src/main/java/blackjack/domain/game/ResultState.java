package blackjack.domain.game;

import java.util.function.Function;

public enum ResultState {

    BLACKJACK(betting -> (int) (betting * 1.5)),
    WIN(betting -> betting * 1),
    TIE(betting -> betting * 0),
    LOSE(betting -> betting * -1);

    private final Function<Integer, Integer> moneyFunction;

    ResultState(final Function<Integer, Integer> moneyFunction) {
        this.moneyFunction = moneyFunction;
    }

    public static ResultState resultPlayer(final Score playerScore, final Score dealerScore) {
        if (playerScore.isBlackjack() && !dealerScore.isBlackjack()) {
            return BLACKJACK;
        }
        if (playerScore.canHit() && (dealerScore.isBust() || playerScore.isGreaterThan(dealerScore))) {
            return WIN;
        }
        if (bothBust(playerScore, dealerScore) || playerScore.isEqualTo(dealerScore)) {
            return TIE;
        }
        return LOSE;
    }

    private static boolean bothBust(final Score scoreFirst, final Score scoreSecond) {
        return scoreFirst.isBust() && scoreSecond.isBust();
    }

    public int calculateProfit(int betting) {
        return moneyFunction.apply(betting);
    }
}
