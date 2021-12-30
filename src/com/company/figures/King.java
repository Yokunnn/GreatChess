package com.company.figures;

import com.company.wayGenerators.KingWayGenerator;

public class King extends ChessFigure {

    public King(boolean isWhite) {
        super(isWhite, new KingWayGenerator());
    }

    @Override
    public String getID() {
        return "King";
    }
}
