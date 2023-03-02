package blackjack.domain.model;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.vo.Letter;
import blackjack.domain.vo.Name;
import blackjack.domain.vo.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 생성 테스트")
    void constructorPlayer(){
        assertThatNoException().isThrownBy(()->new Player(new Name("test"),new Cards()));
    }

    @Test
    @DisplayName("카드 한장을 가져오는지 테스트")
    void drawCardTest() {
        // given
        final Player player = new Player(new Name("test"), new Cards());
        final Card card = new Card(Shape.CLOVER, Letter.ACE);

        // when
        player.drawCard(card);

        // then
        Assertions.assertThat(player.getCardNames())
                .contains(card.getCardName());
    }

    @Test
    @DisplayName("카드의 총 합을 보여주는 테스트")
    void calculateTotal(){
        // given
        final Player player = new Player(new Name("test"), new Cards());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        final int expected = card1.getValue()+card2.getValue();
        player.drawCard(card1);
        player.drawCard(card2);

        // when
        final int actual = player.getTotalScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어 이름 반환하는 테스트")
    void getNameTest(){
        final Player player = new Player(new Name("test"),new Cards());
        assertThat(player.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("플레이어의 카드 이름 리스트를 반환하는 테스트")
    void getCardsTest(){
        // given
        final Player player = new Player(new Name("test"), new Cards());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        player.drawCard(card1);
        player.drawCard(card2);

        Assertions.assertThat(player.getCardNames()).contains(card1.getCardName(),card2.getCardName());
    }

    @Test
    @DisplayName("카드 한장을 가져오는 테스트")
    void getOneCardTest(){
        final Dealer dealer = new Dealer(new Name("test"), new Cards());
        final Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        final Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        dealer.drawCard(card1);
        dealer.drawCard(card2);

        final List<String> expected = dealer.getOneCard();
        Assertions.assertThat(expected.size()).isEqualTo(1);
        Assertions.assertThat(expected).contains(card1.getCardName());
    }
}
