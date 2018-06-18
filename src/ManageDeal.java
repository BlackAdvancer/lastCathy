import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("Serial")
public class ManageDeal extends JFrame {
    private final int WIDTH = 400, HEIGHT = 700;

    public ManageDeal() {
        setSize(WIDTH, HEIGHT);
        setTitle("Task: Manage Items");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        draw();
    }

    private JPanel buttonsPanel;
    private buttonHandler handler;
    public void draw() {
        setLayout(new BorderLayout());
        handler = new buttonHandler();
        drawButtonPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    JButton addNewDeal, modifyDeal;
    private void drawButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));
        addNewDeal = new JButton("Add or Delete Deal");
        addNewDeal.addActionListener(handler);
        modifyDeal = new JButton("Modify Deal Items");
        modifyDeal.addActionListener(handler);
        buttonsPanel.add(addNewDeal);
        buttonsPanel.add(modifyDeal);
    }

    AddDeleteModifyDeal addDeleteDealFrame;
    modifyItems modifyDealFrame;
    private class buttonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == addNewDeal) {
                addDeleteDealFrame = new AddDeleteModifyDeal();
                addDeleteDealFrame.setVisible(true);
            } else if (source == modifyDeal) {
                modifyDealFrame = new modifyItems();
                modifyDealFrame.setVisible(true);
            }
        }
    }

    private class AddDeleteModifyDeal extends JFrame {
        private final int WIDTH = 300, HEIGHT = 130;
        public AddDeleteModifyDeal() {
            setSize(WIDTH, HEIGHT);
            setTitle("Task: Add/Delete/Modify Deal");
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            draw();
        }

        addHandler handler;
        JPanel labelPanel, fieldPanel, buttonsPanel;
        public void draw() {
            handler = new addHandler();
            setLayout(new BorderLayout());
            drawLabelPanel();
            add(labelPanel, BorderLayout.WEST);
            drawFieldPanel();
            add(fieldPanel, BorderLayout.CENTER);
            drawButtonsPanel();
            add(buttonsPanel, BorderLayout.SOUTH);
        }

        JButton add, delete, modify;
        private void drawButtonsPanel() {
            buttonsPanel = new JPanel(new GridLayout(1, 3));
            add = new JButton("Add");
            add.addActionListener(handler);
            delete = new JButton("Delete");
            delete.addActionListener(handler);
            modify = new JButton("Modify");
            modify.addActionListener(handler);
            buttonsPanel.add(add);
            buttonsPanel.add(delete);
            buttonsPanel.add(modify);
        }

        JLabel label_dealName, label_startTime, label_endTime;
        private void drawLabelPanel() {
            labelPanel = new JPanel(new GridLayout(3, 1));
            label_dealName = new JLabel("name");
            label_startTime = new JLabel("start time");
            label_endTime = new JLabel("end tiem");
            labelPanel.add(label_dealName);
            labelPanel.add(label_startTime);
            labelPanel.add(label_endTime);
        }


        JTextField tf_dealName, tf_startTime, tf_endTime;
        boolean startModified, endModified;
        private void drawFieldPanel() {
            fieldPanel = new JPanel(new GridLayout(3, 1));
            tf_dealName = new JTextField();
            tf_startTime = new JTextField("yr mo da");
            tf_startTime.setForeground(Color.GRAY);
            startModified = false;
            tf_startTime.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (startModified)
                        return;
                    tf_startTime.setText("");
                    tf_startTime.setForeground(Color.BLACK);
                    startModified = true;
                }
            });
            tf_endTime = new JTextField("yr mo da");
            tf_endTime.setForeground(Color.GRAY);
            endModified = false;
            tf_endTime.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (endModified)
                        return;
                    tf_endTime.setText("");
                    tf_endTime.setForeground(Color.BLACK);
                    endModified = true;
                }
            });
            fieldPanel.add(tf_dealName);
            fieldPanel.add(tf_startTime);
            fieldPanel.add(tf_endTime);
        }

        private class addHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                String start = tf_startTime.getText();
                String end = tf_endTime.getText();
                if (source == add) {
                    if (!Constraints.ifCorrectDateFormat(start) || !Constraints.ifCorrectDateFormat(end)) {
                        NotificationUI error = new NotificationUI("Wrong Date Format");
                        error.setVisible(true);
                    } else {
                        // todo
                    }
                } if (source == delete) {

                } if (source == modify) {
                    if (!Constraints.ifCorrectDateFormat(start) || !Constraints.ifCorrectDateFormat(end)) {
                        NotificationUI error = new NotificationUI("Wrong Date Format");
                        error.setVisible(true);
                    } else {
                        // todo
                    }
                }
            }
        }
    }

    private class modifyItems extends JFrame {
        private final int WIDTH = 300, HEIGHT = 130;
        public modifyItems() {
            setSize(WIDTH, HEIGHT);
            setTitle("Task: Modify Deal Items");
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            draw();
        }

        addHandler handler;
        JPanel labelPanel, fieldPanel, buttonsPanel;
        public void draw() {
            handler = new addHandler();
            setLayout(new BorderLayout());
            drawLabelPanel();
            add(labelPanel, BorderLayout.WEST);
            drawFieldPanel();
            add(fieldPanel, BorderLayout.CENTER);
            drawButtonsPanel();
            add(buttonsPanel, BorderLayout.SOUTH);
        }

        JButton add, delete, modify;
        private void drawButtonsPanel() {
            buttonsPanel = new JPanel(new GridLayout(1, 3));
            add = new JButton("Add");
            add.addActionListener(handler);
            delete = new JButton("Delete");
            delete.addActionListener(handler);
            modify = new JButton("Modify");
            modify.addActionListener(handler);
            buttonsPanel.add(add);
            buttonsPanel.add(delete);
            buttonsPanel.add(modify);
        }

        JLabel label_itemID, label_dealName, label_discount;
        private void drawLabelPanel() {
            labelPanel = new JPanel(new GridLayout(3, 1));
            label_itemID = new JLabel("itemID");
            label_dealName = new JLabel("deal name");
            label_discount = new JLabel("discount");
            labelPanel.add(label_itemID);
            labelPanel.add(label_dealName);
            labelPanel.add(label_discount);
        }


        JTextField tf_itemID, tf_dealName, tf_discount;
        private void drawFieldPanel() {
            fieldPanel = new JPanel(new GridLayout(3, 1));
            tf_itemID = new JTextField();
            tf_dealName = new JTextField();
            tf_discount = new JTextField();
            fieldPanel.add(tf_itemID);
            fieldPanel.add(tf_dealName);
            fieldPanel.add(tf_discount);
        }

        private class addHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == add) {

                } if (source == delete) {

                } if (source == modify) {

                }
            }
        }
    }
}
