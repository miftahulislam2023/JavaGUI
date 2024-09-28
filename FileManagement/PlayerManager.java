package FileManagement;

import Entities.Player;

import java.io.*;
import java.util.ArrayList;

public class PlayerManager {

    private final String filePath = "DataFiles/players.txt";

    // Write player to file
    public void writePlayer(Player player, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(player.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update player
    public void updatePlayer(Player updatedPlayer) {
        ArrayList<Player> players = new ArrayList<>();
        // Load all players
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[1].equalsIgnoreCase(updatedPlayer.getName())) {  // Compare by name
                    players.add(new Player(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add updated player
        players.add(updatedPlayer);

        // Rewrite the file with updated data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Player player : players) {
                writer.write(player.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete player
    public void deletePlayer(String name) {
        ArrayList<Player> players = new ArrayList<>();
        // Load all players except the one to delete
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[1].equalsIgnoreCase(name)) {  // Compare by name
                    players.add(new Player(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rewrite the file with remaining players
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Player player : players) {
                writer.write(player.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all players
    public Player[] getAllPlayer() {
        ArrayList<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Player player = new Player(data[0], data[1], data[2], data[3]);
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players.toArray(new Player[0]);
    }
}
