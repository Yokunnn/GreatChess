package com.company.figures;

import com.company.wayGenerators.PawnWayGenerator;

public class Pawn extends ChessFigure {

    public Pawn(boolean isWhite) {
        super(isWhite, new PawnWayGenerator());
    }

    @Override
    public String getID() {
        return "Pawn";
    }
}
