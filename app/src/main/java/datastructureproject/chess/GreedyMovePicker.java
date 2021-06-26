package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNode;

import java.util.PriorityQueue;

public class GreedyMovePicker implements ChessMovePicker {
    @Override
    public Move pickNextMove(GameNode state) {
        if (state == null)
            return null;

        PriorityQueue<GameNode> states = state.getNextStates();
        if (states == null)
            return null;
        else if (states.isEmpty())
            return null;

        return state.getNextStates().peek().getLastMove();
    }
}
