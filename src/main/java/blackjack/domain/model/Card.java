package blackjack.domain.model;

import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Shape;

public class Card {
    final private Shape shape;
    final private Letter letter;

    public Card(final Shape shape, final Letter letter) {
        this.shape = shape;
        this.letter = letter;
    }

    public String getCardName() {
        return letter.name() + shape.name();
    }

    public int getValue() {
        return this.letter.getValue();
    }
}