package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.vo.Name;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final Name name;
    private final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public int calculateTotal() {
        return cards.calculateTotalScore();
    }

    public List<String> getOneCard() {
        return List.of(cards.getFirstCard().getCardName());
    }

    public List<String> getCardNames() {
        return cards.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name.get();
    }
}
