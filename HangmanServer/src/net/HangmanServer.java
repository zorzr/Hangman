package net;

import console.LocalPlayer;
import console.RemotePlayer;
import hangman.Hangman;
import hangman.Player;

import java.net.*;
import java.io.*;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 1025;

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            Socket s = serverSocket.accept();

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            Hangman game = new Hangman();
            Player player = new RemotePlayer(in, out);
            game.playGame(player);
        } catch (Exception ignored) {}
    }
    
}
