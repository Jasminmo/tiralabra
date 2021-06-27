package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BoardStateTest {
    private BoardState state;
    private Board board;
    private Board mockBoard;

    @Before
    public void setUp() throws Exception {
        board = new Board();

        mockBoard = mock(Board.class, Answers.RETURNS_DEEP_STUBS);
        when(mockBoard.isDraw()).thenReturn(false);
        when(mockBoard.isMated()).thenReturn(false);
        when(mockBoard.isStaleMate()).thenReturn(false);
        when(mockBoard.isKingAttacked()).thenReturn(false);
        when(mockBoard.getSideToMove()).thenReturn(Side.WHITE);
    }

    @Test
    public void testIsMaxState() {
        state = new BoardState(board, Side.WHITE);

        assertTrue("Should be maxState", state.isMaxState());
        board.doMove(new Move(Square.A2, Square.A4));
        assertFalse("Shouldn't be maxState", state.isMaxState());
    }

    @Test
    public void testIsFinalState() {
        state = new BoardState(board, Side.WHITE);
        assertFalse("Shouldn't be final state", state.isFinalState());

        when(mockBoard.isDraw()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertTrue("Should be final state", state.isFinalState());
    }

    @Test
    public void testGetLastMove() {
        Move mockMove = mock(Move.class);
        when(mockBoard.getBackup().getLast().getMove()).thenReturn(mockMove);

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should return last move", mockMove, state.getLastMove());
    }

    @Test
    public void testValueBeforeAnyMoves() {
        state = new BoardState(board, Side.WHITE);
        assertEquals("Should be before any move", 0, state.value());
    }

    @Test
    public void testValueWhenIsMated() {
        when(mockBoard.isMated()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should be -1000", -1000, state.value());

        when(mockBoard.getSideToMove()).thenReturn(Side.BLACK);
        assertEquals("Should be 1000", 1000, state.value());
    }

    @Test
    public void testValueWhenIsStaleMate() {
        when(mockBoard.isStaleMate()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should be 1000", 1000, state.value());
    }

    @Test
    public void testValueWhenIsDraw() {
        when(mockBoard.isDraw()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should be -100", -100, state.value());
    }

    @Test
    public void testValueWhenIsKingAttacked() {
        when(mockBoard.isKingAttacked()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should be -100", -100, state.value());
    }

    @Test
    public void testValueWithOnePawnLeft() {
        when(mockBoard.getFen()).thenReturn("P");

        state = new BoardState(mockBoard, Side.WHITE);
        assertEquals("Should be 1", 1, state.value());
    }

    @Test
    public void testGetNextStatesOfFinalState() {
        when(mockBoard.isDraw()).thenReturn(true);

        state = new BoardState(mockBoard, Side.WHITE);
        assertTrue("Should be empty", state.getNextStates().isEmpty());
    }

    @Test
    public void testGetNextStatesOfNonFinalState() {
        state = new BoardState(board, Side.WHITE);
        assertFalse("Should not be empty", state.getNextStates().isEmpty());

        //assertFalse("Should not be empty", state.getNextStates().size());
    }
}
