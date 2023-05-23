package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;

public class StartScreen {

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouse;
    JPanel jp;

    public StartScreen(GamePanel gp, KeyHandler keyH, MouseHandler mouse){
        this.gp = gp;
        this.keyH = keyH;
        this.mouse = mouse;
        jp = new JPanel();
        JButton start = new JButton("start");
        jp.add(start);
        class Listener implements ActionListener{

            public void actionPerformed(ActionEvent e){
                gp.startGameThread();
            }

        }
        start.addActionListener(new Listener());

    }
    public void draw(Graphics g){


    }}
