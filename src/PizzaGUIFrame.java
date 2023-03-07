import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class PizzaGUIFrame extends JFrame{

    JPanel mainPnl;
    JPanel topPnl;
    JPanel bottomPnl;
    JPanel crustPnl;
    JPanel sizePnl;
    JPanel toppingsPnl;
    JPanel displayPnl;
    JPanel controlPnl;

    JTextArea displayTA;
    JScrollPane scroller;

    JRadioButton thinRb;
    JRadioButton regularRb = new JRadioButton("Regular");
    JRadioButton dDRb;
    private String crust = "";

    ButtonGroup group = new ButtonGroup();

    JComboBox sizeCb;
    int sizeCost;

    JCheckBox pepperoniCb;
    JCheckBox mushroomCb;
    JCheckBox sausageCb;
    JCheckBox pineappleCb;
    JCheckBox spinachCb;
    JCheckBox baconCb;

    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;

    int subTotal = 0;

    public PizzaGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        topPnl = new JPanel();
        topPnl.setLayout(new BorderLayout());

        bottomPnl = new JPanel();
        bottomPnl.setLayout(new BorderLayout());

        createCrustPnl();
        topPnl.add(crustPnl, BorderLayout.WEST);
        /*mainPnl.add(crustPnl);*/

        createSizePnl();
        topPnl.add(sizePnl, BorderLayout.EAST);
        /*mainPnl.add(sizePnl);*/

        createToppingsPnl();
        topPnl.add(toppingsPnl, BorderLayout.SOUTH);
        /*mainPnl.add(toppingsPnl);*/

        mainPnl.add(topPnl, BorderLayout.NORTH);

        createDisplayPnl();
        bottomPnl.add(displayPnl, BorderLayout.NORTH);
        /*mainPnl.add(displayPnl);*/

        createControlPnl();
        bottomPnl.add(controlPnl, BorderLayout.SOUTH);
        /*mainPnl.add(controlPnl);*/

        mainPnl.add(bottomPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createCrustPnl() {
        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(1, 3));
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust"));

        thinRb = new JRadioButton("Thin");
        thinRb.addActionListener((ae) -> crust = "Thin");

        regularRb.addActionListener((ae) -> crust = "Regular");

        dDRb = new JRadioButton("Deep-dish");
        dDRb.addActionListener((ae) -> crust = "Deep-dish");

        crustPnl.add(thinRb);
        crustPnl.add(regularRb);
        crustPnl.add(dDRb);

        regularRb.setSelected(true);

        group.add(thinRb);
        group.add(regularRb);
        group.add(dDRb);
    }

    private void createSizePnl() {
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Size"));

        sizeCb = new JComboBox();
        sizeCb.addItem("Small");
        sizeCb.addItem("Medium");
        sizeCb.addItem("Large");
        sizeCb.addItem("Super");

        sizePnl.add(sizeCb);
    }

    private void createToppingsPnl() {
        toppingsPnl = new JPanel();
        toppingsPnl.setLayout(new GridLayout(2, 4));
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        pepperoniCb = new JCheckBox("Pepperoni");
        mushroomCb = new JCheckBox("Mushroom");
        sausageCb = new JCheckBox("Sausage");
        pineappleCb = new JCheckBox("Olives");
        spinachCb = new JCheckBox("Onions");
        baconCb = new JCheckBox("Bacon");

        toppingsPnl.add(pepperoniCb);
        toppingsPnl.add(mushroomCb);
        toppingsPnl.add(sausageCb);
        toppingsPnl.add(pineappleCb);
        toppingsPnl.add(spinachCb);
        toppingsPnl.add(baconCb);
    }

    private void createDisplayPnl() {
        displayPnl = new JPanel();
        displayTA = new JTextArea(30,40);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        displayPnl.add(scroller);
    }

    private void createControlPnl() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1,3));
        controlPnl.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));

        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        controlPnl.add(orderBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);

        orderBtn.addActionListener((ActionEvent ae) -> {

            DecimalFormat df = new DecimalFormat("0.00");

            displayTA.append("===========================\n");

            displayTA.append("Crust style: " + crust + "\n");

            if (sizeCb.getSelectedItem() == "Small") {
                sizeCost = 8;
            }
            if (sizeCb.getSelectedItem() == "Medium") {
                sizeCost = 12;
            }
            if (sizeCb.getSelectedItem() == "Large") {
                sizeCost = 16;
            }
            if (sizeCb.getSelectedItem() == "Super") {
                sizeCost = 20;
            }
            subTotal += sizeCost;
            String size = String.format("%-1s%-45s%-15s\n", "Size: ", sizeCb.getSelectedItem(), df.format(sizeCost));
            displayTA.append(size);

            displayTA.append("Toppings: " + /*df.format(toppingTotal) +*/ "\n");

            if (pepperoniCb.isSelected()) {
                String pepperoni = String.format("%-30s%-30s\n", "\tPepperoni", "1.00");
                displayTA.append(pepperoni);
                subTotal += 1;
            }
            if (mushroomCb.isSelected()) {
                String mushroom = String.format("%-28s%-28s\n", "\tMushroom", "1.00");
                displayTA.append(mushroom);
                subTotal += 1;
            }
            if (sausageCb.isSelected()) {
                String sausage = String.format("%-31s%-31s\n", "\tSausage", "1.00");
                displayTA.append(sausage);
                subTotal += 1;
            }
            if (pineappleCb.isSelected()) {
                String pineapple = String.format("%-31s%-31s\n", "\tPineapple", "1.00");
                displayTA.append(pineapple);
                subTotal += 1;
            }
            if (spinachCb.isSelected()) {
                String spinach = String.format("%-31s%-31s\n", "\tSpinach", "1.00");
                displayTA.append(spinach);
                subTotal += 1;
            }
            if (baconCb.isSelected()) {
                String bacon = String.format("%-32s%-32s\n", "\tBacon", "1.00");
                displayTA.append(bacon);
                subTotal += 1;
            }

            displayTA.append("\n");

            String st = String.format("%-52s%-30s\n", "Sub-total: ", df.format(subTotal));
            displayTA.append(st);

            double x = subTotal * .07;
            String tax = String.format("%-57s%-30s\n", "Tax: ", df.format(x));
            displayTA.append(tax);

            displayTA.append("----------------------------------\n");

            double y = subTotal + x;
            String total = String.format("%-55s%-30s\n", "Total: ", df.format(y));
            displayTA.append(total);

            displayTA.append("===========================\n");
        });

        clearBtn.addActionListener((ActionEvent ae) -> {
            displayTA.setText("");
            regularRb.setSelected(true);
            group.clearSelection();
            pepperoniCb.setSelected(false);
            mushroomCb.setSelected(false);
            sausageCb.setSelected(false);
            pineappleCb.setSelected(false);
            spinachCb.setSelected(false);
            baconCb.setSelected(false);
            regularRb.setSelected(true);
        });

        JLabel label = new JLabel();
        quitBtn.addActionListener((ActionEvent ae) -> {
            int result = JOptionPane.showConfirmDialog(displayTA, "Sure? You want to exit?", "Swing Tester", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                label.setText("You selected: Yes");
                System.exit(0);
            }
            else if (result == JOptionPane.NO_OPTION) {
                label.setText("You selected: No");
            }
            else {
                label.setText("None selected");
            }
            System.exit(0);
        });
    }
}