package GUI;
//default libraries
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import Entities.Player;
import FileManagement.PlayerManager;

public class PlayerRegGUI implements ActionListener {
    //frame
    JFrame frame = new JFrame("Football Player");
    //text-fields
    JTextField txtId;
    JTextField txtName;
    JTextField txtSId;
    JTextField txtDob;
    JTextField txtSearch;
    //buttons
    JButton register;
    JButton search;
    JButton update;
    JButton delete;
    //defaultTableModel
    DefaultTableModel tmodel;
    JTable table;

    //selected row index for delete/update
    int selectedRow = -1;

    //constructor
    public PlayerRegGUI() {
        frame.setSize(1300, 600);
        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Search by Name"));
        txtSearch = new JTextField(8);
        frame.add(txtSearch);
        search = new JButton("Search");
        frame.add(search);
        search.addActionListener(this);

        frame.add(new JLabel("Club"));
        txtId = new JTextField(6);
        frame.add(txtId);
        frame.add(new JLabel("Name"));
        txtName = new JTextField(20);
        frame.add(txtName);
        frame.add(new JLabel("Jersey no"));
        txtSId = new JTextField(8);
        frame.add(txtSId);
        frame.add(new JLabel("Playing position"));
        txtDob = new JTextField(8);
        frame.add(txtDob);

        register = new JButton("Register");
        frame.add(register);
        register.addActionListener(this);

        update = new JButton("Update");
        frame.add(update);
        update.addActionListener(this);

        delete = new JButton("Delete");
        frame.add(delete);
        delete.addActionListener(this);

        //table
        tmodel = new DefaultTableModel();
        tmodel.addColumn("Club");
        tmodel.addColumn("Name");
        tmodel.addColumn("Jersey no");
        tmodel.addColumn("Playing position");

        table = new JTable(tmodel);
        JScrollPane jspTable = new JScrollPane(table);
        frame.add(jspTable);

        // Add row selection listener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Make sure row selection is not adjusting
                if (!e.getValueIsAdjusting()) {
                    selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        txtId.setText(tmodel.getValueAt(selectedRow, 0).toString());
                        txtName.setText(tmodel.getValueAt(selectedRow, 1).toString());
                        txtSId.setText(tmodel.getValueAt(selectedRow, 2).toString());
                        txtDob.setText(tmodel.getValueAt(selectedRow, 3).toString());
                    }
                }
            }
        });

        loadData();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //loadData function
    private void loadData() {
        PlayerManager playerManager = new PlayerManager();
        Player[] players = playerManager.getAllPlayer();
        for (Player player : players) {
            Object[] row = new Object[]{player.getClub(), player.getName(), player.getSId(), player.getPlayingPosition()};
            tmodel.addRow(row);
        }
    }

    public void actionPerformed(ActionEvent e) {
        PlayerManager sm = new PlayerManager();
        if (e.getSource() == register) {
            String club = txtId.getText();
            String name = txtName.getText();
            String sid = txtSId.getText();
            String dob = txtDob.getText();

            // Add the player to the table and save it to the file
            Object[] row = new Object[]{club, name, sid, dob};
            tmodel.addRow(row);

            Player player = new Player(club, name, sid, dob);
            sm.writePlayer(player, true); // true for appending
            JOptionPane.showMessageDialog(null, "Player Registered");
        } else if (e.getSource() == search) {
            String searchName = txtSearch.getText();
            boolean found = false;

            // Loop through the table and find the player by name
            for (int i = 0; i < tmodel.getRowCount(); i++) {
                String name = tmodel.getValueAt(i, 1).toString();
                if (name.equalsIgnoreCase(searchName)) {
                    // Select the found row
                    table.setRowSelectionInterval(i, i);
                    // Scroll to the found row (optional, if needed)
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
                String club = txtId.getText();
                String name = txtName.getText();
                String sid = txtSId.getText();
                String dob = txtDob.getText();

                // Update the player in the table
                tmodel.setValueAt(club, selectedRow, 0);
                tmodel.setValueAt(name, selectedRow, 1);
                tmodel.setValueAt(sid, selectedRow, 2);
                tmodel.setValueAt(dob, selectedRow, 3);

                // Update the player in the file
                Player player = new Player(club, name, sid, dob);
                sm.updatePlayer(player);
                JOptionPane.showMessageDialog(null, "Player Updated");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a player to update");
            }
        } else if (e.getSource() == delete) {
            if (selectedRow >= 0) {
                String club = txtId.getText();
                sm.deletePlayer(club);
                tmodel.removeRow(selectedRow); // Remove the row from the table
                selectedRow = -1; // Reset selected row
                JOptionPane.showMessageDialog(null, "Player Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a player to delete");
            }
        }
    }
}
