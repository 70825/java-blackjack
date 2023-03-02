package blackjack.domain;

import blackjack.domain.cardPicker.CardPicker;
import blackjack.domain.cardPicker.RandomCardPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardPickerTest {

    @Test
    @DisplayName("범위에 맞는 랜덤한 정수를 가져온다.")
    void pickIndexTest() {
        // given
        final CardPicker cardPicker = new RandomCardPicker();
        final int size = 10;

        // when
        final int actual = cardPicker.pickIndex(size);

        // then
        Assertions.assertThat(actual)
                .isGreaterThanOrEqualTo(0)
                .isLessThan(size);
    }
}
