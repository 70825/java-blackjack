package blackjack.domain.model;

import blackjack.domain.CardPicker;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeckTest {
    @Test
    @DisplayName("덱을 생성한다.")
    void constructorDeckTest() {
        final List<Card> cards = List.of();
        final CardPicker cardPicker = new TestCardPicker();

        Assertions.assertThatNoException().isThrownBy(() -> new Deck(cards, cardPicker));
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void drawCardTest(){
        //given
        final List<Card> cards = new ArrayList<>(
                Arrays.asList(
                new Card(Shape.CLOVER, Letter.ACE),
                new Card(Shape.DIAMOND,Letter.JACK)));
        final CardPicker testCardPicker = new TestCardPicker();
        final Deck deck = new Deck(cards, testCardPicker);

        //when
        final Card card = deck.drawCard();

        //then
        assertThat(card.getValue()).isEqualTo(Letter.ACE.getValue());
    }

    @Test
    @DisplayName("덱 반환 테스트")
    void getCardsTest(){
        final List<Card> cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Shape.CLOVER, Letter.ACE),
                        new Card(Shape.DIAMOND,Letter.JACK)));
        final CardPicker testCardPicker = new TestCardPicker();
        final Deck deck = new Deck(cards, testCardPicker);

        assertThat(deck.getCards()).isEqualTo(cards);
    }
}
