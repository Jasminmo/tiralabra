package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNode;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreedyMovePickerTest {
    private GreedyMovePicker gmp = new GreedyMovePicker();

    @Test
    public void testPickNextMoveReturnsNullGivenNull() {
        assertNull("returns null when given null", gmp.pickNextMove(null));
    }

    @Test
    public void testPickNextMoveReturnsNullGivenStateWithNoNextMoves() {
        GameNode state = mock(GameNode.class);
        when(state.getNextStates()).thenReturn(null);
        assertNull("returns null when queue is null", gmp.pickNextMove(state));
    }

    @Test
    public void testPickNextMoveReturnsNullGivenStateWithNoEmptyStates() {
        GameNode state = mock(GameNode.class);
        when(state.getNextStates()).thenReturn(new PriorityQueue<>());
        assertNull("returns null when queue is empty", gmp.pickNextMove(state));
    }

    @Test
    public void testPickNextMoveForNonNullValues() {
        GameNode state = mock(GameNode.class);
        GameNode returnedState = mock(GameNode.class);
        PriorityQueue<GameNode> queue = mock(PriorityQueue.class);
        Move mockMove = mock(Move.class);

        when(queue.peek()).thenReturn(returnedState);
        when(state.getNextStates()).thenReturn(queue);
        when(returnedState.getLastMove()).thenReturn(mockMove);

        assertEquals("returns a move when queue is not empty", mockMove, gmp.pickNextMove(state));
    }

}
