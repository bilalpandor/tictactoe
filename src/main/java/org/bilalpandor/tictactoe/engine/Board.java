package org.bilalpandor.tictactoe.engine;

import lombok.SneakyThrows;
import org.bilalpandor.tictactoe.error.BoardException;
import org.bilalpandor.tictactoe.model.BoardSquare;
import org.bilalpandor.tictactoe.model.Player;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Board {

    public static final Player NULL_PLAYER = new Player("NULL");
    private HashMap<BoardSquare, Player> squarePlayerMap;
    private Integer xAxis;
    private Integer yAxis;

    @SneakyThrows
    public void intialiseBoard(int xAxis, int yAxis) {
        validateIntendedBoardSize(xAxis, yAxis);
        calculateInitialSquarePlayerMap(xAxis, yAxis);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    private void calculateInitialSquarePlayerMap(int xAxis, int yAxis) {
        squarePlayerMap = new HashMap<>(xAxis * yAxis);
        IntStream.range(1, xAxis + 1).forEach(i ->
                IntStream.range(1, yAxis + 1).forEach(y ->
                        squarePlayerMap.put(new BoardSquare(i, y), null)
                )
        );
    }

    @SneakyThrows
    private static void validateIntendedBoardSize(Integer xAxis, Integer yAxis) {
        if (xAxis > 5 || yAxis > 5) throw new BoardException("Maximum board size of 5x5 exceeded");
        if (xAxis < 3 || yAxis < 3) throw new BoardException("Minimum board size of 3x3 not met");
    }

    public String getSize() {
        return String.format("%sx%s", xAxis, yAxis);
    }

    public Map<BoardSquare, Player> getSquarePlayerMap() {
        return squarePlayerMap;
    }

    @SneakyThrows
    public Player assignPlayerToSquare(Player player, BoardSquare boardSquare) {
        if (squarePlayerMap.get(boardSquare) != null)
            throw new BoardException("Board square already taken, please choose another square");
        if (!squarePlayerMap.containsKey(boardSquare))
            throw new BoardException("Board square out of bounds");

        squarePlayerMap.put(boardSquare, player);

        return player;
    }

    @SneakyThrows
    public boolean isSquareAssignedToPlayer(Player player, BoardSquare boardSquare) {
        if (!squarePlayerMap.containsKey(boardSquare)) {
            throw new BoardException("Board square out of bounds");
        }

        return player.getName().equals(getPlayerNameFromSquare(boardSquare).getName());
    }

    public List<BoardSquare> retrieveSquaresAssignedToPlayerOnBottomRow(Player player) {
        List<BoardSquare> bottomRowSquaresAssignedToPlayer = new ArrayList<>();

        AtomicInteger xAxisCounter = new AtomicInteger(1);
        while(xAxisCounter.get() <= xAxis) {
            BoardSquare bottomRowSquare = new BoardSquare(xAxisCounter.getAndIncrement(),1);
            if (isSquareAssignedToPlayer(player, bottomRowSquare)) {
                bottomRowSquaresAssignedToPlayer.add(bottomRowSquare);
            }
        }

        return bottomRowSquaresAssignedToPlayer;
    }

    private Player getPlayerNameFromSquare(BoardSquare boardSquare) {
        return Optional.ofNullable(squarePlayerMap.get(boardSquare))
                .orElse(NULL_PLAYER);
    }
}
