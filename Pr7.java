import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pr7 {
    private JTextField textField1;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton buttonMul;
    private JButton button4;
    private JButton button5;
    private JButton button1;
    private JButton button6;
    private JButton buttonSub;
    private JButton button2;
    private JButton button3;
    private JButton buttonAdd;
    private JButton button0;
    private JButton buttonDot;
    private JButton buttonEnter;
    private JButton buttonDiv;
    private JPanel Calculator;
    private JButton buttonClear;
    private JLabel Label;
    private String firstNumberStr = "";
    private String secondNumberStr = "";
    private double firstNumberDouble = 0;
    private double secondNumberDouble = 0;
    private double resultNumberDouble = 0;
    private String action = "";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Pr7().Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Pr7() {
        buttonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondNumberStr = textField1.getText();
                firstNumberDouble = Double.parseDouble(firstNumberStr);
                secondNumberDouble = Double.parseDouble(secondNumberStr);
                if (action == "+") {
                    resultNumberDouble = firstNumberDouble + secondNumberDouble;
                }
                if (action == "*") {
                    resultNumberDouble = firstNumberDouble * secondNumberDouble;
                }
                if (action == "-") {
                    resultNumberDouble = firstNumberDouble - secondNumberDouble;
                }
                if (action == "/") {
                    resultNumberDouble = firstNumberDouble / secondNumberDouble;
                }
                textField1.setText(String.valueOf(resultNumberDouble));
                Label.setText("");
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "7");
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "8");
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "9");
            }
        });
        buttonDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumberStr = textField1.getText();
                Label.setText(textField1.getText() + "/");
                textField1.setText("");
                action = "/";
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "4");
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "5");
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "6");
            }
        });
        buttonMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumberStr = textField1.getText();
                Label.setText(textField1.getText() + "*");
                textField1.setText("");
                action = "*";
            }
        });
        buttonSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumberStr = textField1.getText();
                Label.setText(textField1.getText() + "-");
                textField1.setText("");
                action = "-";
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "3");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "2");
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "1");
            }
        });
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "0");
            }
        });
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + ".");
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumberStr = textField1.getText();
                Label.setText(textField1.getText() + "+");
                textField1.setText("");
                action = "+";
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                firstNumberDouble = 0;
                secondNumberDouble = 0;
                firstNumberStr = "";
                secondNumberStr = "";
                Label.setText("");
                action = "";
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
