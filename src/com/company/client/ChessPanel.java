package com.company.client;

import com.company.chessTools.CellState;
import com.company.chessTools.ChessCell;
import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessPanel extends JPanel implements MouseListener {
    public static final int CELL_SIZE = 60;

    ChessGame game;
    private Coordinate currMove = null;

    public ChessPanel(){
        game = new ChessGame();
        game.start();
    }

    public ChessPanel (ChessGame chessGame) {
        game = chessGame;
        game.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintField(g, game);
        paintCoords(g);
    }

    private void paintField(Graphics g, ChessGame game){
        ChessCell[][] chessField = game.getChessField();
        for(int i = 0; i < chessField.length; i++){
            for(int j = 0; j < chessField[0].length; j++){
                Color color;
                if((i+j)%2==0){
                    color = new Color(255, 206, 158);
                } else {
                    color = new Color(209, 139, 71);
                }
                g.setColor(color);
                g.fillPolygon(new int[]{(j+1)*CELL_SIZE, (j+1)*CELL_SIZE, (j+2)*CELL_SIZE, (j+2)*CELL_SIZE},
                        new int[]{(i+1)*CELL_SIZE, (i+2)*CELL_SIZE, (i+2)*CELL_SIZE, (i+1)*CELL_SIZE}, 4);

                if(chessField[i][j]!=null){
                    if(chessField[i][j].getState() == CellState.REACH){
                        g.setColor(Color.YELLOW);
                        g.fillOval((j+1)*CELL_SIZE+15, (i+1)*CELL_SIZE+15, CELL_SIZE/2, CELL_SIZE/2);
                    } else if(chessField[i][j].getState()==CellState.CAPTURE){
                        g.setColor(Color.RED);
                        g.fillOval((j+1)*CELL_SIZE, (i+1)*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }

                if(chessField[i][j].getFigure()!=null){
                    g.drawImage(IconManager.getIcon(chessField[i][j].getFigure()), (j+1)*CELL_SIZE+5, (i+1)*CELL_SIZE+5, null);
                }
            }
        }
    }

    private void paintCoords(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, CELL_SIZE/2));
        String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        int j = 10;
        for(int i = 0; i < 10; i++){
            g.drawString(String.valueOf(j), CELL_SIZE/3, (i+1)*CELL_SIZE+CELL_SIZE/4*3);
            g.drawString(String.valueOf(j), 11*CELL_SIZE+CELL_SIZE/2, (i+1)*CELL_SIZE+CELL_SIZE/2);
            g.drawString(letters[i], (i+1)*CELL_SIZE+CELL_SIZE/2, CELL_SIZE/4*3);
            g.drawString(letters[i], (i+1)*CELL_SIZE+CELL_SIZE/2, 11*CELL_SIZE+CELL_SIZE/4*3);
            j--;
        }
    }

    public Coordinate getCurrMove() {
        return currMove;
    }

    public void setCurrMove(Coordinate currMove) {
        this.currMove = currMove;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - CELL_SIZE/2;
        x /= CELL_SIZE;
        x--;
        y /= CELL_SIZE;
        y--;
        game.move(new Coordinate(y, x));
        currMove = new Coordinate(y, x);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
