package org.bilalpandor.tictactoe.engine;

import lombok.SneakyThrows;
import org.bilalpandor.tictactoe.error.BoardException;
import org.bilalpandor.tictactoe.model.BoardSquare;
import org.bilalpandor.tictactoe.model.Player;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {

    //region Create Board
    @Test
    public void canCreateBoard(){
        Board board = new Board();
        assertNotNull(board);
    }
    //endregion

    //region Board Initialisation
    @Test
    @SneakyThrows
    public void canCreateBoardOfMinimumSize() {
        Board board = new Board();
        board.intialiseBoard(3, 3);
        assertThat(board.getSize(), is("3x3"));
    }

    @Test
    @SneakyThrows
    public void canCreateBoardOfMediumSize() {
        Board board = new Board();
        board.intialiseBoard(4, 4);
        assertThat(board.getSize(), is("4x4"));
    }

    @Test
    @SneakyThrows
    public void canCreateBoardOfMaximumSize() {
        Board board = new Board();
        board.intialiseBoard(5, 5);
        assertThat(board.getSize(), is("5x5"));
    }

    @Test
    public void cannotCreateBoardWithSizeSmallerThan3x3() {
        Board board = new Board();
        Exception exception = assertThrows(BoardException.class, () -> board.intialiseBoard(2,2));
        assertThat(exception.getMessage(), is("Minimum board size of 3x3 not met"));
    }

    @Test
    public void cannotCreateBoardWithSizeGreaterThan5x5() {
        Board board = new Board();
        Exception exception = assertThrows(BoardException.class, () -> board.intialiseBoard(6,6));
        assertThat(exception.getMessage(), is("Maximum board size of 5x5 exceeded"));
    }
    //endregion

    //region Initialise Square Player Map
    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMap() {
        Board board = new Board();
        board.intialiseBoard(3, 3);
        assertNotNull(board.getSquarePlayerMap());
    }
    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMapOfCorrectSize() {
        Board board = new Board();
        board.intialiseBoard(3, 3);
        assertThat(board.getSquarePlayerMap().size(), is (9));
    }

    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMapWithNoAssignedPlayers() {
        Board board = new Board();
        board.intialiseBoard(3, 3);
        board.getSquarePlayerMap().forEach((key, value) -> assertNull(value));
    }

    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMapWithCorrectCoordinatesForSmallBoard() {
        Board board = new Board();
        board.intialiseBoard(3, 3);
        assertCoordinates(board, 1, 1);
        assertCoordinates(board, 1, 2);
        assertCoordinates(board, 1, 3);
        assertCoordinates(board, 2, 1);
        assertCoordinates(board, 2, 2);
        assertCoordinates(board, 2, 3);
        assertCoordinates(board, 3, 1);
        assertCoordinates(board, 3, 2);
        assertCoordinates(board, 3, 3);
    }

    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMapWithCorrectCoordinatesForMediumBoard() {
        Board board = new Board();
        board.intialiseBoard(4, 4);
        assertCoordinates(board, 1, 1);
        assertCoordinates(board, 1, 2);
        assertCoordinates(board, 1, 3);
        assertCoordinates(board, 1, 4);
        assertCoordinates(board, 2, 1);
        assertCoordinates(board, 2, 2);
        assertCoordinates(board, 2, 3);
        assertCoordinates(board, 2, 4);
        assertCoordinates(board, 3, 1);
        assertCoordinates(board, 3, 2);
        assertCoordinates(board, 3, 3);
        assertCoordinates(board, 3, 4);
        assertCoordinates(board, 4, 1);
        assertCoordinates(board, 4, 2);
        assertCoordinates(board, 4, 3);
        assertCoordinates(board, 4, 4);
    }

    @Test
    @SneakyThrows
    public void canInitialiseSquarePlayerMapWithCorrectCoordinatesForLargeBoard() {
        Board board = new Board();
        board.intialiseBoard(5, 5);
        assertCoordinates(board, 1, 1);
        assertCoordinates(board, 1, 2);
        assertCoordinates(board, 1, 3);
        assertCoordinates(board, 1, 4);
        assertCoordinates(board, 1, 5);
        assertCoordinates(board, 2, 1);
        assertCoordinates(board, 2, 2);
        assertCoordinates(board, 2, 3);
        assertCoordinates(board, 2, 4);
        assertCoordinates(board, 2, 5);
        assertCoordinates(board, 3, 1);
        assertCoordinates(board, 3, 2);
        assertCoordinates(board, 3, 3);
        assertCoordinates(board, 3, 4);
        assertCoordinates(board, 3, 5);
        assertCoordinates(board, 4, 1);
        assertCoordinates(board, 4, 2);
        assertCoordinates(board, 4, 3);
        assertCoordinates(board, 4, 4);
        assertCoordinates(board, 4, 5);
        assertCoordinates(board, 5, 1);
        assertCoordinates(board, 5, 2);
        assertCoordinates(board, 5, 3);
        assertCoordinates(board, 5, 4);
        assertCoordinates(board, 5, 5);
    }

    private static void assertCoordinates(Board board, int expectedXCoordinate, int expectedYCoordinate) {
        assertThat(board.getSquarePlayerMap().keySet().stream()
                        .anyMatch(coordinate -> coordinate.getXCoordinate() == expectedXCoordinate && coordinate.getYCoordinate() == expectedYCoordinate),
                is(true)
        );
    }
    //endregion

    //region Assign Board Square To Player
    @Test
    public void canAssignBoardSquareToPlayer(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 1);

        Player player = board.assignPlayerToSquare(player1, playerMoveCoordinates);

        assertThat(player, is(player));
        assertThat(board.getSquarePlayerMap().size(), is(9));
        assertThat(board.getSquarePlayerMap().get(playerMoveCoordinates), is(player1));
    }

    @Test
    public void cannotAssignBoardSquareToPlayerIfSquareIsTaken(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 1);

        board.assignPlayerToSquare(player1, playerMoveCoordinates);

        Exception exception = assertThrows(BoardException.class, () -> board.assignPlayerToSquare(player1, playerMoveCoordinates));
        assertThat(exception.getMessage(), is("Board square already taken, please choose another square"));
    }

    @Test
    public void cannotAssignBoardSquareToPlayerIfSquareIsOutOfBounds(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 4);

        Exception exception = assertThrows(BoardException.class, () -> board.assignPlayerToSquare(player1, playerMoveCoordinates));
        assertThat(exception.getMessage(), is("Board square out of bounds"));
    }

    //endregion

    //region Is Player Assigned To Board Square
    @Test
    public void canReturnTrueIfPlayerIsAssignedToSquare(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 1);
        board.assignPlayerToSquare(player1, playerMoveCoordinates);

        assertThat(board.isSquareAssignedToPlayer(player1, playerMoveCoordinates), is(true));
    }

    @Test
    public void canReturnFalseIfPlayerIsNotAssignedToSquare(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        Player player2 = new Player("New player");
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 3);
        board.assignPlayerToSquare(player1, playerMoveCoordinates);

        assertThat(board.isSquareAssignedToPlayer(player2, playerMoveCoordinates), is(false));
    }

    @Test
    public void throwExceptionWhenTryingToCheckBoardPlayerMatchOutOfBounds(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinates = new BoardSquare(1, 4);

        Exception exception = assertThrows(BoardException.class, () -> board.isSquareAssignedToPlayer(player1, playerMoveCoordinates));
        assertThat(exception.getMessage(), is("Board square out of bounds"));
    }

    //endregion

    //region Retrieve List of Squares
    @Test
    public void canRetrieveListOfSquaresAssignedToPlayerOnBottomRow(){
        Board board = new Board();
        board.intialiseBoard(3, 3);
        String name = "Bilal Pandor";
        Player player1 = new Player(name);
        BoardSquare playerMoveCoordinate1 = new BoardSquare(1, 1);
        board.assignPlayerToSquare(player1, playerMoveCoordinate1);
        BoardSquare playerMoveCoordinate2 = new BoardSquare(2, 1);
        board.assignPlayerToSquare(player1, playerMoveCoordinate2);
        BoardSquare playerMoveCoordinate3 = new BoardSquare(3, 1);
        board.assignPlayerToSquare(player1, playerMoveCoordinate3);

        assertThat(board.retrieveSquaresAssignedToPlayerOnBottomRow(player1),
                is(List.of(playerMoveCoordinate1, playerMoveCoordinate2, playerMoveCoordinate3))
        );
    }

    //endregion

}