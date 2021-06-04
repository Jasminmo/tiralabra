package datastructureproject.ai;

import java.util.List;

public interface AlphaBetaState {

    List<AlphaBetaState> nextStates();

    boolean isMaxState();

    boolean isFinalState();

    int value();

}
