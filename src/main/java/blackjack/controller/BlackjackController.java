package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.cardPicker.CardPicker;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckMaker;
import blackjack.domain.participant.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardPicker cardPicker;

    public BlackjackController(final InputView inputView, final OutputView outputView, final CardPicker cardPicker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardPicker = cardPicker;
    }

    public void run() {
        final Participants participants = makeParticipants();
        final Deck deck = makeDeck();
        final BlackjackGame blackjackGame = new BlackjackGame(participants, deck);

        startGame(blackjackGame);
        showAllParticipantCards(participants);

        hitParticipants(blackjackGame);

        showAllCardsAndScore(blackjackGame);

        showAllResult(blackjackGame);



//        outputView.outputResult();
//        int win = 0;
//        int tie = 0;
//        int lose = 0;
//        int dealerScore = scoreMap.get(dealer);
//        for (Player player : players.getPlayers()) {
//            int playerScore = scoreMap.get(player);
//            if (playerScore > 21) {
//                if (dealerScore > 21) tie++;
//                if (dealerScore <= 21) win++;
//                continue;
//            }
//            if (dealerScore <= 21 && dealerScore > playerScore) win++;
//            if (dealerScore <= 21 && dealerScore == playerScore) tie++;
//            if (dealerScore <= 21 && dealerScore < playerScore) lose++;
//        }
//
//        outputView.outputDealerResult(dealer.getName(), win, tie, lose);
//
//
//        for (Player player : players.getPlayers()) {
//            if (scoreMap.get(dealer) > scoreMap.get(player)) {
//                outputView.outputPlayerResult(player.getName(), "승");
//            }
//            if (scoreMap.get(dealer) == scoreMap.get(player)) {
//                outputView.outputPlayerResult(player.getName(), "무");
//            }
//            if (scoreMap.get(dealer) < scoreMap.get(player)) {
//                outputView.outputPlayerResult(player.getName(), "패");
//            }
//        }
    }

    private Participants makeParticipants() {
        Dealer dealer = new Dealer();
        String playerNames = inputView.inputPlayers();
        return new Participants(dealer, playerNames);
    }

    private Deck makeDeck() {
        DeckMaker deckMaker = new DeckMaker();
        return new Deck(deckMaker.makeDeck(), cardPicker);
    }

    private void startGame(BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();
        Dealer dealer = participants.getDealer();
        List<String> playerNames = participants.getPlayerNames();

        outputView.outputSplitMessage(dealer.getName(), playerNames);
        blackjackGame.giveTwoCardEveryone();
    }

    private void showAllParticipantCards(Participants participants) {
        Dealer dealer = participants.getDealer();
        outputView.outputPlayerCard(dealer.getName(), dealer.getOneCard());

        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
        }
    }

    private void hitParticipants(BlackjackGame blackjackGame) {
        hitPlayers(blackjackGame);
        hitDealer(blackjackGame);
    }

    private void hitPlayers(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getParticipants().getPlayers();

        for (Player player : players) {
            hitEachPlayer(blackjackGame, player);
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
        }
    }

    private void hitEachPlayer(BlackjackGame blackjackGame, Player player) {
        String wantOrder = inputView.inputOrderCard(player.getName());

        while(!player.isBust() && wantOrder.equals("n")) {
            blackjackGame.drawCard(player);
            wantOrder = inputView.inputOrderCard(player.getName());
        }
    }

    private void hitDealer(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getParticipants().getDealer();

        while(dealer.canHit()) {
            outputView.outputDealerDrawCard(dealer.getName());
            blackjackGame.drawCard(dealer);
        }
    }

    private void showAllCardsAndScore(BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();

        showDealerCardsAndScore(participants);
        showPlayersCardsAndScore(participants);
    }

    private void showDealerCardsAndScore(Participants participants) {
        Dealer dealer = participants.getDealer();
        outputView.outputPlayerCard(dealer.getName(), dealer.getCardNames());
        outputView.outputScore(dealer.getTotalScore());
    }

    private void showPlayersCardsAndScore(Participants participants) {
        List<Player> players = participants.getPlayers();

        for(Player player: players) {
            outputView.outputPlayerCard(player.getName(), player.getCardNames());
            outputView.outputScore(player.getTotalScore());
        }
    }

    private void showAllResult(BlackjackGame blackjackGame) {
        outputView.outputResult();

    }
}
