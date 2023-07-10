package org.bilalpandor.tictactoe.model;

import lombok.Value;

import java.util.List;

@Value
public class TicTacToeInput {
    Integer boardXAxis;
    Integer boardYAxis;
    String playerOneName;
    String playerTwoName;
    List<String> playerOneMoves;
    List<String> playerTwoMoves;

}
