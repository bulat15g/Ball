package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.nio.Buffer;

/**
 * Created by mctf on 23.05.17.
 */
public class Cue {
    public Ball ball;
    public double phi=270;
    public Boolean cueIsNesessary=false;
    int cueRect[][];
    double deltaPhi=7,rad=10;
    double powerShot=4;

    Cue(Ball ball){
        this.ball=ball;
    }
    Cue(){
        ball=new Ball();
        cueRect=new int[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                cueRect[i][j]=0;
            }
        }
    }

    public void setCueRect(){
        for (int i = 0; i < 2; i++) {
            cueRect[i][0]=(int)(ball.getxBallcenter()+rad*Math.cos(degToRad(phi+i*deltaPhi)));
            cueRect[i][1]=(int) (ball.getyBallcenter()-rad*Math.sin(degToRad(phi+i*deltaPhi)));
        }
        for (int i = 0; i < 2; i++) {
            cueRect[2+i][0]=(int)(ball.getxBallcenter()+5*rad*Math.cos(degToRad(phi+i*deltaPhi)));
            cueRect[2+i][1]=(int) (ball.getyBallcenter()-5*rad*Math.sin(degToRad(phi+i*deltaPhi)));
        }

    }

    //назначает шар для кия
    public void setBall(Ball ball) {
        this.ball = ball;

    }

    //Вращение кия вокруг шара
    public void move(int i){
        //против часовой стрелки
        if(i==1){
            phi+=2;
        }
        // по часовой стрелке
        if(i==-1){
            phi-=2;
        }
        if(i==0){
            ball.setSpeed(-powerShot*Math.cos(degToRad(phi)),powerShot*Math.sin(degToRad(phi)));
        }

    }

    public double degToRad(double phi){
        while ((phi<=360&&phi>=0)){
            if (phi<360)phi-=360;
            if (phi>0)phi+=360;
        }

        return phi/180*Math.PI;
    }

    public void Paintthis(Graphics g){
        setCueRect();
        g.drawLine(cueRect[0][0],cueRect[0][1],cueRect[2][0],cueRect[2][1]);
        g.drawLine(cueRect[1][0],cueRect[1][1],cueRect[3][0],cueRect[3][1]);
    }
}
