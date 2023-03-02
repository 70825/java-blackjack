package blackjack.domain.model;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

class PlayersTest {

    @Test
    @DisplayName("플레이어들 생성 테스트")
    void constructorPlayersTest() {
        assertThatNoException().isThrownBy(()->new Players("test"));
    }

    @Test
    @DisplayName("플레이어의 리스트를 반환하는 테스트")
    void getPlayersTest() {
        // given
        final String playerNames = "pobi,crong";
        final Players players = new Players(playerNames);

        // when
        final List<Player> actual = players.getPlayers();

        // then
        for (int i = 0; i < actual.size(); i++){
            Assertions.assertThat(players.getPlayers().get(i).getName()).isEqualTo(playerNames.split(",")[i]);
        }
    }

    @Test
    @DisplayName("플레이어의 이름들을 반환하는 테스트")
    void getNamesTest() {
        // given
        final String playerNames = "pobi,crong";
        final Players players = new Players(playerNames);
        final List<String> expected = Arrays.stream(playerNames.split(",")).collect(Collectors.toList());

        // when
        final List<String> actual = players.getNames();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
