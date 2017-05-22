package com.company;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.*;
/**
 * Created by mctf on 07.05.17.
 */
/**
 * speed in 0..7;
 */
public class Ball {
    double maxSpeed=7;

    protected double clashRadius=8;
    private double xCoord, yCoord;
    private double xSpeed, ySpeed;
    private int radius=7;
    public double mass=1;

    Ball(double xCoord, double yCoord, double xSpeed, double ySpeed){
        this.xCoord = xCoord;this.yCoord = yCoord;
        double frequencyfactor=((double) (MainContent.ball_Frequency))/10;
        double energy=sqrt(xSpeed*xSpeed+ySpeed*ySpeed);
        if(energy>7){
//            System.out.println("im here");
            this.xSpeed = 7*xSpeed/energy*frequencyfactor;this.ySpeed =7*ySpeed/energy*frequencyfactor;
        }
        else {
            this.xSpeed = xSpeed * frequencyfactor;
            this.ySpeed = ySpeed * frequencyfactor;
        }
    }
    Ball(double xCoord, double yCoord,boolean rand){
        this.xCoord = xCoord;this.yCoord = yCoord;this.xSpeed = 0.0;this.ySpeed = 0.0;
    }
    Ball(boolean random){
        if (random){
            Random rand=new Random();
            xCoord=rand.nextDouble()*300;
            yCoord=rand.nextDouble()*500;
            xSpeed=rand.nextDouble()*3;
            ySpeed=rand.nextDouble()*3;

        }
    }

    public Ball() {
    }
    public double getxBallcenter(){
        return xCoord+radius;
    }
    public double getyBallcenter(){
        return yCoord+radius;
    }


    //взаимодействие и движение шара с разными столами
    //овал
    public Boolean Bound_oval(Field field){
        return null;
    }

    //взаимодействие и движение шара с разными столами
    //RECT
    public Boolean Bound_Rect(Field field){
        Boolean xBound=(getxBallcenter()<field.xcoord+clashRadius||
                getxBallcenter()>field.xcoord+field.width-clashRadius);
        Boolean yBound=((getyBallcenter()<field.ycoord+clashRadius)||
                (getyBallcenter()>field.height +field.ycoord -clashRadius));

        if(!(xBound||yBound)){
            xCoord+=xSpeed;yCoord+=ySpeed;
            return true;
        }

        if(xBound&&yBound){
//            System.out.println("IM HERE");
            xSpeed=-xSpeed;ySpeed=-ySpeed;
            xCoord+=xSpeed;yCoord+=ySpeed;
            return false;
        }

        if (yBound){
            ySpeed=-ySpeed;
            xCoord+=xSpeed;yCoord+=ySpeed;
            return false;
        }
        if (xBound){
            xSpeed=-xSpeed;
            xCoord+=xSpeed;yCoord+=ySpeed;
            return false;
        }
        return true;
    }

    //обработка соударения 2х шаров
    public Boolean ClashWithBall(Ball ball){
        if(abs(xCoord-ball.xCoord)<radius+ball.radius+3)
            if (abs(yCoord-ball.yCoord)<radius+ball.radius+3)
        if(sqrt( ((getxBallcenter())-ball.getxBallcenter())*( (getxBallcenter())-ball.getxBallcenter())+
                ( (getyBallcenter())-ball.getyBallcenter())*( (getyBallcenter())-ball.getyBallcenter()))
                <=radius+ball.radius){

            //полные вектора скоростей + нормаль
            Vector Xf=new Vector(xSpeed,ySpeed,"Xvector");
            Vector Yf=new Vector(ball.xSpeed,ball.ySpeed,"yVector");
            Vector L= new Vector(
                    (getxBallcenter())-ball.getxBallcenter(),
                    (getyBallcenter())-ball.getyBallcenter(),"L");L.normal();

//          Xf.show();Yf.show();L.show();

            //определение тангенциальной и нормальной скоростей X
            Vector Xr=L.getMultiplicateNumber(Xf.scalar(L));
            Vector Xt=Xf.summ(Xr.getMultiplicateNumber(-1));
            //определение тангенциальной и нормальной скоростей Y
            Vector Yr=L.getMultiplicateNumber(Yf.scalar(L));
            Vector Yt=Yf.summ(Yr.getMultiplicateNumber(-1));
            //x=(m1 Imnpul - sqrt(m1 m2 (-Imnpul^2 + 2 m1 Energ + 2 m2 Energ)))/(m1 (m1 + m2))         !!!!!!!!!!!!!!!!!!!!!!!
            //y=(m2 Imnpul - sqrt(m1 m2 (-Imnpul^2 + 2 m1 Energ + 2 m2 Energ)))/(m2 (m1 + m2))         !!!!!!!!!!!!!!!!!!!!!!!!!!!
            Double Energ=mass*Xr.lenght()*Xr.lenght()+ball.mass*Yr.lenght()*Yr.lenght();
            Double Impul=mass*Xr.lenght()-ball.mass*Yr.lenght();

//            System.out.println(Xf.scalar(L)+"   "+Yf.scalar(L));

            Double x=(mass* Impul - sqrt(mass*ball.mass*(-Impul*Impul + 2* mass* Energ + 2* ball.mass*Energ)))/(mass *(mass + ball.mass));
            Double y=(ball.mass* Impul - sqrt(mass*ball.mass*(-Impul*Impul + 2* mass* Energ + 2* ball.mass*Energ)))/(ball.mass *(mass + ball.mass));
            if (Xf.scalar(L)+Yf.scalar(L)!=x+y){
                y=(+ball.mass* Impul - sqrt(mass*ball.mass*(-Impul*Impul + 2* mass* Energ + 2* ball.mass*Energ)))/(ball.mass *(mass + ball.mass));
                x=(-mass* Impul + sqrt(mass*ball.mass*(-Impul*Impul + 2* mass* Energ + 2* ball.mass*Energ)))/(mass *(mass + ball.mass));
            }
//            System.out.println(x+"   "+y);

            Xr=L.getMultiplicateNumber(x/sqrt(2));
            Yr=L.getMultiplicateNumber(y/sqrt(2));
            Xf=Xr.summ(Xt);
            Yf=Yr.summ(Yt);

            xSpeed=Xf.getX();ySpeed=Xf.getY();
            ball.xSpeed=Yf.getX();ball.ySpeed=Yf.getY();

//            Window.mainContent.moveTimer.stop();
            Window.clashnumber++;
            return false;
        }

        return true;
    }

    //painting
    public void Paintthis(Graphics g){
        g.fillOval((int)xCoord,(int)yCoord,radius*2,radius*2);
    }

}