package com.company.figures;

import com.company.wayGenerators.RookWayGenerator;

public class Rook extends ChessFigure {

    public Rook(boolean isWhite) {
        super(isWhite, new RookWayGenerator());
    }

    @Override
    public String getID() {
        return "Rook";
    }
}
