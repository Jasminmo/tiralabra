package datastructureproject.ai;

import datastructureproject.chess.Piece;
import datastructureproject.chess.Side;
import com.github.bhlangonijr.chesslib.Board;

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

        map.put(Piece.WHITE_KING, 0);
        map.put(Piece.BLACK_KING, 0);
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
        return getOccurrences(fen, piece, false);
    }

    /** This function returns the number of occurrences of a given piece.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param piece - piece which we are counting.
     * @param debug - whether any debuggin information should be printed.
     * @return number of occurrences of piece.
     */
    public static int getOccurrences(String fen, Piece piece, boolean debug) {
        int occurs = fen.split(" ")[0].split(piece.toFENCharacter(), -1).length - 1;
        if (debug) {
            System.out.print(" " + piece + " occurs " + occurs + " ");
        }
        return occurs;
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
    public static int getBoardValue(Board b, Side side) {
        return getBoardValue(b, side, false);
    }


    /** What is the current value of the gamestate for the given side.
     *
     * @param b - Board
     * @param side - given side (Side.WHITE or Side.BLACK)
     * @param debug - whether any debuggin information should be printed.
     * @return current value of the gamestate
     */
    public static int getBoardValue(Board b, Side side, boolean debug) {
        String fen = b.getFen();
        int player_after = Heuristics.getBoardValueForSide(fen, side, debug);
        int opponent_after = Heuristics.getBoardValueForSide(fen, side.flip(), debug);
        if (debug) {
            System.out.println("player: " + player_after + "\t opponent:" + opponent_after);
        }
        return player_after - opponent_after;
    }


    /** What is the current value of the gamestate for the given side.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param side - given side (Side.WHITE or Side.BLACK)
     * @param debug - whether any debuggin information should be printed.
     * @return current value of the gamestate
     */
    public static int getBoardValueForSide(String fen, Side side, boolean debug) {
        if (side.equals(Side.WHITE)) {
            return getBoardValueForWhite(fen, debug);
        }
        return getBoardValueForBlack(fen, debug);
    }

    /** What is the current value of the gamestate for the Side.WHITE player.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param debug - whether any debuggin information should be printed.
     * @return current value of the gamestate
     */
    public static int getBoardValueForWhite(String fen, boolean debug) {
        return Piece.getWhitePieces()
                .stream()
                .mapToInt(p -> getBoardValueForPiece(fen, p, debug))
                .sum();
    }

    /** What is the current value of the gamestate for the Side.BLACK player.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param debug - whether any debuggin information should be printed.
     * @return current value of the gamestate
     */
    public static int getBoardValueForBlack(String fen, boolean debug) {
        return Piece.getBlackPieces()
                .stream()
                .mapToInt(p -> getBoardValueForPiece(fen, p, debug))
                .sum();
    }

    /** This function returns the number of occurrences of a given piece times its value.
     *
     * @param fen - a string representing the current situation in
     *            the chess board which is in Forsyth–Edwards Notation.
     * @param piece - piece which we are counting.
     * @param debug - whether any debuggin information should be printed.
     * @return occurrences of a given piece times its value
     */
    public static int getBoardValueForPiece(String fen, Piece piece, boolean debug) {
        return getPieceValue(piece) * getOccurrences(fen, piece, debug);
    }
}
