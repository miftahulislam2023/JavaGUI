package FileManagement;

import Entities.Player;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class PlayerManager {
    String filePath;

    public PlayerManager() {
        filePath = "DataFiles/data.txt";
    }

    public void writePlayer(Player player, boolean append) {
        File f = new File(filePath);
        try {
            FileWriter writer = new FileWriter(f, append);
            writer.write(player.getFileWriteFormat());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Player[] getAllPlayer() {
        File f = new File(filePath);
        Player[] players = null;
        try {
            Scanner sc = new Scanner(f);
            int count = 0;
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            players = new Player[count];
            sc.close();

            sc = new Scanner(f);
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                Player player = new Player(data[0], data[1], data[2], data[3]);
                players[index++] = player;
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return players;
    }

    public Player searchPlayer(String club) {
        Player[] players = getAllPlayer();
        for (Player player : players) {
            if (player.getClub().equals(club)) {
                return player;
            }
        }
        return null;
    }

    public void deletePlayer(String club) {
        Player[] players = getAllPlayer();
        try {
            FileWriter writer = new FileWriter(filePath, false); // Overwrite file
            for (Player player : players) {
                if (!player.getClub().equals(club)) {
                    writer.write(player.getFileWriteFormat());
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePlayer(Player updatedPlayer) {
        Player[] players = getAllPlayer();
        try {
            FileWriter writer = new FileWriter(filePath, false); // Overwrite file
            for (Player player : players) {
                if (player.getClub().equals(updatedPlayer.getClub())) {
                    writer.write(updatedPlayer.getFileWriteFormat());
                } else {
                    writer.write(player.getFileWriteFormat());
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
