package blackjack.domain.card;

import blackjack.domain.cardPicker.CardPicker;

import java.util.List;

public class Deck {
    private static final String EMPTY_CARD_MESSAGE = "덱에 카드가 존재하지 않습니다.";

    private final List<Card> cards;
    private final CardPicker cardPicker;

    public Deck(final List<Card> cards, final CardPicker cardPicker) {
        this.cards = cards;
        this.cardPicker = cardPicker;
    }

    public Card drawCard() {
        if (isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARD_MESSAGE);
        }

        final int index = cardPicker.pickIndex(cards.size());
        return cards.remove(index);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return cards;
    }
}
