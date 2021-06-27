package datastructureproject.chess;

import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.ai.algorithms.AlphaBeta;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeSearchBotTest {
    private TreeSearchBot bot;
    private GameState gs;

    @Test
    public void testNextMoveWhenGameStateIsEmpty() {
        bot = new TreeSearchBot(new AlphaBeta(2));
        gs = new GameState();
        gs.playing = Side.WHITE;
        assertNotNull("message", bot.nextMove(gs));
    }

    @Test
    public void testNextMoveWhenGameStateIsNotEmpty() {
        bot = new TreeSearchBot(new GreedyMovePicker());
        gs = new GameState();
        gs.playing = Side.WHITE;
        gs.setMoves("a2a4,a7a5");
        assertNotNull("message", bot.nextMove(gs));
    }
}
