/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myexecise.CaculatorUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author minhn
 */
public class CalculatorUI extends JFrame {
    private JPanel screen;
    private JPanel grid;
    private JTextField screenInput;
    private JTextField screenResult;
    private ButtonCalculator button;

    public CalculatorUI() {
        setBackground(new Color(42,42,42));
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLayout(new BorderLayout(10,20));
        setTitle("Calculator");
        setResizable(false);
        initComponent();
    }

    private void initComponent() {
        screen = new JPanel();
        grid = new JPanel();
        
        screenInput = new JTextField();
        screenInput.setPreferredSize(new Dimension(180, 50));

        screenResult = new JTextField();
        screenResult.setPreferredSize(new Dimension(180, 50));
//        Add textField to screen
        screen.add(screenInput);
        screen.add(screenResult);
        
//        Set layout to grid
        grid.setLayout(new GridLayout(6, 4));
        
        String[] ICON = {"%","CE","C","<<",
                        "(",")","+/-","cls",
                        "1","2","3","+",
                        "4","5","6","-",
                        "7","8","9","*",
                        "0",".","=","/",};
        for (int i = 0; i<24; i++) {
            button = new ButtonCalculator(ICON[i]);
            button.addActionListener((e)->{
                String text = ((JButton) e.getSource()).getText();
                handleInputButton(text);
            });
            grid.add(button);
        }
        
        this.add(screen, BorderLayout.NORTH);
        this.add(grid, BorderLayout.CENTER);
        this.pack();
    }

    private void handleInputButton(String text) {
        switch (text){
            case "1","2","3","+","4","5","6","-","7","8","9","*","/","%","(",")":
                screenInput.setText(screenInput.getText() + text);
                break;
            case "<<", "CE","cls":
                screenInput.setText("");
                break;
            case "C":
                String newValue = screenInput.getText();
                newValue = newValue.substring(0, newValue.length()-1);
                screenInput.setText(newValue);
                break;
            case "=":
                String result = calculatorHandler(screenInput.getText());
                
                screenResult.setText(result);
                break;
        }
    }

    private String calculatorHandler(String input) {
        ArrayList<Character> postfix = infixToPostfix̣̣̣(input);
        
        System.out.println("Postfix is: "+postfix);
        double result = calculatorByPostfix(postfix);
        return String.valueOf(result);
    }

    private ArrayList<Character> infixToPostfix̣̣̣(String input) {
        Stack<Character> operator = new Stack<>();
        ArrayList<Character> postfix = new ArrayList<>();
        for (Character tmp: input.toCharArray()) {
            if (Character.isDigit(tmp)) {
                postfix.add(tmp);
                System.out.println("Array add item: "+tmp);
            } 
            else if (tmp == '(') {
                operator.push(tmp);
            }
            else if (tmp == ')') {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    postfix.add(operator.pop());
                }
                operator.pop();
            }
            else {
                while (!operator.isEmpty() && getPriority(tmp) <= getPriority(operator.peek())) {
                    postfix.add(operator.pop());
                }
                operator.add(tmp);
                System.out.println("Stack push item: "+tmp);
            }
        }
        while (!operator.isEmpty()) {
            postfix.add(operator.pop());
        }
        return postfix;
    }
    
    public static int getPriority(Character ch) {
        if (ch == '*' || ch == '/' || ch == '%') {
            return 2;
        } else if (ch == '-' || ch == '+') {
            return 1;
        } else return 0;
    }
    
    public static boolean checkPriority(Character ch1, Character ch2) {
        return getPriority(ch1) > getPriority(ch2);
    }

    private double calculatorByPostfix(ArrayList<Character> postfix) {
        Stack<Double> stack = new Stack<>();
        for (Character ch: postfix) {
            System.out.println("Character is: "+ch);
            if (getPriority(ch) == 0) {
                stack.push(Double.valueOf(ch-'0'));
                System.out.println("State stack: "+stack.toString());
            } else {
                Double v1 = stack.pop();
                Double v2 = stack.pop();
                Double result = 0.0d;                
                switch (ch) {
                    case '+':
                        result = v2+v1;
                        break;
                    case '-':
                        result = v2-v1;
                        break;
                    case '*':
                        result = v2*v1;
                        break;
                    case '/':
                        result = v2/v1;
                        break;
                    case '%':
                        result = v2%v1;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

}
