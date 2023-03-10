package blackjack.domain.betting;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.Map;

public class ResultBetting {

    private final Map<Participant, Money> playersResult;

    public ResultBetting(final Map<Participant, Money> playersResult) {
        this.playersResult = playersResult;
    }

    public void betMoney(final Participant participant, final Money money) {
        playersResult.put(participant, money);
    }

    public Money getDealerMoney(final Participants participants) {
        final Money allPlayerMoney = getSumAllPlayerMoney(participants);

        return new Money(-allPlayerMoney.getValue());
    }

    private Money getSumAllPlayerMoney(final Participants participants) {
        return new Money(
                participants.getPlayers().stream()
                        .map(this::getPlayerMoney)
                        .map(Money::getValue)
                        .reduce(0, Integer::sum)
        );
    }

    private Money getPlayerMoney(final Participant player) {
        return playersResult.get(player);
    }

    public void updateMoney(Participant participant, WinTieLose winTieLose) {
        Money money = playersResult.get(participant);

        System.out.println(money.updateProfit(winTieLose.getBonus()));

        playersResult.put(participant, money.updateProfit(winTieLose.getBonus()));
    }
}
