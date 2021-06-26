package datastructureproject.util;

import chess.engine.GameState;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

public interface GameStateParser {

    static Board updateState(GameState gs) {
        Board b = new Board();
        gs.moves.forEach(m -> setMove(b, m));
        return b;
    }

    static void setMove(Board b, String move) {
        if (isPromotionMove(move)) {
            setPromotionMove(b, move);
        } else {
            b.doMove(new Move(Square.fromValue(getStartLocation(move)), Square.valueOf(getEndLocation(move))));
        }
    }

    static String getStartLocation(String move) {
        return move.substring(0, 2).toUpperCase();
    }

    static String getEndLocation(String move) {
        return move.substring(2, 4).toUpperCase();
    }

    static boolean isPromotionMove(String move) {
        return move.length() > 4;
    }

    static String getPromotion(String move) {
        return move.substring(4).toUpperCase();
    }

    static void setPromotionMove(Board b, String move) {
        String side = b.getPiece(getStartSquare(move)).getPieceSide().value();
        b.doMove(new Move(getStartSquare(move), getEndSquare(move), getPromotionPiece(side, move)));
    }

    static Square getStartSquare(String move) {
        return Square.valueOf(getStartLocation(move));
    }

    static Square getEndSquare(String move) {
        return Square.valueOf(getEndLocation(move));
    }

    static Piece getPromotionPiece(String player, String move) {
        String promote = getPromotion(move);
        String promotionPiece = player + "_";
        if (promote.equals("Q"))
            promotionPiece += "QUEEN";
        else if (promote.equals("N"))
            promotionPiece += "KNIGHT";
        else if (promote.equals("B"))
            promotionPiece += "BISHOP";
        else if (promote.equals("R"))
            promotionPiece += "ROOK";
        return Piece.fromValue(promotionPiece);
    }

}
