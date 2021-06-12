package datastructureproject.ai;

import chess.bot.*;
import chess.engine.*;
import chess.model.Side;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;
import datastructureproject.util.GameStateParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AlphaBetaBot implements ChessBot, AlphaBetaState {
    private Board b;

    public Board getBoard() {
        return b;
    }

    public AlphaBetaBot() {
        this.b = new Board();
    }

    public AlphaBetaBot(Board b) {
        this.b = b;
    }

    @Override
    public String nextMove(GameState gs) {
        if (!gs.moves.isEmpty()) {
            //System.out.println("Latest move: " + gs.getLatestMove());
            b = GameStateParser.updateState(gs);
        }
        try {
            Move myMove = getNextMove();
            b.doMove(myMove);
            if (myMove != null) {
                return myMove.toString();
            }
        } catch (Exception ex) {
            Logger.getLogger(TestBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Move getNextMove() throws MoveGeneratorException {
        List<AlphaBetaState> states = nextStates();
        System.out.println(states.size() + " possible moves next");
        if (states.isEmpty()) {
            return null;
        } else if (states.size() == 1) {
            Board board = ((AlphaBetaBot) states.get(0)).getBoard();
            Move move = board.getBackup().getLast().getMove();
            return move;
        } else {
            Map<AlphaBetaState, Integer> values = new HashMap<>();
            AlphaBetaState best = null;
            AlphaBetaState worst = null;
            for (AlphaBetaState state : states) {
                int current = AlphaBeta.evaluate(state, 3);
                values.put(state, current);
                if (best == null) {
                    best = worst = state;
                } else {
                    if (current > values.get(best)) {
                        best = state;
                    } else if (current < values.get(worst)) {
                        worst = state;
                    }
                }
            }
            //System.out.println(values);
            System.out.println("Worst move " + getMove((AlphaBetaBot) worst) + " has " + values.get(worst) + " points");
            System.out.println("Best  move " + getMove((AlphaBetaBot) best)  + " has " + values.get(best)  + " points");
            return getMove((AlphaBetaBot) best);
        }
    }


    @Override
    public List<AlphaBetaState> nextStates() {
        List<AlphaBetaState> nextStates = new ArrayList<>();
        try {
            MoveList moves = MoveGenerator.generateLegalMoves(b);
            nextStates = moves.stream().map(move -> {
                Board board = b.clone();
                board.doMove(move);
                return new AlphaBetaBot(board);
            }).collect(Collectors.toList());
        } catch (MoveGeneratorException e) {
            e.printStackTrace();
        }
        return nextStates;
    }

    @Override
    public boolean isMaxState() {
        return b.getSideToMove().toString().equals(Side.WHITE.toString());
    }

    @Override
    public boolean isFinalState() {
        return b.isDraw() || b.isMated() || b.isStaleMate();
    }

    @Override
    public int value() {
        if (b.isMated())
            return -1000;
        return Heuristics.getBoardValue(b);
    }

    private Move getMove(AlphaBetaBot bot) {
        Board board = bot.getBoard();
        return board.getBackup().getLast().getMove();
    }
}