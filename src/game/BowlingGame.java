package game;

import player.Player;
import score.Score;
import score.ScoreEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BowlingGame {

    public static void startGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("How many players to add to current game");
        int numberOfPlayers = in.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(new Score()));
        }
        while(!gameOver(players)) {
            for (int i = 0; i < numberOfPlayers; i++) {
                while(true) {
                    System.out.println("How many points did player " + (i + 1) + " score?");
                    int points = in.nextInt();
                    players.get(i).bowl(points);
                    System.out.print("Player " + ( i +1 )  + " score: ");
                    players.get(i).getScore().displayScoreToConsole();
                    System.out.println();
                    if(players.get(i).isCurrentFrameCompleted()) {
                        break;
                    }
                }
            }
            askToSeeTotalScoreOfPlayers(in, numberOfPlayers, players);
            askToSeeAllScoreOfPlayers(in, numberOfPlayers, players);
        }
    }

    private static void askToSeeTotalScoreOfPlayers(Scanner in, int numberOfPlayers, List<Player> players) {
        System.out.println("See total score of all players?(y/n)");
        String totalScore = in.next();
        if(totalScore.equals("y")) {
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Player " + (i + 1) + " score: " + players.get(i).getScore().getTotalScore());
            }
        }
    }

    private static void askToSeeAllScoreOfPlayers(Scanner in, int numberOfPlayers, List<Player> players) {
        System.out.println("See all score of all players?(y/n)");
        String AllScore = in.next();
        if(AllScore.equals("y")) {
            for (int i = 0; i < numberOfPlayers; i++) {
                players.get(i).getScore().displayScoreToConsole();
                System.out.println();
            }
        }
    }

    public static boolean gameOver(List<Player> players) {
        for (Player player : players) {
            if (!player.getScore().gameOver()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        startGame();
    }
}
