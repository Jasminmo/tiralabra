package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNode;
import datastructureproject.ai.algorithms.GameNodeEvaluator;
import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EvaluatingPickerTest {
    private GameNodeEvaluator evaluator;
    private EvaluatingPicker picker;
    private GameNode startState;
    private GameNode returnedState;
    private PriorityQueue<GameNode> queue;
    private Move mockMove;

    @Before
    public void setUp() {
        evaluator = mock(GameNodeEvaluator.class);
        startState = mock(GameNode.class);
        returnedState = mock(GameNode.class);
        queue = mock(PriorityQueue.class);
        mockMove = mock(Move.class);

        when(queue.size()).thenReturn(1);
        when(queue.peek()).thenReturn(returnedState);
        when(evaluator.evaluate(returnedState)).thenReturn(0);
        when(startState.getNextStates()).thenReturn(queue);
        when(returnedState.getLastMove()).thenReturn(mockMove);


        picker = new EvaluatingPicker(evaluator);
    }

    @Test
    public void testPickNextMoveReturnsNullForNullInput() {
        assertNull("should return null", picker.pickNextMove(null));

        GameNode state = mock(GameNode.class);
        when(state.getNextStates()).thenReturn(new PriorityQueue<>());

        assertNull("should return null", picker.pickNextMove(state));
    }

    @Test
    public void testPickNextMoveReturnsTheOnlyStateInTheQueue() {
        assertEquals(mockMove, picker.pickNextMove(startState));
    }

    @Test
    public void testPickNextMoveReturns() {
        BoardState node = new BoardState(new Board(), Side.WHITE);
        assertNotNull("should not be null", picker.pickNextMove(node));
    }
}
