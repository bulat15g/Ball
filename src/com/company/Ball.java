package com.company;

import java.awt.*;

import static java.lang.Math.*;

/**
 * Created by mctf on 07.05.17.
 */

/**
 * speed in 0..7;
 */
public class Ball {
    double maxSpeed=7;

    private double clashRadius=8;
    private double xCoord, yCoord;
    private double xSpeed, ySpeed;
    private int radius=7;


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
        /**
         * r^2=(x-x0)^2/a^2+(y-y0)^2/b^2
         * Есть вектор R
         Это линия
         Надо найти -а это просто R^-1
         A'=A+|A|*Er^-1
         */

        //ниже 4 строки-определяем константы и вспомогательные хрени
        double xthis=xCoord-field.getXCenter();
        double ythis=yCoord-field.getYCenter();
        double aa=field.getWidth()*field.getWidth();
        double bb=field.getHeight()*field.getHeight();

        //проверка входа в окружность
        if (1<=4*xthis*xthis/aa +4*ythis*ythis/bb )
        {
            //фи-угол (вычислено верно)
            double phi=((1-2*xthis/bb)*aa/(2*ythis));

            Vector fromcenter=new Vector(xthis,ythis,"fromcenter");
            Vector line;Vector ball=new Vector(xSpeed,ySpeed,"ball");

            //предельный случай
            if(phi>=99999999){
                line=new Vector(0,1,"line");
            }

            //его отсутсвие
            else {
                line=new Vector(sqrt(1/(1+phi*phi)),phi/sqrt((1+phi*phi)),"line");
            }

            ball.show();
            line.show();
//            System.out.println("next normal+perp");

            //нормализация линии
            line.line_normal();line.normal();
//            line.show();


            if (fromcenter.scalar(ball)>0){
//                System.out.println("next mult 1");
                line.multiplicate(2*ball.lenght());
//                line.show();
                ball=ball.summ(line);
            }
                else{
//                System.out.println("next mult 2");
                    line.multiplicate(-2*ball.lenght());
//                    line.show();
                    ball=ball.summ(line);
                }


            ball.show();
            line.show();
            xSpeed=ball.getX();
            ySpeed=ball.getY();
            xCoord+=xSpeed;yCoord+=ySpeed;

//            System.out.println(phi);
            return false;
        }

        else {
            xCoord+=xSpeed;yCoord+=ySpeed;
//            ySpeed=ySpeed*exp(-0.005);
            return true;
        }

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
            System.out.println("IM HERE");
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
        if (  sqrt(  ( (getxBallcenter())+ball.getxBallcenter())*( (getxBallcenter())+ball.getxBallcenter())   +
                ( (getyBallcenter())+ball.getyBallcenter())*( (getyBallcenter())+ball.getyBallcenter()))   >=
                radius+ball.radius){
            return  false;
        }


        return true;
    }

    //painting
    public void Paintthis(Graphics g){
        g.fillOval((int)xCoord,(int)yCoord,radius*2,radius*2);
    }


}