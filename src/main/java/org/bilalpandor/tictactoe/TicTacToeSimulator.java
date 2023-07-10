package org.bilalpandor.tictactoe;

import org.bilalpandor.tictactoe.engine.TicTacToe;
import org.bilalpandor.tictactoe.model.BoardSquare;
import org.bilalpandor.tictactoe.model.Player;
import org.bilalpandor.tictactoe.model.TicTacToeInput;

import java.util.List;
import java.util.stream.Collectors;


public class TicTacToeSimulator {

    public static final int MINIMUM_WINNING_THRESHOLD = 3;

    public String simulateGame(TicTacToeInput ticTacToeInput) {
        Player winningPlayer = null;

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.createBoard(List.of(ticTacToeInput.getBoardXAxis(), ticTacToeInput.getBoardYAxis()));

        Player player1 = new Player(ticTacToeInput.getPlayerOneName());
        ticTacToe.initialisePlayer(player1);

        Player player2 = new Player(ticTacToeInput.getPlayerTwoName());
        ticTacToe.initialisePlayer(player2);

        List<BoardSquare> player1Moves = ticTacToeInput.getPlayerOneMoves().stream()
                .map(playerMove -> {
                    String[] axes = playerMove.split(":");
                    return new BoardSquare(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));
                }).collect(Collectors.toList());

        List<BoardSquare> player2Moves = ticTacToeInput.getPlayerTwoMoves().stream()
                .map(playerMove -> {
                    String[] axes = playerMove.split(":");
                    return new BoardSquare(Integer.parseInt(axes[0]), Integer.parseInt(axes[1]));
                }).collect(Collectors.toList());

        for (int i = 0; i < player1Moves.size(); i++) {
            ticTacToe.performPlayerMove(player1, player1Moves.get(i));
            ticTacToe.performPlayerMove(player2, player2Moves.get(i));

            if (i <= MINIMUM_WINNING_THRESHOLD && ticTacToe.checkIfPlayerHasWonVertically(player1)) {
                winningPlayer = player1;
            }
            
            if (i <= MINIMUM_WINNING_THRESHOLD && winningPlayer == null && ticTacToe.checkIfPlayerHasWonVertically(player2)) {
                winningPlayer = player2;
            }

            if (winningPlayer != null){
                return String.format("%s wins!", winningPlayer.getName());
            }
        }

        return "Draw";
    }
}
