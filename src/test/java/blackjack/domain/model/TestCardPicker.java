package blackjack.domain.model;

import blackjack.domain.CardPicker;

public class TestCardPicker implements CardPicker {

    @Override
    public int pickIndex(final int size){
        return 0;
    }
}
