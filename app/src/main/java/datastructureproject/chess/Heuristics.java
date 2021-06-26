package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.Board;
import datastructureproject.datastructures.Piece;
import datastructureproject.datastructures.Side;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains static functions which are used to evaluate the current board situation.
 */
public class Heuristics {
    private static final Map<Piece, Integer> map;

    static {
        map = new HashMap<>();
        map.put(Piece.WHITE_PAWN, 1);
        map.put(Piece.BLACK_PAWN, 1);

        map.put(Piece.WHITE_ROOK, 5);
        map.put(Piece.BLACK_ROOK, 5);

        map.put(Piece.WHITE_BISHOP, 3);
        map.put(Piece.BLACK_BISHOP, 3);

        map.put(Piece.WHITE_KNIGHT, 3);
        map.put(Piece.BLACK_KNIGHT, 3);

        map.put(Piece.WHITE_QUEEN, 9);
        map.put(Piece.BLACK_QUEEN, 9);

        map.put(Piece.WHITE_KING, 100);
        map.put(Piece.BLACK_KING, 100);
    }

    /**
     *
     * @return Map<Piece, Integer> - a map of piece values
     */
    public static Map<Piece, Integer> getPieceValues() {
        return map;
    }

    /** This function returns the value of a given piece.
     *
     * @param piece
     * @return value of piece.
     */
    public static int getPieceValue(Piece piece) {
        return getPieceValues().get(piece);
    }

    /** This function returns the number of occurrences of a given piece.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param piece - piece which we are counting.
     * @return number of occurrences of piece.
     */
    public static int getOccurrences(String fen, Piece piece) {
        return fen.split(" ")[0].split(piece.toFENCharacter(), -1).length - 1;
    }

    /** What is the current value of the gamestate for the player whose turn is next.
     *
     * @param b - Board
     * @return current value of the gamestate
     */
    public static int getBoardValue(Board b) {
        return getBoardValue(b, Side.parseFen(b.getFen()));
    }

    /** What is the current value of the gamestate for the given side.
     *
     * @param b - Board
     * @param side - given side (Side.WHITE or Side.BLACK)
     * @return current value of the gamestate
     */
    public static int getBoardValue(Board b, String side) {
        return getBoardValue(b, Side.valueOf(side));
    }


    /** What is the current value of the gamestate for the given side.
     *
     * @param b - Board
     * @param side - given side (Side.WHITE or Side.BLACK)
     * @return current value of the gamestate
     */
    public static int getBoardValue(Board b, Side side) {
        String fen = b.getFen();
        int player_after = Heuristics.getBoardValueForSide(fen, side);
        int opponent_after = Heuristics.getBoardValueForSide(fen, side.flip());
        return player_after - opponent_after;
    }


    /** What is the current value of the gamestate for the given side.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param side - given side (Side.WHITE or Side.BLACK)
     * @return current value of the gamestate
     */
    public static int getBoardValueForSide(String fen, Side side) {
        if (side.equals(Side.WHITE)) {
            return getBoardValueForWhite(fen);
        }
        return getBoardValueForBlack(fen);
    }

    /** What is the current value of the gamestate for the Side.WHITE player.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @return current value of the gamestate
     */
    public static int getBoardValueForWhite(String fen) {
        return Piece.getWhitePieces()
                .stream()
                .mapToInt(p -> getBoardValueForPiece(fen, p))
                .sum();
    }

    /** What is the current value of the gamestate for the Side.BLACK player.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth-Edwards Notation.
     * @return current value of the gamestate
     */
    public static int getBoardValueForBlack(String fen) {
        return Piece.getBlackPieces()
                .stream()
                .mapToInt(p -> getBoardValueForPiece(fen, p))
                .sum();
    }

    /** This function returns the number of occurrences of a given piece times its value.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param piece - piece which we are counting.
     * @return occurrences of a given piece times its value
     */
    public static int getBoardValueForPiece(String fen, Piece piece) {
        return getPieceValue(piece) * getOccurrences(fen, piece);
    }
}
