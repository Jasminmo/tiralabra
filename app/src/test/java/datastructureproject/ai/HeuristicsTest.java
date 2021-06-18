package datastructureproject.ai;

import datastructureproject.chess.Piece;

import java.util.Map;

import static org.junit.Assert.*;
import org.junit.Test;

public class HeuristicsTest {

    @Test
    public void testGetPieceValuesIsNotNullOrEmpty() {
        assertNotNull(Heuristics.getPieceValues());
        assertNotEquals(0, Heuristics.getPieceValues().size());
    }

    @Test
    public void testGetPieceValuesPawnHasValueOfOne() {
        assertEquals("White pawn should have a value of 1", 1, Heuristics.getPieceValue(Piece.WHITE_PAWN));
        assertEquals("Black pawn should have a value of 1", 1, Heuristics.getPieceValue(Piece.BLACK_PAWN));
    }

    @Test
    public void testGetPieceValuesRookHasValueOfThree() {
        assertEquals("White rook should have a value of 5", 5, Heuristics.getPieceValue(Piece.WHITE_ROOK));
        assertEquals("Black rook should have a value of 5", 5, Heuristics.getPieceValue(Piece.BLACK_ROOK));
    }

    @Test
    public void testGetPieceValuesBishopHasValueOfThree() {
        assertEquals("White bishop should have a value of 3", 3, Heuristics.getPieceValue(Piece.WHITE_BISHOP));
        assertEquals("Black bishop should have a value of 3", 3, Heuristics.getPieceValue(Piece.BLACK_BISHOP));
    }

    @Test
    public void testGetPieceValuesKnightHasValueOfFive() {
        assertEquals("White knight should have a value of 3", 3, Heuristics.getPieceValue(Piece.WHITE_KNIGHT));
        assertEquals("Black knight should have a value of 3", 3, Heuristics.getPieceValue(Piece.BLACK_KNIGHT));
    }

    @Test
    public void testGetPieceValuesQueenHasValueOfNine() {
        assertEquals("White queen should have a value of 9", 9, Heuristics.getPieceValue(Piece.WHITE_QUEEN));
        assertEquals("Black queen should have a value of 9", 9, Heuristics.getPieceValue(Piece.BLACK_QUEEN));
    }

    @Test
    public void testGetPieceValuesKingHasValueOfZero() {
        assertEquals("White king should have a value of 0", 0, Heuristics.getPieceValue(Piece.WHITE_KING));
        assertEquals("Black king should have a value of 0", 0, Heuristics.getPieceValue(Piece.BLACK_KING));
    }
}
