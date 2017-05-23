package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static java.lang.Integer.parseInt;
import static java.lang.Math.sqrt;

/**
 * Created by mctf on 06.05.17.
 */
public class MainContent extends JComponent {
    public static int ball_Frequency=10;
    public MoveObjects moveObjects = new MoveObjects();
    public static Integer countRandomBalls;


    //repaint timer every 10 MS
    Timer repaintTimer =new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    /**
     * setMoveObjects
     */
    public void setMoveObjects(){
        moveObjects.addObject(new Field(30,30,350,500));
        moveObjects.addObject(new Ball(100,80,0,1));
        moveObjects.getBall(0).setBallColor(Color.GREEN);
        moveObjects.addObject(new Ball(100,180,0,0));

        for (int i = 0; i < countRandomBalls; i++) {
            moveObjects.addObject(new Ball(true));
        }
        moveObjects.clearUselessObjects();
    }

    // TODO: 07.05.17 ????
    Timer moveTimer =new Timer(ball_Frequency, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveObjects.moveTimer();
        }
    });


    /**
     * initiate repaint&move
     */
    MainContent(){

        countRandomBalls=Integer.parseInt(JOptionPane.showInputDialog("ВВедите колво шаров"));
        repaintTimer.start();
        moveTimer.start();
        setMoveObjects();
    }


    // TODO: 07.05.17 add normal resizable
    public void paint (Graphics G){
        moveObjects.paintObjects(G);
    }

}
