package com.company.server;

import com.company.chessTools.ChessGame;
import com.company.chessTools.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChessSession implements Runnable{

    private ChessGame game;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChessSession(Socket socket){
        game = new ChessGame();
        game.start();
        this.socket = socket;

    }

    @Override
    public void run() {
        while (game.getState()!= ChessGame.GameState.ENDED){
            serverMove();
        }
        System.out.println("Game ended");
    }

    public void serverMove(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String command;
            while((command = in.readLine())==null){
            }
            System.out.println("From client: " + command);
            String[] coordinate = command.split(" ");
            game.move(new Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1])));
            String answer = game.isWhitesTurn() ? "Whites turn" : "Blacks turn";
            if(game.getState()== ChessGame.GameState.ENDED){
                answer = game.isWhitesTurn() ? "Blacks win" : "Whites win";
            }
            System.out.println("To client: " + answer);
            out.println(answer);
        } catch (IOException e) {
            throw new IllegalStateException("No connection with client", e);
        }
    }
}
