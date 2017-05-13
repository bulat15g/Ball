package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * Created by mctf on 06.05.17.
 */
public class MainContent extends JComponent {
    public static int ball_Frequency=10;
    private Field field=new Field(30,30,350,500);
    public Ball ball=new Ball(150,80,1,3);

    //oval+rect
    //repaint timer every 10 MS
    Timer repaintTimer =new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });


    // TODO: 07.05.17 normal move
    Timer moveTimer =new Timer(ball_Frequency, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ball.Bound_Rect(field);
        }
    });


    /**
     * initiate repaint&move
     */
    MainContent(){
        repaintTimer.start();
        moveTimer.start();
    }



    // TODO: 07.05.17 add normal resizable
    public void paint (Graphics G){
        field.Paintthis(G);
        ball.Paintthis(G);
    }

}
