package blackjack.domain.card;

import blackjack.domain.CardPicker;

import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final CardPicker cardPicker;

    public Deck(final List<Card> cards, final CardPicker cardPicker) {
        this.cards = cards;
        this.cardPicker = cardPicker;
    }

    public Card drawCard() {
        final int index = cardPicker.pickIndex(cards.size());
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    public List<Card> getCards() {
        return cards;
    }
}
