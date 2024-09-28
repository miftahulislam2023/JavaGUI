package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import Entities.Player;
import FileManagement.PlayerManager;

public class PlayerRegGUI implements ActionListener {
    JFrame frame = new JFrame("Player Registration");
    // text-fields
    JTextField txtName;
    JTextField txtJerseyNo;
    JTextField txtPlayingPosition;
    JTextField txtSearchByName;
    JComboBox<String> sportDropdown;
    // buttons
    JButton register;
    JButton search;
    JButton update;
    JButton delete;
    JButton showCricketPlayers;
    JButton showFootballPlayers;
    // defaultTableModel
    DefaultTableModel tmodel;
    JTable table;

    // selected row index for delete/update
    int selectedRow = -1;

    public PlayerRegGUI() {
        frame.setSize(1300, 600);
        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Search by Name"));
        txtSearchByName = new JTextField(8);
        frame.add(txtSearchByName);
        search = new JButton("Search");
        frame.add(search);
        search.addActionListener(this);

        // Sport dropdown menu
        frame.add(new JLabel("Sport"));
        String[] sports = {"Cricket", "Football"};
        sportDropdown = new JComboBox<>(sports);
        frame.add(sportDropdown);

        frame.add(new JLabel("Name"));
        txtName = new JTextField(20);
        frame.add(txtName);
        frame.add(new JLabel("Jersey no"));
        txtJerseyNo = new JTextField(8);
        frame.add(txtJerseyNo);
        frame.add(new JLabel("Playing position"));
        txtPlayingPosition = new JTextField(8);
        frame.add(txtPlayingPosition);

        register = new JButton("Register");
        frame.add(register);
        register.addActionListener(this);

        update = new JButton("Update");
        frame.add(update);
        update.addActionListener(this);

        delete = new JButton("Delete");
        frame.add(delete);
        delete.addActionListener(this);

        // Buttons for showing players
        showCricketPlayers = new JButton("Show Cricket Players");
        showFootballPlayers = new JButton("Show Football Players");
        frame.add(showCricketPlayers);
        frame.add(showFootballPlayers);
        showCricketPlayers.addActionListener(this);
        showFootballPlayers.addActionListener(this);

        // Table
        tmodel = new DefaultTableModel();
        tmodel.addColumn("Sport");
        tmodel.addColumn("Name");
        tmodel.addColumn("Jersey no");
        tmodel.addColumn("Playing position");

        table = new JTable(tmodel);
        JScrollPane jspTable = new JScrollPane(table);
        frame.add(jspTable);

        // Add row selection listener to the table
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    sportDropdown.setSelectedItem(tmodel.getValueAt(selectedRow, 0).toString());
                    txtName.setText(tmodel.getValueAt(selectedRow, 1).toString());
                    txtJerseyNo.setText(tmodel.getValueAt(selectedRow, 2).toString());
                    txtPlayingPosition.setText(tmodel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        loadData();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Load data function
    private void loadData() {
        PlayerManager playerManager = new PlayerManager();
        Player[] players = playerManager.getAllPlayer();
        for (Player player : players) {
            Object[] row = new Object[]{player.getSport(), player.getName(), player.getJerseyNo(), player.getPlayingPosition()};
            tmodel.addRow(row);
        }
    }

    // Function to display players based on sport
    private void displayPlayersBySport(String sport) {
        JFrame sportFrame = new JFrame("Players - " + sport);
        DefaultTableModel sportModel = new DefaultTableModel();
        sportModel.addColumn("Name");
        sportModel.addColumn("Jersey No");
        sportModel.addColumn("Playing Position");

        JTable sportTable = new JTable(sportModel);
        JScrollPane scrollPane = new JScrollPane(sportTable);
        sportFrame.add(scrollPane);

        PlayerManager playerManager = new PlayerManager();
        Player[] players = playerManager.getAllPlayer();
        for (Player player : players) {
            if (player.getSport().equals(sport)) {
                sportModel.addRow(new Object[]{player.getName(), player.getJerseyNo(), player.getPlayingPosition()});
            }
        }

        sportFrame.setSize(500, 300);
        sportFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        PlayerManager sm = new PlayerManager();
        if (e.getSource() == register) {
            String sport = sportDropdown.getSelectedItem().toString();
            String name = txtName.getText();
            String jerseyNo = txtJerseyNo.getText();
            String playingPosition = txtPlayingPosition.getText();

            // Add the player to the table and save it to the file
            Object[] row = new Object[]{sport, name, jerseyNo, playingPosition};
            tmodel.addRow(row);

            Player player = new Player(sport, name, jerseyNo, playingPosition);
            sm.writePlayer(player, true); // true for appending
            JOptionPane.showMessageDialog(null, "Player Registered");
        } else if (e.getSource() == search) {
            String searchName = txtSearchByName.getText();
            boolean found = false;

            // Loop through the table and find the player by name
            for (int i = 0; i < tmodel.getRowCount(); i++) {
                String name = tmodel.getValueAt(i, 1).toString();
                if (name.equalsIgnoreCase(searchName)) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Player not found!");
            }
        } else if (e.getSource() == update) {
            if (selectedRow >= 0) {
                String sport = sportDropdown.getSelectedItem().toString();
                String name = txtName.getText();
                String sid = txtJerseyNo.getText();
                String dob = txtPlayingPosition.getText();

                tmodel.setValueAt(sport, selectedRow, 0);
                tmodel.setValueAt(name, selectedRow, 1);
                tmodel.setValueAt(sid, selectedRow, 2);
                tmodel.setValueAt(dob, selectedRow, 3);

                Player player = new Player(sport, name, sid, dob);
                sm.updatePlayer(player);
                JOptionPane.showMessageDialog(null, "Player Updated");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a player to update");
            }
        } else if (e.getSource() == delete) {
            if (selectedRow >= 0) {
                String sport = sportDropdown.getSelectedItem().toString();
                sm.deletePlayer(sport);
                tmodel.removeRow(selectedRow);
                selectedRow = -1;
                JOptionPane.showMessageDialog(null, "Player Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a player to delete");
            }
        } else if (e.getSource() == showCricketPlayers) {
            displayPlayersBySport("Cricket");
        } else if (e.getSource() == showFootballPlayers) {
            displayPlayersBySport("Football");
        }
    }
}
