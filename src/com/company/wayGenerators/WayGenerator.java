package com.company.wayGenerators;

import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;

import java.util.List;

public abstract class WayGenerator {
    public abstract List<List<Coordinate>> getPossibleMoves(ChessGame game, Coordinate curr);
}
