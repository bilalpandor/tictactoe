package org.bilalpandor.tictactoe.engine;

import org.bilalpandor.tictactoe.error.BoardException;
import org.bilalpandor.tictactoe.error.PlayerException;
import org.bilalpandor.tictactoe.model.BoardSquare;
import org.bilalpandor.tictactoe.model.Player;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TicTacToeTest {


    //region Create Game
    @Test
    public void canCreateGame() {
        TicTacToe ticTacToe = new TicTacToe();
        assertNotNull(ticTacToe);
    }
    //endregion

    //region Creating Board
    @Test
    public void canCreateBoard() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        assertNotNull(ticTacToe.getBoard());
    }

    @ParameterizedTest
    @MethodSource("customBoardSizeArgs")
    void canCreateBoardOfCustomSize(List<Integer> boardSizeList) {
        TicTacToe ticTacToe = buildGameWithSpecifiedBoard(boardSizeList);
        assertNotNull(ticTacToe.getBoard());
    }

    static Stream<List<Integer>> customBoardSizeArgs() {
        return Stream.of(
                List.of(3, 3),
                List.of(3, 4),
                List.of(3, 5),
                List.of(4, 3),
                List.of(4, 4),
                List.of(4, 5),
                List.of(5, 3),
                List.of(5, 4),
                List.of(5, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectCustomBoardInputArgs")
    void cannotCreateBoardWithLessThanOrMoreThanTwoInputs(List<Integer> boardInputsList) {
        Exception exception = assertThrows(BoardException.class, () -> buildGameWithSpecifiedBoard(boardInputsList));
        assertThat(exception.getMessage(), is("Board input must be of size 2"));
    }

    static Stream<List<Integer>> incorrectCustomBoardInputArgs() {
        return Stream.of(
                List.of(3),
                List.of(3, 3, 3)
        );
    }
    //endregion

    //region Initialising Players
    @Test
    public void canInitialiseOnePlayer() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        ticTacToe.initialisePlayer(player1);

        assertThat(ticTacToe.getPlayers().size(), is(1));
        assertThat(ticTacToe.getPlayers().get(0).getName(), is(name));
    }

    @Test
    public void canInitialiseTwoPlayers() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        String playerName1 = "Bilal Pandor";
        String playerName2 = "Peter Parker";
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        ticTacToe.initialisePlayer(player1);
        ticTacToe.initialisePlayer(player2);

        assertThat(ticTacToe.getPlayers().size(), is(2));
    }

    @Test
    public void cannotInitialiseMoreThanTwoPlayers() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        String playerName1 = "Bilal Pandor";
        String playerName2 = "Peter Parker";
        String playerName3 = "J. R. Tolkien";
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        Player player3 = new Player(playerName3);

        ticTacToe.initialisePlayer(player1);
        ticTacToe.initialisePlayer(player2);
        assertThat(ticTacToe.getPlayers().size(), is(2));

        Exception exception = assertThrows(PlayerException.class, () -> ticTacToe.initialisePlayer(player3));
        assertThat(exception.getMessage(), is("Cannot initialise more than 2 players"));
    }
    //endregion

    //region Perform Player Move
    @Test
    public void canPerformPlayerMove() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        ticTacToe.initialisePlayer(player1);
        BoardSquare playerMove = new BoardSquare(1, 1);

        Player player = ticTacToe.performPlayerMove(player1, playerMove);

        assertThat(player, is(player1));
        assertThat(ticTacToe.getBoard().getSquarePlayerMap().get(playerMove), is(player1));
    }

    @Test
    public void willNotAllowUnidentifiedPlayerToMakeMove() {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        Player player1 = new Player("Bilal Pandor");
        ticTacToe.initialisePlayer(player1);
        Player player2 = new Player("Non Player");
        BoardSquare playerMove = new BoardSquare(1, 1);

        ticTacToe.performPlayerMove(player1, playerMove);

        Exception exception = assertThrows(PlayerException.class, () -> ticTacToe.performPlayerMove(player2, playerMove));
        assertThat(exception.getMessage(), is("Non Player not recognised as a valid player"));
    }
    //endregion

    //region Calculate Winning Criteria
    @ParameterizedTest
    @MethodSource("verticalBoardSquareArgs")
    void canCalculateIfPlayerHasWonVertically(BoardSquare player1Move1, BoardSquare player1Move2, BoardSquare player1Move3) {
        TicTacToe ticTacToe = buildGameWithDefaultBoard();
        String playerName1 = "Bilal Pandor";
        Player player1 = new Player(playerName1);
        ticTacToe.initialisePlayer(player1);

        ticTacToe.performPlayerMove(player1, player1Move1);
        ticTacToe.performPlayerMove(player1, player1Move2);
        ticTacToe.performPlayerMove(player1, player1Move3);

        assertThat(ticTacToe.checkIfPlayerHasWonVertically(player1), is(true));
    }

    static Stream<Arguments> verticalBoardSquareArgs() {
        return Stream.of(
                Arguments.of(
                        new BoardSquare(1, 1),
                        new BoardSquare(1, 2),
                        new BoardSquare(1, 3)
                ),
                Arguments.of(
                        new BoardSquare(2, 1),
                        new BoardSquare(2, 2),
                        new BoardSquare(2, 3)
                ),
                Arguments.of(
                        new BoardSquare(3, 1),
                        new BoardSquare(3, 2),
                        new BoardSquare(3, 3)
                )
        );
    }
    //endregion

    private TicTacToe buildGameWithDefaultBoard() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.createBoard();
        return ticTacToe;
    }

    private TicTacToe buildGameWithSpecifiedBoard(List<Integer> boardInputs) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.createBoard(boardInputs);
        return ticTacToe;
    }

}
