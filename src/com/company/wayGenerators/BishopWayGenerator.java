package com.company.wayGenerators;

import com.company.figures.ChessFigure;
import com.company.chessTools.ChessCell;
import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class BishopWayGenerator extends WayGenerator{
    @Override
    public List<List<Coordinate>> getPossibleMoves(ChessGame game, Coordinate curr) {
        List<List<Coordinate>> moves = new ArrayList<>();
        List<Coordinate> capturable = new ArrayList<>();
        List<Coordinate> reachable = new ArrayList<>();

        ChessCell[][] chessField = game.getChessField();
        int row = curr.getRow();
        int col = curr.getCol();
        int[][] deltas = new int[][]{new int[]{1, 1}, new int[]{-1, -1},
                new int[]{1, -1}, new int[]{-1, 1}};
        for(int[] delta : deltas){
            row += delta[0];
            col += delta[1];
            while(row>=0 && col>=0 && row<chessField.length && col<chessField.length){
                ChessFigure figure = chessField[row][col].getFigure();
                if(figure==null){
                    reachable.add(new Coordinate(row, col));
                } else {
                    if(figure.isWhite()!=game.isWhitesTurn()){
                        capturable.add(new Coordinate(row, col));
                    }
                    break;
                }
                row += delta[0];
                col += delta[1];
            }
            row = curr.getRow();
            col = curr.getCol();
        }

        moves.add(capturable);
        moves.add(reachable);
        return moves;
    }
}
