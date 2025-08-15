import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame {

    private Container c;
    private JLabel fnLabel, lnLabel, phoneLabel, gpaLabel, titleLabel;
    private JTextField fnTf, lnTf, phoneTf, gpaTf;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTable table;
    private DefaultTableModel model;

    Student() {
        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Student Table");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.black);

        Font font = new Font("Arial", Font.BOLD, 16);

        // Title
        titleLabel = new JLabel("Student Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(280, 10, 300, 40);
        c.add(titleLabel);

        // First Name
        fnLabel = new JLabel("First Name:");
        fnLabel.setBounds(10, 80, 140, 30);
        fnLabel.setFont(font);
        fnLabel.setForeground(Color.WHITE);
        c.add(fnLabel);

        fnTf = new JTextField();
        fnTf.setBounds(150, 80, 200, 30);
        fnTf.setFont(font);
        c.add(fnTf);

        addButton = new JButton("Add");
        addButton.setBounds(400, 80, 100, 30);
        addButton.setFont(font);
        c.add(addButton);

        // Last Name
        lnLabel = new JLabel("Last Name:");
        lnLabel.setBounds(10, 130, 140, 30);
        lnLabel.setFont(font);
        lnLabel.setForeground(Color.WHITE);
        c.add(lnLabel);

        lnTf = new JTextField();
        lnTf.setBounds(150, 130, 200, 30);
        lnTf.setFont(font);
        c.add(lnTf);

        updateButton = new JButton("Update");
        updateButton.setBounds(400, 130, 100, 30);
        updateButton.setFont(font);
        c.add(updateButton);

        // Phone
        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(10, 180, 140, 30);
        phoneLabel.setFont(font);
        phoneLabel.setForeground(Color.WHITE);
        c.add(phoneLabel);

        phoneTf = new JTextField();
        phoneTf.setBounds(150, 180, 200, 30);
        phoneTf.setFont(font);
        c.add(phoneTf);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(400, 180, 100, 30);
        deleteButton.setFont(font);
        c.add(deleteButton);

        // GPA
        gpaLabel = new JLabel("GPA:");
        gpaLabel.setBounds(10, 230, 140, 30);
        gpaLabel.setFont(font);
        gpaLabel.setForeground(Color.WHITE);
        c.add(gpaLabel);

        gpaTf = new JTextField();
        gpaTf.setBounds(150, 230, 200, 30);
        gpaTf.setFont(font);
        c.add(gpaTf);

        clearButton = new JButton("Clear");
        clearButton.setBounds(400, 230, 100, 30);
        clearButton.setFont(font);
        c.add(clearButton);

        // Table
        String[] columns = {"First Name", "Last Name", "Phone", "GPA"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 300, 760, 300);
        c.add(scroll);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                fnTf.setText(model.getValueAt(row, 0).toString());
                lnTf.setText(model.getValueAt(row, 1).toString());
                phoneTf.setText(model.getValueAt(row, 2).toString());
                gpaTf.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    // Add student
    private void addStudent() {
        String firstName = fnTf.getText();
        String lastName = lnTf.getText();
        String phone = phoneTf.getText();
        String gpa = gpaTf.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || gpa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        model.addRow(new Object[]{firstName, lastName, phone, gpa});
        clearFields();
    }

    // Update student
    private void updateStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to update!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        model.setValueAt(fnTf.getText(), selectedRow, 0);
        model.setValueAt(lnTf.getText(), selectedRow, 1);
        model.setValueAt(phoneTf.getText(), selectedRow, 2);
        model.setValueAt(gpaTf.getText(), selectedRow, 3);
        clearFields();
    }

    // Delete student
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        model.removeRow(selectedRow);
        clearFields();
    }

    // Clear text fields
    private void clearFields() {
        fnTf.setText("");
        lnTf.setText("");
        phoneTf.setText("");
        gpaTf.setText("");
    }

    public static void main(String[] args) {
        new Student();
    }
}
