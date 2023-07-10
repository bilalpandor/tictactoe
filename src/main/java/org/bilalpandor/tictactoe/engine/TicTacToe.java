package org.bilalpandor.tictactoe.engine;

import lombok.SneakyThrows;
import org.bilalpandor.tictactoe.error.BoardException;
import org.bilalpandor.tictactoe.error.PlayerException;
import org.bilalpandor.tictactoe.model.BoardSquare;
import org.bilalpandor.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    private Board board;
    private final List<Player> players = new ArrayList<>(2);

    public void createBoard() {
        this.board = new Board();
        this.board.intialiseBoard(3, 3);
    }

    @SneakyThrows
    public void createBoard(List<Integer> boardInputs) {
        if (boardInputs.size() != 2) throw new BoardException("Board input must be of size 2");
        Integer xAxis = boardInputs.get(0);
        Integer yAxis = boardInputs.get(1);
        this.board = new Board();
        this.board.intialiseBoard(xAxis, yAxis);
    }

    public Board getBoard() {
        return board;
    }

    @SneakyThrows
    public void initialisePlayer(Player player) {
        if (players.size() >= 2) throw new PlayerException("Cannot initialise more than 2 players");
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player performPlayerMove(Player player, BoardSquare boardSquare) {
        Player thisPlayer = findPlayerByName(player.getName());
        return board.assignPlayerToSquare(thisPlayer, boardSquare);
    }

    public boolean checkIfPlayerHasWonVertically(Player thisPlayer) {
        List<BoardSquare> bottomRowSquares = board.retrieveSquaresAssignedToPlayerOnBottomRow(thisPlayer);
        return bottomRowSquares.stream()
                .anyMatch(boardSquare ->
                        board.isSquareAssignedToPlayer(thisPlayer, getYAxisIncrementedBoardSquare(boardSquare, 1)) &&
                                board.isSquareAssignedToPlayer(thisPlayer, getYAxisIncrementedBoardSquare(boardSquare, 2)));
    }

    private static BoardSquare getYAxisIncrementedBoardSquare(BoardSquare boardSquare, Integer incrementAmount) {
        return new BoardSquare(boardSquare.getXCoordinate(), boardSquare.getYCoordinate() + incrementAmount);
    }

    @SneakyThrows
    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(thisPlayer -> thisPlayer.getName().equals(name)).
                findFirst()
                .orElseThrow(() -> new PlayerException(String.format("%s not recognised as a valid player", name)));
    }

}
