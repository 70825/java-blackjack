package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.cardPicker.CardPicker;
import blackjack.domain.cardPicker.RandomCardPicker;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Scanner;

public class BlackjackApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final CardPicker cardPicker = new RandomCardPicker();

        BlackjackController blackjackController = new BlackjackController(inputView, outputView, cardPicker);

    }
}
