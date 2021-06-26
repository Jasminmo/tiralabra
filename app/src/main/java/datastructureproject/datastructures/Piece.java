package datastructureproject.datastructures;

import java.util.Arrays;
import java.util.List;

public enum Piece {
    /**
     * BLACK pawn piece.
     */
    WHITE_PAWN(Side.WHITE),
    /**
     * BLACK knight piece.
     */
    WHITE_KNIGHT(Side.WHITE),
    /**
     * BLACK bishop piece.
     */
    WHITE_BISHOP(Side.WHITE),
    /**
     * BLACK rook piece.
     */
    WHITE_ROOK(Side.WHITE),
    /**
     * BLACK queen piece.
     */
    WHITE_QUEEN(Side.WHITE),
    /**
     * BLACK king piece.
     */
    WHITE_KING(Side.WHITE),

    /**
     * BLACK pawn piece.
     */
    BLACK_PAWN(Side.BLACK),
    /**
     * BLACK knight piece.
     */
    BLACK_KNIGHT(Side.BLACK),
    /**
     * BLACK bishop piece.
     */
    BLACK_BISHOP(Side.BLACK),
    /**
     * BLACK rook piece.
     */
    BLACK_ROOK(Side.BLACK),
    /**
     * BLACK queen piece.
     */
    BLACK_QUEEN(Side.BLACK),
    /**
     * BLACK king piece.
     */
    BLACK_KING(Side.BLACK);

    private final Side side;

    Piece(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    public boolean hasSide(Side side) {
        return this.side == side;
    }

    public static List<Piece> getAll() {
        return Arrays.asList(WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING,
                BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING);
    }

    public static List<Piece> getWhitePieces() {
        return Arrays.asList(WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING);
    }

    public static List<Piece> getBlackPieces() {
        return Arrays.asList(BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING);
    }

    /**
     * From value piece.
     *
     * @param string the FEN-letter representing a piece
     * @return the piece type
     */
    public static Piece fromFEN(String string) {
        char key = string.charAt(0);
        boolean isWhite = Character.isUpperCase(key);
        key = Character.toLowerCase(key);
        switch (key) {
            case 'p': return (isWhite)? WHITE_PAWN: BLACK_PAWN;
            case 'r': return (isWhite)? WHITE_ROOK: BLACK_ROOK;
            case 'b': return (isWhite)? WHITE_BISHOP: BLACK_BISHOP;
            case 'n': return (isWhite)? WHITE_KNIGHT: BLACK_KNIGHT;
            case 'q': return (isWhite)? WHITE_QUEEN: BLACK_QUEEN;
            case 'k': return (isWhite)? WHITE_KING: BLACK_KING;
            default: throw new IllegalArgumentException("The letter can be one of 'pnbrqkPNBRQK'");
        }
    }

    /**
     * @return the FEN-letter representing a piece
     */
    public String toFENCharacter() {
        String[] parts = toString().split("_");
        String color = parts[0];
        String type = parts[1];
        boolean isWhite = color.equals("WHITE");
        switch (type) {
            case "PAWN": return (isWhite)? "P": "p";
            case "ROOK": return (isWhite)? "R": "r";
            case "BISHOP": return (isWhite)? "B": "b";
            case "KNIGHT": return (isWhite)? "N": "n";
            case "QUEEN": return (isWhite)? "Q": "q";
            // case "KING": return (isWhite)? "K": "k";
            default: return (isWhite)? "K": "k"; // KING
        }
    }
}
