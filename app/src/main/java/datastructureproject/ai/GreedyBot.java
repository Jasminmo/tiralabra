package datastructureproject.ai;

import chess.bot.*;
import chess.engine.*;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;
import datastructureproject.util.GameStateParser;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreedyBot implements ChessBot {
    private Board b = new Board();

    @Override
    public String nextMove(GameState gs) {
        if (!gs.moves.isEmpty()) {
            //System.out.println("Latest move: " + gs.getLatestMove());
            b = GameStateParser.updateState(gs);
        }
        //System.out.println(gs.playing + " playing");
        //int player_before = Heuristics.getValue(b, side, true);
        //int opponent_before = Heuristics.getValue(b, !side, true);
        //System.out.println("You      before:" + player_before);
        //System.out.println("Opponent before:" + opponent_before);

        try {
            Move myMove = getNextGreedyMove();
            b.doMove(myMove);
            if (myMove != null) {
                return myMove.toString();
            }
        } catch (Exception ex) {
            Logger.getLogger(TestBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Move getNextGreedyMove() throws MoveGeneratorException {
        MoveList moves = MoveGenerator.generateLegalMoves(b);
        //System.out.println(moves.size() + " possible moves next");
        if (moves.isEmpty()) {
            return null;
        } else if (moves.size() == 1) {
            return moves.get(0);
        } else {
            Map<Move, Integer> values = new HashMap<>();
            Move best = null;
            Move worst = null;
            for (Move move : moves) {
                b.doMove(move);
                values.put(move, Heuristics.getBoardValue(b));
                if (best == null) {
                    best = worst = move;
                } else {
                    int current = values.get(move);
                    if (current > values.get(best)) {
                        best = move;
                    } else if (current < values.get(worst)) {
                        worst = move;
                    }
                }
                b.undoMove();
            }
            //System.out.println(values);
            //System.out.println("Worst move " + worst + " has " + values.get(worst) + " points");
            //System.out.println("Best  move " + best  + " has " + values.get(best)  + " points");
            return best;
        }
    }

}
