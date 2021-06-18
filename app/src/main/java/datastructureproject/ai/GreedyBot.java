package datastructureproject.ai;

import chess.bot.*;
import chess.engine.*;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;
import datastructureproject.util.GameStateParser;

import datastructureproject.chess.Side;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class evaluates the board situation and gives the best next move according to greedy-algorithm.
 */
public class GreedyBot implements ChessBot {
    private Board b = new Board();
    private boolean DEBUG = true;

    /** This function is called by the LichessAPI and XBoardHandler-classes
     * to get the next chess move given the current game state.
     *
     * @param gs : GameState
     * @return
     */
    @Override
    public String nextMove(GameState gs) {
        if (!gs.moves.isEmpty()) {
            System.out.println("\nOpponents latest move: " + gs.getLatestMove());
            b = GameStateParser.updateState(gs);
        }
        Side thisSide = Side.parseString(b.getSideToMove().toString());
        Side opponentSide = thisSide.flip();

        /*System.out.println("Before next move:");
        int player_before = Heuristics.getBoardValueForSide(b.getFen(), thisSide);
        System.out.println("\tYou (" + thisSide + "):" + player_before);

        int opponent_before = Heuristics.getBoardValueForSide(b.getFen(), opponentSide);
        System.out.println("\tOpponent (" + opponentSide + "):" + opponent_before + "\n");*/

        try {
            Move myMove = getNextGreedyMove(gs, thisSide);
            b.doMove(myMove);
            if (myMove != null) {
                return myMove.toString();
            }
        } catch (Exception ex) {
            Logger.getLogger(TestBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Move getNextGreedyMove(GameState gs, Side side) throws MoveGeneratorException {
        MoveList moves = MoveGenerator.generateLegalMoves(b);

        if (moves.isEmpty()) {
            return null;
        } else if (moves.size() == 1) {
            return moves.get(0);
        } else {
            Map<Move, Integer> values = new HashMap<>();
            Move best = moves.get(0);
            Move worst = best;
            System.out.println("Before: " + Heuristics.getBoardValue(b, side, false));
            for (Move move : moves) {
                b.doMove(move);
                // int value = Heuristics.getBoardValueForSide(b.getFen(), side, false);

                System.out.print(move + "; ");
                int value = Heuristics.getBoardValue(b, side, false);
                values.put(move, value);
                if (value >= values.get(best)) {
                    best = move;
                } else if (value <= values.get(worst)) {
                    worst = move;
                }
                b.undoMove();
            }

            if (DEBUG) {
                System.out.println(moves.size() + " possible moves next");
                System.out.println("Values of each move: " + values);
                System.out.println("Worst move " + worst + " has " + values.get(worst) + " points");
                System.out.println("Best  move " + best + " has " + values.get(best) + " points\n");
            }
            if (values.size() > 1 && values.get(best).equals(values.get(worst))) {
                System.out.println("Picking a random move!");
                return getRandomMove(moves, gs);
            }
            if (gs.moves.size() > 1) {
                if (gs.moves.get(0).equals(best.toString())) {
                    System.out.println("Bot has already made this move. Picking next one!");
                    values.remove(best);
                }
            }
            return getBestMove(values);
        }
    }

    private Move getBestMove(Map<Move, Integer> values) {
        return values.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    private Move getRandomMove(MoveList moves, GameState gs) {
        Move randomMove = moves.get((new Random()).nextInt(moves.size()));
        System.out.println(randomMove + "; " + gs.moves.contains(randomMove.toString()));
        if (!gs.moves.contains(randomMove.toString()))
            return randomMove;
        return getRandomMove(moves, gs);
    }

}
