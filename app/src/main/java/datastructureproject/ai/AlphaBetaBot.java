package datastructureproject.ai;

import com.github.bhlangonijr.chesslib.Square;
import datastructureproject.chess.Side;

import chess.bot.*;
import chess.engine.*;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;
import datastructureproject.util.GameStateParser;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class evaluates the board situation and gives the best next move according to alpha-beta pruning algorithm.
 */
public class AlphaBetaBot implements ChessBot, AlphaBetaState {
    private final static int MAX_DEPTH = 3;
    private Board board;
    private Move lastMove;
    private Random random;
    private static Side thisSide = Side.WHITE;

    /**
     * @return the current gameboard : Board
     */
    public Board getBoard() {
        return board;
    }


    /** Constructor
     */
    public AlphaBetaBot() {
        this(new Board());
    }

    /** Constructor
     *
     * @param board: Board - the current gameboard
     */
    public AlphaBetaBot(Board board) {
        this.board = board;
        this.random = new Random();
    }

    /** This function is called by the LichessAPI and XBoardHandler-classes
     * to get the next chess move given the current game state.
     *
     * @param gs : GameState
     * @return
     */
    @Override
    public String nextMove(GameState gs) {
        thisSide = datastructureproject.chess.Side.parseString(board.getSideToMove().toString());
        if (!gs.moves.isEmpty()) {
            //System.out.println("Latest move: " + gs.getLatestMove());
            board = GameStateParser.updateState(gs);
        }
        try {
            long start = System.currentTimeMillis();
            Move lastMove = getNextMove();
            this.lastMove = lastMove;
            board.doMove(lastMove);
            long end = System.currentTimeMillis();
            System.out.println("Move took " + (end - start) / 1000. + " s");
            if (lastMove != null) {
                return lastMove.toString();
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
                int current = AlphaBeta.evaluate(state, MAX_DEPTH);
                if (lastMove != null && getMove((AlphaBetaBot) state).equals(lastMove))
                    continue;
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
            Move bestMove = getMove((AlphaBetaBot) best);
            Move worstMove = getMove((AlphaBetaBot) worst);
            if (values.get(best).equals(values.get(worst))) {
                System.out.println("Picking a random move!");
                AlphaBetaBot randomState = (AlphaBetaBot) states.get(random.nextInt(states.size()));
                return getMove(randomState);
            }

            //System.out.println(values);
            System.out.println("Worst move " + getMove((AlphaBetaBot) worst) + " has " + values.get(worst) + " points");
            System.out.println("Best  move " + getMove((AlphaBetaBot) best)  + " has " + values.get(best)  + " points");
            return getMove((AlphaBetaBot) best);
        }
    }


    /** This function overrides the nextStates() function of the AlphaBetaState-interface,
     * which is used by AlphaBeta-class.
     *
     * @return List<AlphaBetaState> - list of next possible moves that can be made next.
     */
    @Override
    public List<AlphaBetaState> nextStates() {
        List<AlphaBetaState> nextStates = new ArrayList<>();
        try {
            MoveList moves = MoveGenerator.generateLegalMoves(board);
            nextStates = moves.stream().map(move -> {
                Board board = this.board.clone();
                board.doMove(move);
                return new AlphaBetaBot(board);
            }).collect(Collectors.toList());
        } catch (MoveGeneratorException e) {
            e.printStackTrace();
        }
        return nextStates;
    }

    /** This function overrides the isMaxState() function of the AlphaBetaState-interface,
     * which is used by AlphaBeta-class.
     *
     * @return boolean - is this state a max or min state.
     */
    @Override
    public boolean isMaxState() {
        return board.getSideToMove().toString().equals(Side.WHITE.toString());
    }

    /** This function overrides the isFinalState() function of the AlphaBetaState-interface,
     * which is used by AlphaBeta-class.
     *
     * @return boolean - is this state a final state.
     */
    @Override
    public boolean isFinalState() {
        return board.isDraw() || board.isMated() || board.isStaleMate();
    }

    /** This function overrides the value() function of the AlphaBetaState-interface,
     * which is used by AlphaBeta-class.
     *
     * @return int - the value of this state.
     */
    @Override
    public int value() {
        Square sqr = board.getKingSquare(board.getSideToMove().flip());
        boolean ismate = board.squareAttackedBy(sqr, board.getSideToMove()) != 0;
        if (ismate)
            return 10000;
        return Heuristics.getBoardValue(board);
    }

    private Move getMove(AlphaBetaBot bot) {
        Board board = bot.getBoard();
        return board.getBackup().getLast().getMove();
    }
}