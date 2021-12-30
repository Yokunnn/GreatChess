package com.company.client;

import com.company.figures.ChessFigure;

import javax.swing.*;
import java.awt.*;

public class IconManager {
    public static Image getIcon(ChessFigure figure){
        StringBuilder path = new StringBuilder("FigureIcons/");
        if(figure.isWhite()){
            path.append("White");
        } else {
            path.append("Black");
        }
        path.append(figure.getID());
        path.append(".png");
        ImageIcon icon = new ImageIcon(path.toString());
        return icon.getImage();
    }
}
