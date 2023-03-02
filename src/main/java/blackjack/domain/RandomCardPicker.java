package blackjack.domain;

import java.util.Random;

public class RandomCardPicker implements CardPicker {
    public static final Random RANDOM = new Random();

    @Override
    public int pickIndex(final int size) {
        return RANDOM.nextInt(size);
    }
}
