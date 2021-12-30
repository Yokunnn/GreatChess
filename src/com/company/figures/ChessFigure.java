package com.company.figures;

import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;
import com.company.wayGenerators.WayGenerator;

import java.util.ArrayList;
import java.util.List;

/*
    TODO: сделать классы генераторы путей и передавать в конструктор каждого ребенка нужный
 */

public abstract class ChessFigure {
    private boolean isWhite;
    private List<List<Coordinate>> possibleMoves;
    private List<WayGenerator> generators = new ArrayList<>();

    public ChessFigure(boolean isWhite, WayGenerator...generators){
        this.isWhite = isWhite;
        for(WayGenerator g : generators){
            this.generators.add(g);
        }
    }

    public boolean isWhite() {
        return isWhite;
    }

    public List<List<Coordinate>> findPossibleMoves(ChessGame game, Coordinate curr){
        List<List<Coordinate>> moves = new ArrayList<>();
        for(WayGenerator g : generators){
            moves.addAll(g.getPossibleMoves(game, curr));
        }
        return moves;
    }
    public abstract String getID();

    public List<List<Coordinate>> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<List<Coordinate>> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
