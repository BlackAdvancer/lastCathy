import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Serial")
public class ManageEmployee extends JFrame {

    private final int WIDTH = 400, HEIGHT = 700;
    public ManageEmployee() {
        setSize(WIDTH, HEIGHT);
        setTitle("Task: Manage Employee");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        draw();
    }

    private JPanel processPanel;
    private JScrollPane tablePanel;
    private buttonHandler handler;
    public void draw() {
        setLayout(new BorderLayout());
        handler = new buttonHandler();
        drawProcessPanel();
        add(processPanel, BorderLayout.SOUTH);
    }

    private JButton changeWage;
    private JTextField tf_wage, tf_employeeID;
    private JLabel label_wage, label_employeeID;
    private void drawProcessPanel() {
        processPanel = new JPanel();
        changeWage = new JButton("Change Wage");
        changeWage.addActionListener(handler);
        tf_wage = new JTextField(13);
        tf_employeeID = new JTextField(13);
        label_wage = new JLabel("Wage", SwingConstants.LEFT);
        label_employeeID = new JLabel("Employee ID", SwingConstants.LEFT);
        JPanel labels = new JPanel();
        GridLayout layout = new GridLayout(2, 1);
        layout.setVgap(5);
        labels.setLayout(layout);
        labels.add(label_employeeID);
        labels.add(label_wage);
        processPanel.add(labels, BorderLayout.WEST);
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(2, 1));
        fields.add(tf_employeeID);
        fields.add(tf_wage);
        processPanel.add(fields, BorderLayout.CENTER);
        processPanel.add(changeWage, BorderLayout.WEST);
    }


    private class buttonHandler implements ActionListener {
        @Override
        // todo
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == changeWage) {
//                if ()
            }
        }
    }
}
