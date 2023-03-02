package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.vo.Name;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public List<String> getOneCard() {
        return List.of(super.getCards().get(0).getCardName());
    }
}
