package blackjack.controller;

import blackjack.domain.CardPicker;
import blackjack.domain.model.*;
import blackjack.domain.service.DeckMaker;
import blackjack.domain.vo.Name;
import blackjack.domain.vo.Order;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.function.Supplier;

public class BlackjackController {

    private static final String MAX_ATTEMPT_REACHED_ERROR_MESSAGE = "최대 시도 횟수에 도달했습니다. 프로그램을 종료합니다.";

    private final InputView inputView;
    private final OutputView outputView;
    private final CardPicker cardPicker;

    BlackjackController(final InputView inputView, final OutputView outputView, final CardPicker cardPicker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardPicker = cardPicker;
    }

    public void run() {
        final String playerNames = inputView.inputPlayers();

        Player dealer = new Player(new Name("딜러"), new Cards());
        Players players = new Players(playerNames);
        DeckMaker deckMaker = new DeckMaker();
        Deck deck = new Deck(deckMaker.makeDeck(), cardPicker);

        //TODO: 메서드 분리
        outputView.outputSplitMessage(dealer.getName(), players.getNames());
        giveTwoCardToPlayer(dealer, deck);
        for (Player player : players.getPlayers()) {
            giveTwoCardToPlayer(player, deck);
        }

        //TODO: 메서드 분리
        outputView.outputPlayerCard(dealer.getName(), dealer.getOneCard());
        for (Player player : players.getPlayers()) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
        }

        //TODO: 메서드 분리
        for (Player player : players.getPlayers()) {
            while (inputView.inputOrderCard(player.getName()).equals(Order.NO)) {
                player.drawCard(deck.drawCard());

                if (player.calculateTotal() > 21) {
                    break;
                }
            }
        }

        while (dealer.calculateTotal() <= 16) {
            outputView.outputDealerDrawCard(dealer.getName());
            dealer.drawCard(deck.drawCard());
        }

        HashMap<Player, Integer> scoreMap = new HashMap<>();
        outputView.outputPlayerCard(dealer.getName(), dealer.getCardNames());
        scoreMap.put(dealer, dealer.calculateTotal());
        outputView.outputScore(scoreMap.get(dealer));
        for (Player player : players.getPlayers()) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
            scoreMap.put(player, player.calculateTotal());
            outputView.outputScore(scoreMap.get(player));
        }

        outputView.outputResult();
        int win = 0;
        int tie = 0;
        int lose = 0;
        int dealerScore = scoreMap.get(dealer);
        for (Player player : players.getPlayers()) {
            int playerScore = scoreMap.get(player);
            if (playerScore > 21) {
                if (dealerScore > 21) tie++;
                if (dealerScore <= 21) win++;
                continue;
            }
            if (dealerScore <= 21 && dealerScore > playerScore) win++;
            if (dealerScore <= 21 && dealerScore == playerScore) tie++;
            if (dealerScore <= 21 && dealerScore < playerScore) lose++;
        }

        outputView.outputDealerResult(dealer.getName(), win, tie, lose);


        for (Player player : players.getPlayers()) {
            if (scoreMap.get(dealer) > scoreMap.get(player)) {
                outputView.outputPlayerResult(player.getName(), "승");
            }
            if (scoreMap.get(dealer) == scoreMap.get(player)) {
                outputView.outputPlayerResult(player.getName(), "무");
            }
            if (scoreMap.get(dealer) < scoreMap.get(player)) {
                outputView.outputPlayerResult(player.getName(), "패");
            }
        }
    }

    private void giveTwoCardToPlayer(Player player, Deck deck) {
        player.drawCard(deck.drawCard());
        player.drawCard(deck.drawCard());
    }

    private <T> T retry(final Supplier<T> supplier) {
        for (int count = 0; count < 5; count++) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }

        throw new IllegalArgumentException(MAX_ATTEMPT_REACHED_ERROR_MESSAGE);
    }
}
