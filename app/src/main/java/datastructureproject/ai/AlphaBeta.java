package datastructureproject.ai;

/**
 * This contains code which is the core of the alpha-beta-algorithm.
 */
public class AlphaBeta {

    public static int AlphaBetaArvo(AlphaBetaState state){
        if(state.isMaxState()) {
            return getMaxValue(state, -1, 1);
        }
        return getMinValue(state, 1, -1);
    }

    private static int getMinValue(AlphaBetaState state, int alpha, int beta){
        if(state.isFinalState()) {
            return state.value();
        }

        int value = Integer.MAX_VALUE;
        for(AlphaBetaState adjacent : state.nextStates()){
            value = Math.min(value, getMaxValue(adjacent, alpha, beta));
            if(value <= alpha)
                return value;

            beta = Math.min(beta, value);
        }
        return value;
    }

    private static int getMaxValue(AlphaBetaState state, int alpha, int beta) {
        if(state.isFinalState())
            return state.value();
        int value = Integer.MIN_VALUE;
        for(AlphaBetaState adjacent : state.nextStates()){
            value = Math.max(value, getMinValue(adjacent, alpha, beta));
            if(value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }


}
