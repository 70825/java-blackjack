package blackjack.controller;

import blackjack.domain.CardPicker;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Participants;
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

        Dealer dealer = new Dealer(new Name("딜러"), new Cards());
        Participants participants = new Participants(playerNames);
        DeckMaker deckMaker = new DeckMaker();
        Deck deck = new Deck(deckMaker.makeDeck(), cardPicker);

        //TODO: 메서드 분리
        outputView.outputSplitMessage(dealer.getName(), participants.getNames());
        giveTwoCardToPlayer(dealer, deck);
        for (Player player : participants.getPlayers()) {
            giveTwoCardToPlayer(player, deck);
        }

        //TODO: 메서드 분리
        outputView.outputPlayerCard(dealer.getName(), dealer.getOneCard());
        for (Player player : participants.getPlayers()) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
        }

        //TODO: 메서드 분리
        for (Player player : participants.getPlayers()) {
            while (inputView.inputOrderCard(player.getName()).equals(Order.NO)) {
                player.drawCard(deck.drawCard());

                if (player.getTotalScore() > 21) {
                    break;
                }
            }
        }

        while (dealer.getTotalScore() <= 16) {
            outputView.outputDealerDrawCard(dealer.getName());
            dealer.drawCard(deck.drawCard());
        }

        HashMap<Participant, Integer> scoreMap = new HashMap<>();
        outputView.outputPlayerCard(dealer.getName(), dealer.getCardNames());
        scoreMap.put(dealer, dealer.getTotalScore());
        outputView.outputScore(scoreMap.get(dealer));
        for (Player player : participants.getPlayers()) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
            scoreMap.put(player, player.getTotalScore());
            outputView.outputScore(scoreMap.get(player));
        }

        outputView.outputResult();
        int win = 0;
        int tie = 0;
        int lose = 0;
        int dealerScore = scoreMap.get(dealer);
        for (Player player : participants.getPlayers()) {
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


        for (Player player : participants.getPlayers()) {
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

    private void giveTwoCardToPlayer(Participant player, Deck deck) {
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
