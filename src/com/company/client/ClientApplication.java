package com.company.client;

import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;
import com.company.chessTools.NetworkChessGame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.company.client.ChessPanel.CELL_SIZE;

public class ClientApplication extends JFrame {

    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ChessPanel chessPanel;

    public static void main(String[] args) throws IOException{
        ChessPanel panel = new ChessPanel(new NetworkChessGame("", 0));
        ClientApplication client = new ClientApplication("localhost", 1111, panel);
        client.start();
    }

    public ClientApplication(String host, int port, ChessPanel panel){
        this.host = host;
        this.port = port;
        this.chessPanel = panel;
    }

    private void drawPanel(ChessPanel chessPanel){
        setTitle("GreatChess");
        setSize(CELL_SIZE*13, CELL_SIZE*13);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(chessPanel);
        addMouseListener(chessPanel);
        setVisible(true);
    }

    public void start()throws IOException{
        drawPanel(chessPanel);
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String info;
        while(!socket.isClosed()){
            Coordinate curr = chessPanel.getCurrMove();
            if(curr!=null){
                System.out.println("To server: " + curr.getRow() + " " + curr.getCol());
                out.println(curr.getRow() + " " + curr.getCol());
                chessPanel.setCurrMove(null);

                info = in.readLine();
                System.out.println("From server: " + info);
                String[] parsed = info.split(" ");
                if(parsed[1].matches("win")){
                    socket.close();
                }
            }
        }
    }
}
