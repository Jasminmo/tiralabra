package datastructureproject.ai;

import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Board;

public class Heuristics {

    private static Side fromFen(String fen) {
        Side side = Side.BLACK;
        if (fen.split(" ")[1].equals("w")) {
            side = Side.WHITE;
        }
        return side;
    }

    public static int getBoardValue(AlphaBetaState state) {
        Board b = ((AlphaBetaBot) state).getBoard();
        Side side = fromFen(b.getFen());
        int player_after = Heuristics.getSideValue(b, side);
        int opponent_after = Heuristics.getSideValue(b, side.flip());
        return player_after - opponent_after;
    }


    public static int getBoardValue(Board b) {
        Side side = fromFen(b.getFen());
        int player_after = Heuristics.getSideValue(b, side);
        int opponent_after = Heuristics.getSideValue(b, side.flip());
        return player_after - opponent_after;
    }

    public static int getSideValue(String fen) {
        return getSideValue(fen, fromFen(fen), false);
    }

    public static int getSideValue(Board b, Side side) {
        return getSideValue(b.getFen(), side);
    }

    public static int getSideValue(String fen, Side side) {
        return getSideValue(fen, side, false);
    }


    public static int getSideValue(Board b, Side side, boolean debug) {
        String fen = b.getFen();
        return getSideValue(fen, side, debug);
    }

    public static int getSideValue(String fen, Side side, boolean debug) {
        String[] fenPieces = fen.split(" ");
        String placement = fenPieces[0];
        String pawn = "p";
        String rook = "r";
        String night = "n";
        String queen = "q";
        String bishop = "b";
        if (side.equals(Side.WHITE)) {
            pawn = pawn.toUpperCase();
            rook = rook.toUpperCase();
            night = night.toUpperCase();
            queen = queen.toUpperCase();
            bishop = bishop.toUpperCase();
        }
        int pawns = placement.split(pawn, -1).length - 1;
        int rooks = placement.split(rook, -1).length - 1;
        int nights = placement.split(night, -1).length - 1;
        boolean hasQueen = placement.contains(queen);
        int bishops = placement.split(bishop, -1).length - 1;

        if (debug) {
            System.out.println("placement: " + placement + "\tfen: " + fen);
            System.out.println("This board has: " + pawns + " pawns, " +
                    rooks + " rooks, " + nights + " nights, " + bishops + " bishops" +
                    ((hasQueen) ? " and a queen" : ""));
        }
        return pawns + rooks * 10 + bishops * 10 + nights * 5 + ((hasQueen) ? 50 : 0);
    }
}
