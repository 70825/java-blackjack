package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {

    private final Name name;
    private final Cards cards;

    public Participant(final Name name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public List<String> getCardNames() {
        return cards.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }
}
