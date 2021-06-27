package datastructureproject.util;

import chess.engine.GameState;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateParserTest {

    @Test
    public void testGetStartAndEndSquares() {
        assertEquals(Square.A2, GameStateParser.getStartSquare("a2a4"));
        assertEquals(Square.A4, GameStateParser.getEndSquare("a2a4"));
    }


    @Test
    public void testIsPromotionMove() {
        assertFalse("This should be false!", GameStateParser.isPromotionMove("...."));
        assertTrue("This should be true!", GameStateParser.isPromotionMove("....."));
    }

    @Test
    public void testGetPromotion() {
        assertEquals("Q", GameStateParser.getPromotion("a2a1q"));
    }

    @Test
    public void testGetPromotionPiece() {
        assertEquals(Piece.BLACK_QUEEN, GameStateParser.getPromotionPiece("BLACK", "a2a1q"));
        assertEquals(Piece.BLACK_KNIGHT, GameStateParser.getPromotionPiece("BLACK", "a2a1n"));
        assertEquals(Piece.BLACK_BISHOP, GameStateParser.getPromotionPiece("BLACK", "a2a1b"));
        assertEquals(Piece.BLACK_ROOK, GameStateParser.getPromotionPiece("BLACK", "a2a1r"));
    }

    @Test
    public void testUpdateState() {
        Board b = new Board();
        GameState gs = new GameState();
        assertEquals(b, GameStateParser.updateState(gs));
        String move = "a2a4";
        b.doMove(new Move(move, Side.WHITE));
        gs.setMoves(move);
        assertEquals(b, GameStateParser.updateState(gs));
    }

    @Test
    public void testUpdateStatePromotion() {
        Board b = new Board();
        GameState gs = new GameState();
        assertEquals(b, GameStateParser.updateState(gs));
        String move = "a2a1q";
        b.doMove(new Move(move, Side.WHITE));
        gs.setMoves(move);
        assertEquals(b, GameStateParser.updateState(gs));
    }
}
