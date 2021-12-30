package com.company.figures;

import com.company.wayGenerators.BishopWayGenerator;

public class Bishop extends ChessFigure {

    public Bishop(boolean isWhite) {
        super(isWhite, new BishopWayGenerator());
    }

    @Override
    public String getID() {
        return "Bishop";
    }
}
