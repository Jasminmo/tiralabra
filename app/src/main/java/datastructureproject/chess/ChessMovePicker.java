package datastructureproject.chess;

import com.github.bhlangonijr.chesslib.move.Move;
import datastructureproject.ai.algorithms.GameNode;

interface ChessMovePicker {
    Move pickNextMove(GameNode state);
}
