package com.company.figures;

import com.company.wayGenerators.BishopWayGenerator;
import com.company.wayGenerators.HorseWayGenerator;
import com.company.wayGenerators.RookWayGenerator;

public class Giraffe extends ChessFigure {

    public Giraffe(boolean isWhite) {
        super(isWhite, new RookWayGenerator(), new HorseWayGenerator(), new BishopWayGenerator());
    }

    @Override
    public String getID() {
        return "Giraffe";
    }
}
