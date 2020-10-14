import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private static final long serialVersionUID = 10L;

    JButton a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, aPoint, aEqual, aClear, aAdd, aSub, aMul, aDiv;
    JTextField tf1;
    JFrame f1;

    StringBuffer numStr1, numStr2;
    String sign, operation;
    double num1, num2, res;

    public Calculator() {
        numStr1 = new StringBuffer();
        numStr2 = new StringBuffer();
        num1=0;
        num2=0;
        sign="+";

        setSize(270, 200);
        setResizable(false);
        setTitle("Calculator");

        a0 = new JButton("0");
        a1 = new JButton("1");
        a2 = new JButton("2");
        a3 = new JButton("3");
        a4 = new JButton("4");
        a5 = new JButton("5");
        a6 = new JButton("6");
        a7 = new JButton("7");
        a8 = new JButton("8");
        a9 = new JButton("9");
        aPoint = new JButton(".");
        aEqual = new JButton("=");
        aClear = new JButton("AC");
        aAdd = new JButton("+");
        aSub = new JButton("-");
        aMul = new JButton("*");
        aDiv = new JButton("/");
        tf1 = new JTextField(20);

        add(tf1);
        tf1.setText("0");
        setLayout(new FlowLayout());

        add(a1);
        add(a2);
        add(a3);
        add(aAdd);
        add(aSub);
        add(a4);
        add(a5);
        add(a6);
        add(aMul);
        add(aDiv);
        add(a7);
        add(a8);
        add(a9);
        add(a0);
        add(aPoint);
        add(aEqual);
        add(aClear);

        a0.addActionListener(new number());
        a1.addActionListener(new number());
        a2.addActionListener(new number());
        a3.addActionListener(new number());
        a4.addActionListener(new number());
        a5.addActionListener(new number());
        a6.addActionListener(new number());
        a7.addActionListener(new number());
        a8.addActionListener(new number());
        a9.addActionListener(new number());

        aPoint.addActionListener(new symbol());
        aAdd.addActionListener(new symbol());
        aSub.addActionListener(new symbol());
        aMul.addActionListener(new symbol());
        aDiv.addActionListener(new symbol());
        aEqual.addActionListener(new symbol());
        aClear.addActionListener(new symbol());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e1){
                System.exit(0);
            }
        });

        setVisible(true);
    }

    class number implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String c = ((JButton) e.getSource()).getText();
            numStr1.append(c);
            tf1.setText(numStr1.toString());
        }
    }

    class symbol implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            operation  = ((JButton) e.getSource()).getText();
            if (operation==".") {
                numStr1.append(".");
                tf1.setText(numStr1.toString());
            }
            else if (operation=="="){
                num1=Double.parseDouble(numStr1.toString());
                num2=Double.parseDouble(numStr2.toString());
                switch (sign) {
                    case "+": res=num2+num1; break;
                    case "-": res=num2-num1; break;
                    case "*": res=num2*num1; break;
                    case "/": res=num2/num1; break;
                }
                tf1.setText(Double.toString(res));
                System.out.println(num2+sign+num1+"="+res);

                numStr1.delete(0, numStr1.length());
                numStr2.delete(0, numStr2.length());
            }
            else if (operation=="AC") {
                numStr1.delete(0, numStr1.length());
                numStr2.delete(0, numStr2.length());
                num1=0;
                num2=0;
                res=0;
                sign="Add";
                tf1.setText("0");
            }
            else {
                sign = operation;
                if (numStr1.length()+numStr2.length()<1) {
                    numStr2.replace(0, numStr1.length(), Double.toString(res));
                }
                else if (numStr2.length()==0) {
                    numStr2=numStr1;
                    numStr1= new StringBuffer();
                }
                tf1.setText(numStr2.toString());
            }
        }
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal);
    }
}
