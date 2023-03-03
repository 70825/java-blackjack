package blackjack.domain.participant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final String playerNames) {
        this.dealer = dealer;
        this.players = makePlayers(playerNames);
    }

    private List<Player> makePlayers(final String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(name -> new Player(new Name(name)))
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
