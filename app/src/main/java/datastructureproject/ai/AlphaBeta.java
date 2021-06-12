package datastructureproject.ai;

/**
 * This contains code which is the core of the alpha-beta-algorithm.
 */
public class AlphaBeta {

    public static int evaluate(AlphaBetaState state, int maxDepth) {
        if (state.isMaxState()) {
            return getMaxValue(state, -1000, 1000, maxDepth);
        }
        return getMinValue(state, -1000, 1000, maxDepth);
    }

    private static int getMinValue(AlphaBetaState state, int alpha, int beta, int maxDepth) {
        if (state.isFinalState() || maxDepth == 0) {
            return state.value();
        }

        int value = Integer.MAX_VALUE;
        for (AlphaBetaState adjacent : state.nextStates()) {
            value = Math.min(value, getMaxValue(adjacent, alpha, beta, maxDepth - 1));
            if (value <= alpha)
                return value;

            beta = Math.min(beta, value);
        }
        return value;
    }

    private static int getMaxValue(AlphaBetaState state, int alpha, int beta, int maxDepth) {
        if (state.isFinalState() || maxDepth == 0) {
            return state.value();
        }
        int value = Integer.MIN_VALUE;
        for (AlphaBetaState adjacent : state.nextStates()) {
            value = Math.max(value, getMinValue(adjacent, alpha, beta, maxDepth - 1));
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }

}
