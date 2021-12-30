package com.company.wayGenerators;

import com.company.chessTools.ChessCell;
import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class PawnWayGenerator extends WayGenerator{
    @Override
    public List<List<Coordinate>> getPossibleMoves(ChessGame game, Coordinate curr) {
        List<List<Coordinate>> moves = new ArrayList<>();
        List<Coordinate> capturable = new ArrayList<>();
        List<Coordinate> reachable = new ArrayList<>();

        ChessCell[][] chessField = game.getChessField();
        int delta = chessField[curr.getRow()][curr.getCol()].getFigure().isWhite() ? -1 : 1;
        int row = curr.getRow() + delta;
        int col = curr.getCol();
        if(chessField[row][col].getFigure()==null){
            reachable.add(new Coordinate(row, col));
        }
        if(col+1 < chessField.length && chessField[row][col+1].getFigure()!=null &&
                chessField[row][col+1].getFigure().isWhite()!=game.isWhitesTurn()){
            capturable.add(new Coordinate(row, col+1));
        }
        if(col-1 >= 0 && chessField[row][col-1].getFigure()!=null &&
                chessField[row][col-1].getFigure().isWhite()!= game.isWhitesTurn()){
            capturable.add(new Coordinate(row, col-1));
        }

        moves.add(capturable);
        moves.add(reachable);
        return moves;
    }
}
