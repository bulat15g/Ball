package com.company;

import static java.lang.Math.sqrt;

/**
 * Created by mctf on 08.05.17.
 */
public class Vector {
    String name;
    double x,y;

    public Vector(double x, double y,String name) {
        this.x = x;
        this.y = y;
        this.name=name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void normal(){
        double len=x*x+y*y;
        x/=len;y/=len;

    }

    public void multiplicate(double a){
        x=x*a;y=y*a;
    }

    public void line_normal(){
        double buff=x;
        x=-y;
        y=buff;
    }

    public void show(){
        System.out.println(name+"   x:="+x+"      "+"y=    "+y+"   module:="+lenght());
    }

    public double scalar(Vector a){
        return a.x*x+a.y*y;

    }

    public double lenght(){
        return sqrt(x*x+y*y);
    }

    public Vector summ(Vector a){
        Vector ret= new Vector(a.x+x,a.y+y,name);
        return ret;
    }
}
