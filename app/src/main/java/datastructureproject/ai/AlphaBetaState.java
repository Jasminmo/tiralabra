package datastructureproject.ai;

import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;

import java.util.List;

public interface AlphaBetaState {

    List<AlphaBetaState> nextStates();

    boolean isMaxState();

    boolean isFinalState();

    int value();

}
