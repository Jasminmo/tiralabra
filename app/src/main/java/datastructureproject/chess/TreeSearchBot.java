package datastructureproject.chess;

import chess.bot.ChessBot;
import chess.engine.GameState;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNodeEvaluator;
import datastructureproject.util.GameStateParser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeSearchBot implements ChessBot {
    private final ChessMovePicker movePicker;

    /** Constructor
     *
     * @param movePicker: The chess move picker which is used to search the tree and pick next chess move.
     */
    public TreeSearchBot(ChessMovePicker movePicker) {
        this.movePicker = movePicker;
    }

    /** Constructor
     *
     * @param evaluator: The algorithm which is used to search the tree.
     */
    public TreeSearchBot(GameNodeEvaluator evaluator) {
        movePicker = new EvaluatingPicker(evaluator);
    }

    @Override
    public String nextMove(GameState gs) {
        Side thisSide = Side.fromValue(gs.playing.toString());
        System.out.println("Playing as " + thisSide);

        Board board = new Board();
        if (!gs.moves.isEmpty()) {
            board = GameStateParser.updateState(gs);
            System.out.println("previous move made by opponent: " + gs.getLatestMove());
        }

        BoardState gameNode = new BoardState(board, thisSide);
        try {
            long start = System.currentTimeMillis();
            Move nextMove = movePicker.pickNextMove(gameNode);
            System.out.println("nextMove: " + nextMove);

            long end = System.currentTimeMillis();
            System.out.println("Move took " + (end - start) / 1000. + " s");

            if (nextMove != null) {
                return nextMove.toString();
            }
        } catch (Exception ex) {
            Logger.getLogger(TreeSearchBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
