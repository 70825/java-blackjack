package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateTotalScore() {
        return cards.stream().
                map(Card::getValue).
                reduce(0, Integer::sum);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
