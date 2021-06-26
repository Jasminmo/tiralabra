package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNode;
import datastructureproject.ai.algorithms.GameNodeEvaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class EvaluatingPicker implements ChessMovePicker {
    private final GameNodeEvaluator evaluator;

    public EvaluatingPicker(GameNodeEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Move pickNextMove(GameNode gameNode) {
        if (gameNode == null)
            return null;

        PriorityQueue<GameNode> states = gameNode.getNextStates();
        System.out.println(states.size() + " possible moves next");

        if (states.isEmpty()) {
            return null;
        } else if (states.size() == 1) {
            return states.peek().getLastMove();
        } else {
            Map<GameNode, Integer> values = new HashMap<>();
            GameNode best = states.peek();
            GameNode worst = best;
            System.out.println("Best move before eval " + best.getLastMove() + " has " + best.value() + " points\n");

            for (GameNode state : states) {
                int current = evaluator.evaluate(state);
                values.put(state, current);
                if (current > values.get(best)) {
                    best = state;
                } else if (current < values.get(worst)) {
                    worst = state;
                }
            }

            System.out.print("Best  move " + best.getLastMove());
            System.out.print(" has " + best.value() + " immediate points ");
            System.out.println("and " + values.get(best) + " future points\n");

            System.out.print("Worst  move " + worst.getLastMove());
            System.out.print(" has " + worst.value() + " immediate points ");
            System.out.println("and " + values.get(worst) + " future points\n");

            return best.getLastMove();
        }
    }
}
