package datastructureproject.datastructures;

import datastructureproject.ai.algorithms.GameNode;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameNodeComparatorTest {
    private final GameNodeComparator comparator = new GameNodeComparator();

    @Test
    public void testCompare() {
        GameNode left = mock(GameNode.class);
        GameNode right = mock(GameNode.class);

        when(left.value()).thenReturn(50);
        when(right.value()).thenReturn(100);

        assertEquals("the value should equal 50", 50, comparator.compare(left, right));
    }
}
