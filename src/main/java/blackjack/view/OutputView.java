package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultGame;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String COMMA = ", ";
    private static final String COLON = ": ";
    private static final String BLANK = " ";
    private static final String GIVE_TWO_CARD_MESSAGE = "에게 두 장을 나누었습니다.";
    private static final String NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String RESULT_DELIMITER = " - 결과 : ";
    private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_MESSAGE = "승";
    private static final String TIE_MESSAGE = "무";
    private static final String LOSE_MESSAGE = "패";
    private static final String ERROR_PREFIX_MESSAGE = "[ERROR] ";

    public void printInitialHandOutMessage(final Participants participants) {
        System.out.println(getParticipantsList(participants) + GIVE_TWO_CARD_MESSAGE);
        participants.getAll().forEach(this::printParticipantNameAndCards);
    }

    private String getParticipantsList(final Participants participants) {
        return String.join(COMMA, participants.getNames());
    }

    public void printParticipantNameAndCards(final Participant participant) {
        System.out.println(getNameAndCards(participant));
    }

    private String getNameAndCards(final Participant participant) {
        List<String> cardNames = participant.getHand()
                .stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());

        return participant.getName() + COLON + String.join(COMMA, cardNames);
    }

    public void printAllCardsAndScore(final Participants participants) {
        participants.getAll().forEach(this::printCardsAndScore);
    }

    private void printCardsAndScore(final Participant participant) {
        System.out.println(getNameAndCards(participant) + RESULT_DELIMITER + getScore(participant));
    }

    private String getScore(final Participant participant) {
        return String.valueOf(participant.getScore());
    }

    public void printDealerDrawCard() {
        System.out.println(NOTICE_TOTAL_SCORE_UNDER_SIXTEEN_MESSAGE);
    }

    public void printParticipantsResult(final Participants participants, final ResultGame resultGame) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        System.out.println(NEW_LINE + NEW_LINE + FINAL_RESULT_MESSAGE);
        printDealerResult(dealer, resultGame);
        for (final Participant player : players) {
            printPlayerResult(player, resultGame);
        }
    }

    private void printDealerResult(final Participant dealer, final ResultGame resultGame) {
        final String dealerName = dealer.getName();
        final int win = resultGame.getDealerCount(WinTieLose.WIN);
        final int tie = resultGame.getDealerCount(WinTieLose.TIE);
        final int lose = resultGame.getDealerCount(WinTieLose.LOSE);

        System.out.println(dealerName + COLON
                + win + WIN_MESSAGE + BLANK
                + tie + TIE_MESSAGE + BLANK
                + lose + LOSE_MESSAGE);
    }

    private void printPlayerResult(final Participant player, final ResultGame resultGame) {
        final String playerName = player.getName();
        final String result = resultGame.getPlayerResult(player).getValue();

        System.out.println(playerName + COLON + result);
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX_MESSAGE + e.getMessage());
    }
}
