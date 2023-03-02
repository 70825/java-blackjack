package blackjack.domain;

@FunctionalInterface
public interface CardPicker {
    int pickIndex(final int size);
}
