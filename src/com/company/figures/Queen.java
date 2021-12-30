package com.company.figures;

import com.company.wayGenerators.BishopWayGenerator;
import com.company.wayGenerators.RookWayGenerator;

public class Queen extends ChessFigure {

    public Queen(boolean isWhite) {
        super(isWhite, new RookWayGenerator(), new BishopWayGenerator());
    }

    @Override
    public String getID() {
        return "Queen";
    }
}
