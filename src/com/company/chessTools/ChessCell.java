package com.company.chessTools;

import com.company.figures.ChessFigure;

public class ChessCell {

    private ChessFigure figure = null;
    private CellState state = CellState.NONE;

    public ChessCell(ChessFigure figure){
        this.figure = figure;
    }

    public ChessFigure getFigure() {
        return figure;
    }
    public CellState getState() {
        return state;
    }

    public void setFigure(ChessFigure figure) {
        this.figure = figure;
    }
    public void setState(CellState state) {
        this.state = state;
    }
}
