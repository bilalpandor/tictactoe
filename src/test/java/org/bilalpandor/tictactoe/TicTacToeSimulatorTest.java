package org.bilalpandor.tictactoe;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.bilalpandor.tictactoe.model.TicTacToeInput;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TicTacToeSimulatorTest {
    Gson gson = new Gson();

    @Test
    @SneakyThrows
    public void canSimulateGameOfTicTacToeWithBilalAsWinner() {
        TicTacToeSimulator ticTacToeSimulator = new TicTacToeSimulator();
        String input = parseJSONFile("src/test/java/resources/bilalPandorWinsVertically.json");
        TicTacToeInput ticTacToeInput = gson.fromJson(input, TicTacToeInput.class);
        assertThat(ticTacToeSimulator.simulateGame(ticTacToeInput), is("Bilal Pandor wins!"));
    }

    @Test
    public void canSimulateGameOfTicTacToeWithDeustcheAsWinner() {
        TicTacToeSimulator ticTacToeSimulator = new TicTacToeSimulator();
        String input = parseJSONFile("src/test/java/resources/deustcheWinsVertically.json");
        TicTacToeInput ticTacToeInput = gson.fromJson(input, TicTacToeInput.class);
        assertThat(ticTacToeSimulator.simulateGame(ticTacToeInput), is("Deustche Bank wins!"));
    }

    @Test
    public void canSimulateGameOfTicTacToeWithNoWinner() {
        TicTacToeSimulator ticTacToeSimulator = new TicTacToeSimulator();
        String input = parseJSONFile("src/test/java/resources/noWinner.json");
        TicTacToeInput ticTacToeInput = gson.fromJson(input, TicTacToeInput.class);
        assertThat(ticTacToeSimulator.simulateGame(ticTacToeInput), is("Draw"));
    }

    @SneakyThrows
    public static String parseJSONFile(String filename)  {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

}
