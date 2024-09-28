package FileManagement;

import Entities.FootballPlayer;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class PlayerManager {
    String filePath;

    public PlayerManager() {
        filePath = "DataFiles/data.txt";
    }

    public void writePlayer(FootballPlayer footballPlayer, boolean append) {
        File f = new File(filePath);
        try {
            FileWriter writer = new FileWriter(f, append);
            writer.write(footballPlayer.getFileWriteFormat());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public FootballPlayer[] getAllPlayer() {
        File f = new File(filePath);
        FootballPlayer[] footballPlayers = null;
        try {
            Scanner sc = new Scanner(f);
            int count = 0;
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            footballPlayers = new FootballPlayer[count];
            sc.close();

            sc = new Scanner(f);
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                FootballPlayer footballPlayer = new FootballPlayer(data[0], data[1], data[2], data[3]);
                footballPlayers[index++] = footballPlayer;
            }
            sc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return footballPlayers;
    }

    public FootballPlayer searchPlayer(String club) {
        FootballPlayer[] footballPlayers = getAllPlayer();
        for (FootballPlayer footballPlayer : footballPlayers) {
            if (footballPlayer.getClub().equals(club)) {
                return footballPlayer;
            }
        }
        return null;
    }

    public void deletePlayer(String club) {
        FootballPlayer[] footballPlayers = getAllPlayer();
        try {
            FileWriter writer = new FileWriter(filePath, false); // Overwrite file
            for (FootballPlayer footballPlayer : footballPlayers) {
                if (!footballPlayer.getClub().equals(club)) {
                    writer.write(footballPlayer.getFileWriteFormat());
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePlayer(FootballPlayer updatedPlayer) {
        FootballPlayer[] footballPlayers = getAllPlayer();
        try {
            FileWriter writer = new FileWriter(filePath, false); // Overwrite file
            for (FootballPlayer footballPlayer : footballPlayers) {
                if (footballPlayer.getClub().equals(updatedPlayer.getClub())) {
                    writer.write(updatedPlayer.getFileWriteFormat());
                } else {
                    writer.write(footballPlayer.getFileWriteFormat());
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
