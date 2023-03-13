package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShapeTest {

    @ParameterizedTest
    @ValueSource(strings = {"다이아몬드", "하트", "스페이드", "클로버"})
    @DisplayName("모양이 가진 이름이 정확한지 테스트")
    void getValue(final String value) {
        Assertions.assertThat(Shape.from(value).getValue()).isEqualTo(value);
    }
}