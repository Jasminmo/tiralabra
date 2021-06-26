package datastructureproject.ai.algorithms;

/**
 * This contains code which is the core of the alpha-beta-algorithm.
 */
public class AlphaBeta implements GameNodeEvaluator {
    private final int MAX_DEPTH;

    public AlphaBeta(int maxDepth) {
        MAX_DEPTH = maxDepth;
    }

    @Override
    public int evaluate(GameNode state) {
        if (state.isMaxState()) {
            return getMaxValue(state, -10000, 10000, MAX_DEPTH);
        }
        return getMinValue(state, -10000, 10000, MAX_DEPTH);
    }

    private int getMinValue(GameNode state, int alpha, int beta, int maxDepth) {
        if (state.isFinalState() || maxDepth < 1) {
            return state.value();
        }

        int value = Integer.MAX_VALUE;
        for (GameNode adjacent : state.getNextStates()) {
            int result = getMaxValue(adjacent, alpha, beta, maxDepth - 1);
            value = Math.min(value, result);
            beta = Math.min(beta, result);
            if (alpha >= beta) {
                return value;
            }
        }
        return value;
    }

    private int getMaxValue(GameNode state, int alpha, int beta, int maxDepth) {
        if (state.isFinalState() || maxDepth < 1) {
            return state.value();
        }
        int value = Integer.MIN_VALUE;
        for (GameNode adjacent : state.getNextStates()) {
            int result = getMinValue(adjacent, alpha, beta, maxDepth - 1);
            value = Math.max(value, result);
            alpha = Math.max(alpha, result);
            if (alpha >= beta) {
                return value;
            }
        }
        return value;
    }

}
