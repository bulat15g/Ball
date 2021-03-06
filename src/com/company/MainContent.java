package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static java.lang.Integer.parseInt;
import static java.lang.Math.random;
import static java.lang.Math.sqrt;

/**
 * Created by mctf on 06.05.17.
 */
public class MainContent extends JComponent {
    public static int ball_Frequency=10;
    public MoveObjects moveObjects = new MoveObjects();


    //repaint timer every 10 MS
    Timer repaintTimer =new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    Timer moveTimer =new Timer(ball_Frequency, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveObjects.moveTimer();
        }
    });

    /**
     * setMoveObjects
     */
    public void setMoveObjects(){
        if(moveObjects==null)moveObjects=new MoveObjects();

        moveObjects.addObject(new Field(30,30,350,500));

        // TODO: 5/28/17 tut kosyak
        Dimension fieldCenter = moveObjects.getCenter();

        moveObjects.addObject(new Ball(fieldCenter.getWidth(), fieldCenter.getHeight() + 60, 0, 0));
        moveObjects.getBall(0).setBallColor(Color.GREEN);
        moveObjects.cue.setBall(moveObjects.getBall(0));

        for (int i = 5; i >= 1; i--) {
            int rad = 11;
            for (int j = 0; j < 1 + i * 2; j++) {
                if (j == 0) {
                    moveObjects.addObject(new Ball(fieldCenter.getWidth(), fieldCenter.getHeight() - 80 - i * rad));
                    continue;
                }
                moveObjects.addObject(new Ball(
                        fieldCenter.getWidth() - j * rad - 2 * random(),
                        fieldCenter.getHeight() - 80 - (3 - i) * rad - j * rad));
                moveObjects.addObject(new Ball(
                        fieldCenter.getWidth() + j * rad,
                        fieldCenter.getHeight() - 80 - (3 - i) * rad - j * rad));
            }
        }

    }

    /**
     * initiate repaint&move
     */
    MainContent(){
        repaintTimer.start();
//        moveTimer.start();
        setMoveObjects();
    }


    public void paint (Graphics G ) {
        moveObjects.paintObjects(G);
    }

}
