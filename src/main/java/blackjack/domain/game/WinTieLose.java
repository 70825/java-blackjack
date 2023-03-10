package blackjack.domain.game;

public enum WinTieLose {

    WIN("승", 1),
    TIE("무", 0),
    LOSE("패", -1);

    private final String value;
    private final int bonus;

    WinTieLose(final String value, final int bonus) {
        this.value = value;
        this.bonus = bonus;
    }

    public String getValue() {
        return value;
    }

    public int getBonus() {
        return bonus;
    }

    public WinTieLose reverse() {
        if (equals(WIN)) {
            return LOSE;
        }
        if (equals(TIE)) {
            return TIE;
        }
        return WIN;
    }
}
