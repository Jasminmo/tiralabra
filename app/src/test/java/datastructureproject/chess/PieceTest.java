package datastructureproject.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceTest {
    @Test
    public void testFromFENReturnsPawnWithCorrectSide () {
        String key = "P";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white pawn", Piece.WHITE_PAWN, Piece.fromFEN(key));
        key = "p";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black pawn", Piece.BLACK_PAWN, Piece.fromFEN(key));
    }

    @Test
    public void testFromFENReturnsRookWithCorrectSide () {
        String key = "R";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white rook", Piece.WHITE_ROOK, Piece.fromFEN(key));
        key = "r";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black rook", Piece.BLACK_ROOK, Piece.fromFEN(key));
    }

    @Test
    public void testFromFENReturnsBishopWithCorrectSide () {
        String key = "B";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white bishop", Piece.WHITE_BISHOP, Piece.fromFEN(key));
        key = "b";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black bishop", Piece.BLACK_BISHOP, Piece.fromFEN(key));
    }

    @Test
    public void testFromFENReturnsKnightWithCorrectSide () {
        String key = "N";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white knight", Piece.WHITE_KNIGHT, Piece.fromFEN(key));
        key = "n";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black knight", Piece.BLACK_KNIGHT, Piece.fromFEN(key));
    }

    @Test
    public void testFromFENReturnsQueenWithCorrectSide () {
        String key = "Q";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white queen", Piece.WHITE_QUEEN, Piece.fromFEN(key));
        key = "q";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black queen", Piece.BLACK_QUEEN, Piece.fromFEN(key));
    }

    @Test
    public void testFromFENReturnsKingWithCorrectSide () {
        String key = "K";
        assertEquals("piece is white", Side.WHITE, Piece.fromFEN(key).getSide());
        assertEquals("piece is white king", Piece.WHITE_KING, Piece.fromFEN(key));
        key = "k";
        assertEquals("piece is black", Side.BLACK, Piece.fromFEN(key).getSide());
        assertEquals("piece is black king", Piece.BLACK_KING, Piece.fromFEN(key));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromValueThrowsException () {
        Piece.fromFEN("a");
    }


    @Test
    public void testKingToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white king it returns K", "K", Piece.WHITE_KING.toFENCharacter());
        assertEquals("given a black king it returns k", "k", Piece.BLACK_KING.toFENCharacter());
    }

    @Test
    public void testQueenToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white queen it returns Q", "Q", Piece.WHITE_QUEEN.toFENCharacter());
        assertEquals("given a black queen it returns q", "q", Piece.BLACK_QUEEN.toFENCharacter());
    }

    @Test
    public void testKnightToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white knight it returns N", "N", Piece.WHITE_KNIGHT.toFENCharacter());
        assertEquals("given a black knight it returns n", "n", Piece.BLACK_KNIGHT.toFENCharacter());
    }

    @Test
    public void testBishopToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white bishop it returns B", "B", Piece.WHITE_BISHOP.toFENCharacter());
        assertEquals("given a black bishop it returns b", "b", Piece.BLACK_BISHOP.toFENCharacter());
    }

    @Test
    public void testRookToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white rook it returns R", "R", Piece.WHITE_ROOK.toFENCharacter());
        assertEquals("given a black rook it returns r", "r", Piece.BLACK_ROOK.toFENCharacter());
    }

    @Test
    public void testPawnToFENCharacterReturnsStringWithFENPiece () {
        assertEquals("given a white pawn it returns P", "P", Piece.WHITE_PAWN.toFENCharacter());
        assertEquals("given a black pawn it returns p", "p", Piece.BLACK_PAWN.toFENCharacter());
    }

}
