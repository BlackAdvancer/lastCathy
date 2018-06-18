import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Serial")
public class ManageItem extends JFrame {
    private final int WIDTH = 400, HEIGHT = 700;

    public ManageItem() {
        setSize(WIDTH, HEIGHT);
        setTitle("Task: Manage Items");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        draw();
    }

    private JPanel buttonsPanel;
    private JScrollPane tablePanel;
    private buttonHandler handler;

    public void draw() {
        setLayout(new BorderLayout());
        handler = new buttonHandler();
        drawButtonPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JButton changeItemStorage, changeItemPrice;

    private void drawButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));
        changeItemStorage = new JButton("Change Item Storage");
        changeItemStorage.addActionListener(handler);
        changeItemPrice = new JButton("Change Item Price");
        changeItemPrice.addActionListener(handler);
        buttonsPanel.add(changeItemStorage);
        buttonsPanel.add(changeItemPrice);
    }


    changeItemPrice changeItemPriceFrame;
    changeItemStorage changeItemStorageFrame;

    private class buttonHandler implements ActionListener {
        @Override
        // todo
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == changeItemPrice) {
                changeItemPrice();
            } else if (source == changeItemStorage) {
                changeItemStorage();
                changeItemStorageFrame.setVisible(true);
            }
        }
    }

    private void changeItemPrice() {
        changeItemPriceFrame = new changeItemPrice();
        changeItemPriceFrame.setVisible(true);
    }

    private void changeItemStorage() {
        changeItemStorageFrame = new changeItemStorage();
    }

    private class changeItemPrice extends JFrame {

        private final int WIDTH = 200, HEIGHT = 100;

        public changeItemPrice() {
            setSize(WIDTH, HEIGHT);
            setTitle("Task: change Item Price");
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            draw();
        }

        JButton setPrice;
        JPanel labelPanel, fieldPanel;
        priceHandler handler;

        public void draw() {
            handler = new priceHandler();
            setLayout(new BorderLayout());
            drawLabelPanel();
            add(labelPanel, BorderLayout.WEST);
            drawFieldPanel();
            add(fieldPanel, BorderLayout.CENTER);
            setPrice = new JButton("Change");
            setPrice.addActionListener(handler);
            add(setPrice, BorderLayout.SOUTH);
        }

        JLabel label_itemNumber, label_price;

        public void drawLabelPanel() {
            labelPanel = new JPanel(new GridLayout(2, 1));
            label_itemNumber = new JLabel("item number");
            label_price = new JLabel("price");
            labelPanel.add(label_itemNumber);
            labelPanel.add(label_price);
        }

        JTextField tf_itemNumber, tf_price;

        public void drawFieldPanel() {
            fieldPanel = new JPanel(new GridLayout(2, 1));
            tf_itemNumber = new JTextField();
            tf_price = new JTextField();
            fieldPanel.add(tf_itemNumber);
            fieldPanel.add(tf_price);
        }

        private class priceHandler implements ActionListener {
            @Override
            // todo
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == setPrice) {

                }
            }
        }
    }

    private class changeItemStorage extends JFrame {

        private final int WIDTH = 200, HEIGHT = 100;
        public changeItemStorage() {
            setSize(WIDTH, HEIGHT);
            setTitle("Task: change Item Storage");
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            draw();
        }

        JButton setStorage;
        JPanel labelPanel, fieldPanel;
        amountHandler handler;
        public void draw() {
            handler = new amountHandler();
            setLayout(new BorderLayout());
            drawLabelPanel();
            add(labelPanel, BorderLayout.WEST);
            drawFieldPanel();
            add(fieldPanel, BorderLayout.CENTER);
            setStorage = new JButton("Change");
            setStorage.addActionListener(handler);
            add(setStorage, BorderLayout.SOUTH);
        }

        JLabel label_itemNumber, label_amount;
        public void drawLabelPanel() {
            labelPanel = new JPanel(new GridLayout(2, 1));
            label_itemNumber = new JLabel("item number");
            label_amount = new JLabel("amount");
            labelPanel.add(label_itemNumber);
            labelPanel.add(label_amount);
        }

        JTextField tf_itemNumber, tf_amount;
        public void drawFieldPanel() {
            fieldPanel = new JPanel(new GridLayout(2, 1));
            tf_itemNumber = new JTextField();
            tf_amount = new JTextField();
            fieldPanel.add(tf_itemNumber);
            fieldPanel.add(tf_amount);
        }

        private class amountHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == setStorage) {

                }
            }
        }
    }
}

