package datastructureproject.datastructures;


import datastructureproject.ai.algorithms.GameNode;

import java.util.Comparator;

public class GameNodeComparator implements Comparator<GameNode> {
    @Override
    public int compare(GameNode left, GameNode right) {
        return right.value() - left.value();
    }
}
