package com.company.figures;

import com.company.wayGenerators.BishopWayGenerator;
import com.company.wayGenerators.HorseWayGenerator;

public class Vizier extends ChessFigure{

    public Vizier(boolean isWhite){
        super(isWhite, new HorseWayGenerator(), new BishopWayGenerator());
    }

    @Override
    public String getID() {
        return "Vizier";
    }
}
