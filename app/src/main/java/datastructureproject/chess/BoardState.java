package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;
import datastructureproject.ai.algorithms.GameNode;
import datastructureproject.datastructures.GameNodeComparator;

import java.util.*;
import java.util.stream.Collectors;

public class BoardState implements GameNode {
    private final Board board;
    private static Side thisSide;

    /** Constructor
     *
     * @param gameBoard: Board - the current gameBoard
     * @param sideToPlay - Side which will be the max-player
     */
    public BoardState(Board gameBoard, Side sideToPlay) {
        board = gameBoard;
        thisSide = sideToPlay;
    }

    /** This function overrides the nextStates() function of the AlphaBetaState-interface,
     * which is used by AlphaBeta-class.
     *
     * @return List<AlphaBetaState> - list of next possible moves that can be made next.
     */
    @Override
    public PriorityQueue<GameNode> getNextStates() {
        PriorityQueue<GameNode> nextStates = new PriorityQueue<>(new GameNodeComparator());
        try {
            MoveList moves = MoveGenerator.generateLegalMoves(this.board);
            List<GameNode> list = moves.stream().map(move -> {
                Board board = this.board.clone();
                board.doMove(move);
                return (GameNode) new BoardState(board, thisSide);
            }).collect(Collectors.toList());
            nextStates.addAll(list);
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
        return board.getSideToMove().equals(thisSide);
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
        int a = (board.getSideToMove().equals(thisSide)) ? -1: 1;

        if (board.isMated())
            return a * 1000;

        if (board.isStaleMate())
            return a * -1000;

        if (board.isDraw())
            return a * 100;

        if (board.isKingAttacked())
            return a * 100;

        return Heuristics.getBoardValue(board, thisSide.toString());
    }

    public Move getLastMove() {
        return board.getBackup().getLast().getMove();
    }

}