package GUI;
//default libraries

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
//user-defined libraries
import Entities.Student;
import FileManagement.StudentManager;

public class StudentRegGUI implements ActionListener {
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

    //constructor
    public StudentRegGUI() {
        frame.setSize(1200, 600);
        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Search by Sl"));
        txtSearch = new JTextField(8);
        frame.add(txtSearch);
        search = new JButton("Search");
        frame.add(search);
        search.addActionListener(this);


        frame.add(new JLabel("Id"));
        txtId = new JTextField(6);
        frame.add(txtId);
        frame.add(new JLabel("Name"));
        txtName = new JTextField(20);
        frame.add(txtName);
        frame.add(new JLabel("SId"));
        txtSId = new JTextField(8);
        frame.add(txtSId);
        frame.add(new JLabel("Dob"));
        txtDob = new JTextField(8);
        frame.add(txtDob);
        register = new JButton("Register");
        frame.add(register);
        register.addActionListener(this);
        update = new JButton("Update");
        frame.add(update);
        update.addActionListener(this);
        delete = new JButton("delete");
        frame.add(delete);
        delete.addActionListener(this);

        //table
        tmodel = new DefaultTableModel();
        tmodel.addColumn("SlNo");
        tmodel.addColumn("Name");
        tmodel.addColumn("SId");
        tmodel.addColumn("Dob");

        JTable table = new JTable(tmodel);
        JScrollPane jspTable = new JScrollPane(table);
        frame.add(jspTable);

        loadData();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //loadData function
    private void loadData() {
        StudentManager studentManager = new StudentManager();
        Student[] students = studentManager.getAllStudents();
        for (Student student : students) {
            Object[] row = new Object[]{student.getSl(), student.getName(), student.getSId(), student.getDob()};
            tmodel.addRow(row);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            String sl = txtId.getText();
            String name = txtName.getText();
            String sid = txtSId.getText();
            String dob = txtDob.getText();
            Object[] row = new Object[]{sl, name, sid, dob};
            tmodel.addRow(row);

            Student s = new Student(sl, name, sid, dob);
            StudentManager sm = new StudentManager();
            sm.writeStudent(s, true);
            JOptionPane.showMessageDialog(null, "Student Inserted");
        } else if (e.getSource() == search) {
            String sl = txtSearch.getText();
            StudentManager sm = new StudentManager();
            JOptionPane.showMessageDialog(null, "Search");
        } else if (e.getSource() == delete) {
            JOptionPane.showMessageDialog(null, "Deleted");
        } else if (e.getSource() == update) {
            JOptionPane.showMessageDialog(null, "Updated");
        }

    }
}