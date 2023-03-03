package blackjack.domain.game;

import blackjack.domain.participant.Participant;

public enum WinDrawLose {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    WinDrawLose(final String value) {
        this.value = value;
    }

    public WinDrawLose calculateBothBust(Participant participantOrigin, Participant participantOther) {
        return DRAW;
    }

    public WinDrawLose calculateOnePlayerBust(Participant participantOrigin, Participant participantOther) {
        if (participantOrigin.isBust()) {
            return LOSE;
        }

        if (participantOther.isBust()) {
            return WIN;
        }

        throw new IllegalStateException();
    }

    public WinDrawLose calculateNeitherBust(Participant participantOrigin, Participant participantOther) {
        int originScore = participantOrigin.getTotalScore();
        int otherScore = participantOther.getTotalScore();

        if (originScore > otherScore) {
            return WIN;
        }
        if (originScore == otherScore) {
            return DRAW;
        }
        return LOSE;
    }
}
