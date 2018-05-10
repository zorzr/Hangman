package console;

import hangman.Player;
import hangman.Game;

import java.io.*;

/**
 * Manage a player playing with the terminal.
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class RemotePlayer extends Player {
    BufferedReader in;
    PrintWriter out;

    /**
     * Constructor.
     */
    public RemotePlayer(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void update(Game game) {
        switch(game.getResult()) {
            case FAILED:
                printBanner("Hai perso!  La parola da indovinare era '" +
                        game.getSecretWord() + "'");
                break;
            case SOLVED:
                printBanner("Hai indovinato!   (" + game.getSecretWord() + ")");
                break;
            case OPEN:
                int rem = Game.MAX_FAILED_ATTEMPTS - game.countFailedAttempts();
                out.print("\n" + rem + " tentativi rimasti\n");
                out.println(this.gameRepresentation(game));
                out.println(game.getKnownLetters());
                break;
        }
    }

    private void printBanner(String message) {
        String banner = "\n";
        for (int i = 0; i < 80; i++)
            banner += "*";
        banner += "\n***  " + message + "\n";
        for (int i = 0; i < 80; i++)
            banner += "*";

        out.println(banner);
    }

    /**
     * Ask the user to guess a letter.
     *
     * @param game
     * @return
     */
    @Override
    public char chooseLetter(Game game) {
        for (;;) {
            out.println("Inserisci una lettera: ");
            String line;
            try {
                line = in.readLine().trim();
            } catch (IOException e) {
                line = "";
            }
            if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
                return line.charAt(0);
            } else {
                out.println("Lettera non valida.");
            }
        }
    }
}

