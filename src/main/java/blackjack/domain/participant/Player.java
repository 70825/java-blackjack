package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.vo.Name;

import java.util.List;
import java.util.stream.Collectors;

public class Player extends Participant {

    public Player(final Name name, final Cards cards) {
        super(name, cards);
    }
}
