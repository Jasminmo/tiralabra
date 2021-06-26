package datastructureproject.ai.algorithms;

/**
 * This contains code which is the core of the alpha-beta-algorithm.
 */
public class MinMax implements GameNodeEvaluator {
    private final int MAX_DEPTH;

    public MinMax(int maxDepth) {
        MAX_DEPTH = maxDepth;
    }

    @Override
    public int evaluate(GameNode state) {
        if (state.isMaxState()) {
            return getMaxValue(state, MAX_DEPTH);
        }
        return getMinValue(state, MAX_DEPTH);
    }

    private static int getMinValue(GameNode state, int maxDepth) {
        if (state.isFinalState() || maxDepth < 1) {
            return state.value();
        }
        int value = Integer.MAX_VALUE;
        for (GameNode adjacent : state.getNextStates()) {
            int result = getMaxValue(adjacent, maxDepth - 1);
            value = Math.min(value, result);
        }
        return value;
    }

    private static int getMaxValue(GameNode state, int maxDepth) {
        if (state.isFinalState() || maxDepth < 1) {
            return state.value();
        }
        int value = Integer.MIN_VALUE;
        for (GameNode adjacent : state.getNextStates()) {
            int result = getMinValue(adjacent, maxDepth - 1);
            value = Math.max(value, result);
        }
        return value;
    }

}
