package chess.ai;

import com.github.bhlangonijr.chesslib.Board;

public class Heuristics {
    public static int getValue(Board board, boolean isWhite) {
        String placement = board.getFen().split(" ")[0];
        String pawn = "p";
        String rook = "r";
        String night = "n";
        String queen = "q";
        String bishop = "b";
        if (isWhite) {
            pawn = pawn.toUpperCase();
            rook = pawn.toUpperCase();
            night = pawn.toUpperCase();
            queen = pawn.toUpperCase();
            bishop = pawn.toUpperCase();
        }
        int pawns = placement.split(pawn, -1).length;
        int rooks = placement.split(rook, -1).length;
        int nights = placement.split(night, -1).length;
        boolean hasQueen = placement.contains(queen);
        int bishops = placement.split(bishop, -1).length;
        return pawns + rooks * 10 + bishops * 10 + nights * 5 + ((hasQueen)? 50:0);
    }
}
