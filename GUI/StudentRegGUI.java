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

public class StudentRegGUI extends JFrame implements ActionListener {
    JTextField txtId;
    JTextField txtName;
    JTextField txtSId;
    JTextField txtDob;
    JTextField txtSearch;

    JButton submit;
    JButton search;
    JButton update;
    JButton delete;
    DefaultTableModel tmodel;

    public StudentRegGUI() {
        setSize(1100, 300);
        setLayout(new FlowLayout());

        /*JLabel idlbl = new JLabel("Id");
        add(idlbl);*/

        add(new JLabel("Search by Sl"));
        txtSearch = new JTextField(8);
        add(txtSearch);
        search = new JButton("Search");
        add(search);
        search.addActionListener(this);


        add(new JLabel("Id"));
        txtId = new JTextField(6);
        add(txtId);
        add(new JLabel("Name"));
        txtName = new JTextField(20);
        add(txtName);
        add(new JLabel("SId"));
        txtSId = new JTextField(8);
        add(txtSId);
        add(new JLabel("Dob"));
        txtDob = new JTextField(8);
        add(txtDob);
        submit = new JButton("Register");
        add(submit);
        submit.addActionListener(this);
        update = new JButton("Update");
        add(update);
        update.addActionListener(this);
        delete = new JButton("delete");
        add(delete);
        delete.addActionListener(this);

        //tables
        tmodel = new DefaultTableModel();
        tmodel.addColumn("SlNo");
        tmodel.addColumn("Name");
        tmodel.addColumn("SId");
        tmodel.addColumn("Dob");


        JTable table = new JTable(tmodel);
        JScrollPane jspTable = new JScrollPane(table);
        add(jspTable);


        loadData();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
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
            JOptionPane.showMessageDialog(null, "Srch");
        } else if (e.getSource() == delete) {
            JOptionPane.showMessageDialog(null, "Delete");
        } else if (e.getSource() == update) {
            JOptionPane.showMessageDialog(null, "Update");
        }

    }

    private void loadData() {
        StudentManager sm = new StudentManager();
        Student[] students = sm.getAllStudents();
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            Object[] row = new Object[]{s.getSl(), s.getName(), s.getSId(), s.getDob()};
            tmodel.addRow(row);
        }
    }
}