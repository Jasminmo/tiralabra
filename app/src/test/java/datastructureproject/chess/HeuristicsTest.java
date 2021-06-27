package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.Board;
import datastructureproject.datastructures.Piece;

import static org.junit.Assert.*;

import datastructureproject.datastructures.Side;
import org.junit.Test;

public class HeuristicsTest {
    private static String startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1\n";

    @Test
    public void testGetPieceValuesIsNotNullOrEmpty() {
        assertNotNull((new Heuristics()).getPieceValues());
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
        assertEquals("White king should have a value of 100", 100, Heuristics.getPieceValue(Piece.WHITE_KING));
        assertEquals("Black king should have a value of 100", 100, Heuristics.getPieceValue(Piece.BLACK_KING));
    }

    @Test
    public void testBoardValueIsEqualForBothSideAtTheStartState() {
        assertEquals("The board value should be equal", 0, Heuristics.getBoardValue(new Board()));
        assertEquals("The board value should be equal", 0, Heuristics.getBoardValue(new Board(), "WHITE"));
        assertEquals("The board value should be equal", 0, Heuristics.getBoardValue(new Board(), "BLACK"));

        assertEquals("The board value should be equal", Heuristics.getBoardValueForSide(startingFen, Side.WHITE), Heuristics.getBoardValueForSide(startingFen, Side.BLACK));
    }

    @Test
    public void testGetBoardValueForPieceAtTheStartState() {
        assertEquals("The board value for white pawn should have a value of 8", 8, Heuristics.getBoardValueForPiece(startingFen, Piece.WHITE_PAWN));
        assertEquals("The board value for both sides should be equal", Heuristics.getBoardValueForPiece(startingFen, Piece.WHITE_PAWN), Heuristics.getBoardValueForPiece(startingFen, Piece.BLACK_PAWN));
    }

}
