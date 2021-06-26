package datastructureproject.ai.algorithms;

import com.github.bhlangonijr.chesslib.move.Move;

import java.util.PriorityQueue;

public interface GameNode {

    PriorityQueue<GameNode> getNextStates();

    boolean isMaxState();

    boolean isFinalState();

    int value();

    Move getLastMove();
}
