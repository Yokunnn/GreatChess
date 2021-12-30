package com.company.chessTools;

import com.company.figures.ChessFigure;
import com.company.figures.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ChessGame {

    public enum GameState {
        NOT_STARTED,
        PLAYING,
        ENDED
    }

    private static final int DEFAULT_FIELD_SIZE = 10;

    private GameState state = GameState.NOT_STARTED;
    private ChessCell[][] chessField = new ChessCell[DEFAULT_FIELD_SIZE][DEFAULT_FIELD_SIZE];
    private boolean whitesTurn = true;
    private ChessFigure chosenFigure = null;
    private Coordinate chosenCoordinates = null;

    public GameState getState() {
        return state;
    }
    public ChessFigure getChosenFigure() {
        return chosenFigure;
    }
    public ChessCell[][] getChessField(){
        return chessField;
    }
    public boolean isWhitesTurn(){
        return whitesTurn;
    }

    public void setChosenFigure(ChessFigure chosenFigure) {
        this.chosenFigure = chosenFigure;
    }
    public void setChosenCoordinates(Coordinate chosenCoordinates) {
        this.chosenCoordinates = chosenCoordinates;
    }
    public void setWhitesTurn(boolean whitesTurn) {
        this.whitesTurn = whitesTurn;
    }

    public void start(){
        state = GameState.PLAYING;
        initCells();
        setDefaultField();
    }

    private void chooseFigure(Coordinate coordinate){
        if(chessField[coordinate.getRow()][coordinate.getCol()].getFigure()==null){
            return;
        }
        ChessFigure tmp = chessField[coordinate.getRow()][coordinate.getCol()].getFigure();
        if(tmp.isWhite()!=isWhitesTurn()){
            return;
        }
        setChosenFigure(tmp);
        setChosenCoordinates(coordinate);
        List<List<Coordinate>> moves = chosenFigure.findPossibleMoves(this, coordinate);
        chosenFigure.setPossibleMoves(moves);
        cleanPossMoves();
        int i = 0;
        for(List<Coordinate> list : moves){
            CellState current = i%2==0 ? CellState.CAPTURE : CellState.REACH;
            for(Coordinate c : list){
                chessField[c.getRow()][c.getCol()].setState(current);
            }
            i++;
        }
    }

    public void move(Coordinate destination){
        if(getState()!=GameState.PLAYING) return;
        if(chosenFigure == null || !isAccessable(destination)){
            chooseFigure(destination);
            return;
        }
        cleanPossMoves();
        int destRow = destination.getRow();
        int destCol = destination.getCol();
        ChessFigure reached = chessField[destRow][destCol].getFigure();
        chessField[destRow][destCol].setFigure(chosenFigure);
        chessField[chosenCoordinates.getRow()][chosenCoordinates.getCol()].setFigure(null);

        //if king was killed
        if(reached!=null && reached.getID().matches("King")){
            state = GameState.ENDED;
        }

        //if pawn reached the end of the board
        if((destRow==0 || destRow==chessField.length-1) && chosenFigure.getID().matches("Pawn")){
            chessField[destRow][destCol].setFigure(new Queen(isWhitesTurn()));
        }

        setChosenFigure(null);
        setChosenCoordinates(null);
        setWhitesTurn(!isWhitesTurn());
    }

    private boolean isAccessable(Coordinate coordinate){
        CellState state = chessField[coordinate.getRow()][coordinate.getCol()].getState();
        return state==CellState.REACH || state==CellState.CAPTURE;
    }

    private void cleanPossMoves(){
        for(int i = 0; i < chessField.length; i++){
            for(int j = 0; j < chessField[0].length; j++){
                chessField[i][j].setState(CellState.NONE);
            }
        }
    }

    private void initCells(){
        for(int i = 0; i < chessField.length; i++){
            for(int j = 0; j < chessField[0].length; j++){
                chessField[i][j] = new ChessCell(null);
            }
        }
    }
    private void setDefaultField(){
        //placing black side
        chessField[0][5] = new ChessCell(new King(false));
        chessField[0][4] = new ChessCell(new Giraffe(false));
        chessField[0][3] = new ChessCell(new Vizier(false));
        chessField[0][6] = new ChessCell(new Queen(false));
        chessField[0][0] = new ChessCell(new Rook(false));
        chessField[0][9] = new ChessCell(new Rook(false));
        chessField[0][1] = new ChessCell(new Horse(false));
        chessField[0][8] = new ChessCell(new Horse(false));
        chessField[0][2] = new ChessCell(new Bishop(false));
        chessField[0][7] = new ChessCell(new Bishop(false));
        chessField[1][4] = new ChessCell(new Chancellor(false));
        chessField[1][5] = new ChessCell(new Chancellor(false));
        chessField[1][0] = new ChessCell(new Pawn(false));
        chessField[1][1] = new ChessCell(new Pawn(false));
        chessField[1][2] = new ChessCell(new Pawn(false));
        chessField[1][3] = new ChessCell(new Pawn(false));
        chessField[2][4] = new ChessCell(new Pawn(false));
        chessField[2][5] = new ChessCell(new Pawn(false));
        chessField[1][6] = new ChessCell(new Pawn(false));
        chessField[1][7] = new ChessCell(new Pawn(false));
        chessField[1][8] = new ChessCell(new Pawn(false));
        chessField[1][9] = new ChessCell(new Pawn(false));

        //placing white side
        chessField[9][4] = new ChessCell(new King(true));
        chessField[9][5] = new ChessCell(new Giraffe(true));
        chessField[9][6] = new ChessCell(new Vizier(true));
        chessField[9][3] = new ChessCell(new Queen(true));
        chessField[9][0] = new ChessCell(new Rook(true));
        chessField[9][9] = new ChessCell(new Rook(true));
        chessField[9][1] = new ChessCell(new Horse(true));
        chessField[9][8] = new ChessCell(new Horse(true));
        chessField[9][2] = new ChessCell(new Bishop(true));
        chessField[9][7] = new ChessCell(new Bishop(true));
        chessField[8][4] = new ChessCell(new Chancellor(true));
        chessField[8][5] = new ChessCell(new Chancellor(true));
        chessField[8][0] = new ChessCell(new Pawn(true));
        chessField[8][1] = new ChessCell(new Pawn(true));
        chessField[8][2] = new ChessCell(new Pawn(true));
        chessField[8][3] = new ChessCell(new Pawn(true));
        chessField[7][4] = new ChessCell(new Pawn(true));
        chessField[7][5] = new ChessCell(new Pawn(true));
        chessField[8][6] = new ChessCell(new Pawn(true));
        chessField[8][7] = new ChessCell(new Pawn(true));
        chessField[8][8] = new ChessCell(new Pawn(true));
        chessField[8][9] = new ChessCell(new Pawn(true));
    }
}
