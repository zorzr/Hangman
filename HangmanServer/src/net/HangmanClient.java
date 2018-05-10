package net;

import java.net.*;
import java.io.*;

public class HangmanClient {
    public static void main(String[] args) {
        BufferedReader in, stdIn;
        PrintWriter out;

        String ip = "127.0.0.1";
        int port = 80;

        try {
            Socket c = new Socket(ip, port);
            out = new PrintWriter(c.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = in.readLine()) != null) {
                if (!line.equalsIgnoreCase("Inserisci una lettera: "))
                    System.out.println(line);
                else
                    out.println(stdIn.readLine());
            }

        } catch (Exception ignored) {}
    }
}
