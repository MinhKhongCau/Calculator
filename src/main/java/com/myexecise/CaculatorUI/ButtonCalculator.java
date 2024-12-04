package com.myexecise.CaculatorUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author minhn
 */
public class ButtonCalculator extends JButton{

    public ButtonCalculator() {
        this.setBackground(new Color(185,178,158));
        this.setForeground(new Color(0,0,0));
        this.setPreferredSize(new Dimension(80, 60));
    }
    
    public ButtonCalculator(String text) {
        this();
        this.setText(text);
    }
    
    
    
}
