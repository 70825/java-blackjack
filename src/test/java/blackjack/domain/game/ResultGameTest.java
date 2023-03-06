package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ResultGameTest {
    private Dealer dealer;
    private Participants participants;
    private Map<Player, WinTieLose> playersResult;


    @BeforeEach
    void setting() {
        dealer = new Dealer();
        participants = new Participants(dealer, List.of("pobi", "crong", "dali"));
        playersResult = new HashMap<>();
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> new ResultGame(participants, playersResult));
    }

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class PlayerBust {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi"));
        Map<Player, WinTieLose> playersResult = new HashMap<>();
        ResultGame resultGame = new ResultGame(participants, playersResult);
        Player player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(new Card(Shape.HEART, Letter.TEN));
            player.drawCard(new Card(Shape.HEART, Letter.EIGHT));
            player.drawCard(new Card(Shape.HEART, Letter.SEVEN));
        }

        @Test
        @DisplayName("딜러가 버스트일 때 TIE가 나온다.")
        void dealerBust() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.SEVEN));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 버스트가 아닐 때 LOSE가 나온다.")
        void dealerNotBust() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.THREE));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class PlayerNormal {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi"));
        Map<Player, WinTieLose> playersResult = new HashMap<>();
        ResultGame resultGame = new ResultGame(participants, playersResult);
        Player player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(new Card(Shape.HEART, Letter.TEN));
            player.drawCard(new Card(Shape.HEART, Letter.EIGHT));
            player.drawCard(new Card(Shape.HEART, Letter.THREE));
        }

        @Test
        @DisplayName("딜러가 플레이어보다 점수가 높을 때 WIN이 나온다.")
        void dealerGreaterThanPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.SEVEN));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }

        @Test
        @DisplayName("딜러가 플레이어와 점수가 같을 때 TIE가 나온다.")
        void dealerEqualsPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.THREE));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아닐 때 WIN이 나온다.")
        void dealerLessThanPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TWO));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭이 아닐 때")
    class PlayerBlackjack {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi"));
        Map<Player, WinTieLose> playersResult = new HashMap<>();
        ResultGame resultGame = new ResultGame(participants, playersResult);
        Player player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(new Card(Shape.HEART, Letter.TEN));
            player.drawCard(new Card(Shape.HEART, Letter.EIGHT));
            player.drawCard(new Card(Shape.HEART, Letter.TWO));
        }

        @Test
        @DisplayName("딜러가 버스트일 때 WIN이 나온다.")
        void dealerBust() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.SEVEN));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }

        @Test
        @DisplayName("딜러가 버스트가 아니고, 플레이어보다 점수가 높을 때 LOSE가 나온다.")
        void dealerNotBustAndGreaterThanPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.THREE));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
        }

        @Test
        @DisplayName("딜러가 플레이어와 점수가 같을 때 TIE이 나온다.")
        void dealerEqualPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.EIGHT));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TWO));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 플레이어보다 점수가 낮을 때 WIN이 나온다.")
        void dealerLessThanPlayer() {
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.SEVEN));
            dealer.drawCard(new Card(Shape.DIAMOND, Letter.TWO));

            resultGame.calculateResult();
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }
    }

    @Test
    @DisplayName("딜러의 승무패가 제대로 나오는지 테스트")
    void getDealerCountTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.WIN)).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 결과를 출력하는 테스트")
    void getPlayerResultTest() {
        // given
        ResultGame resultGame = new ResultGame(participants, playersResult);
        dealer.drawCard(new Card(Shape.CLOVER, Letter.EIGHT));
        Player player = participants.getPlayers().get(0);
        player.drawCard(new Card(Shape.CLOVER, Letter.NINE));

        // when
        resultGame.calculateResult();

        // then
        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }
}
