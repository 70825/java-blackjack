package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isHit() {
        return getScore().isHit();
    }
}
