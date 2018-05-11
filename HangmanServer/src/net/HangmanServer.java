package net;

import console.RemotePlayer;
import hangman.Hangman;
import hangman.Player;

import java.net.*;
import java.io.*;

public class HangmanServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 1025;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            for (int i = 0; i < 3; i++) {
                Socket s = serverSocket.accept();

                Thread playThread = new PlayThread(s);
                playThread.start();
            }
        } catch (IOException ignored) {}
    }
}

class PlayThread extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    PlayThread (Socket s) throws IOException {
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream(), true);
        socket = s;
    }

    @Override
    public void run() {
        Hangman game = new Hangman();
        Player player = new RemotePlayer(in, out);
        game.playGame(player);

        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
