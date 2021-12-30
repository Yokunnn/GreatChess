package com.company.figures;

import com.company.wayGenerators.HorseWayGenerator;
import com.company.wayGenerators.RookWayGenerator;

public class Chancellor extends ChessFigure{

    public Chancellor(boolean isWhite) {
        super(isWhite, new HorseWayGenerator(), new RookWayGenerator());
    }

    @Override
    public String getID() {
        return "Chancellor";
    }
}
