package com.company.figures;

import com.company.wayGenerators.HorseWayGenerator;

public class Horse extends ChessFigure {

    public Horse(boolean isWhite) {
        super(isWhite, new HorseWayGenerator());
    }

    @Override
    public String getID() {
        return "Horse";
    }
}
