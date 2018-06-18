import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class ManagerUI extends JFrame {

    Manager manager;
    private final int WIDTH = 500, HEIGHT = 90;
    public int managerID;
    ManagerUI() {
        setSize(WIDTH, HEIGHT);
        setTitle("ManagerUI");
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        draw();
        this.manager = new Manager();
        LoginUI loginUI = new LoginUI(this, "Manager", manager);
        loginUI.setVisible(true);
        loginUI.setResizable(false);
    }

    private JPanel buttonsPanel, northPanel;
    private buttonHandler handler;
    public void draw() {
        setLayout(new BorderLayout());
        handler = new buttonHandler();
        drawNorthPanel();
        add(northPanel, BorderLayout.NORTH);
        drawButtons();
        add(buttonsPanel, BorderLayout.CENTER);
    }

    public void repaint() {
        setVisible(true);
        displayID.setText(managerID+"");
    }

    JButton manageItem, manageEmployee, manageDeal;
    private void drawButtons() {
        buttonsPanel = new JPanel();
        manageItem = new JButton("Manage Items");
        manageItem.addActionListener(handler);
        manageEmployee = new JButton("Manage Employee");
        manageEmployee.addActionListener(handler);
        manageDeal = new JButton("Manage Deal");
        manageDeal.addActionListener(handler);
        buttonsPanel.setLayout(new GridLayout(1, 3));
        buttonsPanel.add(manageItem);
        buttonsPanel.add(manageEmployee);
        buttonsPanel.add(manageDeal);
    }

    private JLabel friendly, displayID;
    private JButton logOut;
    private void drawNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 3));
        friendly = new JLabel("Employee ID: ");
        northPanel.add(friendly);
        displayID = new JLabel(managerID+"");
        displayID.setForeground(Color.BLUE);
        northPanel.add(displayID);
        logOut = new JButton("Log Out");
        logOut.addActionListener(handler);
        northPanel.add(logOut);
    }

    private ManageEmployee employees;
    private ManageItem items;
    private ManageDeal deals;
    private class buttonHandler implements ActionListener {
        @Override
        // todo
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == manageItem) {
                if (items == null)
                    manageItem();
                else {
                    items.setVisible(false);
                    items.dispose();
                    items = null;
                }
            } else if (source == manageEmployee) {
                if (employees == null)
                    manageEmployee();
                else {
                    employees.setVisible(false);
                    employees.dispose();
                    employees = null;
                }
            } else if (source == manageDeal) {
                if (deals == null)
                    manageDeal();
                else {
                    deals.setVisible(false);
                    deals.dispose();
                    deals = null;
                }
            } else if (source == logOut) {
                logOut();
            }
        }
    }

    private void logOut() {
        LoginUI loginUI = new LoginUI(this, "Employee", manager);
        loginUI.setVisible(true);
        setVisible(false);
    }

    private void manageEmployee() {
        employees = new ManageEmployee();
        employees.setVisible(true);
    }

    private void manageDeal() {
        deals = new ManageDeal();
        deals.setVisible(true);
    }

    private void manageItem() {
        items = new ManageItem();
        items.setVisible(true);
    }
}
