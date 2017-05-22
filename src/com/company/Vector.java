package com.company;

import com.sun.org.apache.xpath.internal.operations.Operation;

import static java.lang.Math.sqrt;

/**
 * Created by mctf on 08.05.17.
 */
public class Vector {
    String name;
    double x,y;

    /**
     * приравниевает левый вектор
     * @param a
     */
    public void equals(Vector a){
        x=a.x;y=a.y;
    }

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
        double len=sqrt(x*x+y*y);
        x/=len;y/=len;

    }

    public void show(){
        System.out.println(name+"   x:="+x+"      "+"y=    "+y+"   module:="+lenght());
    }

    public double scalar(Vector a){
        return a.x*x+a.y*y;

    }

    public static Vector getNormal(Vector a){
        double len=sqrt(a.x*a.x+a.y*a.y);
        a.x/=len;a.y/=len;
        return a;
    }

    public Vector getMultiplicateNumber(double a){
        return new Vector(x*a,y*a,"Mult");
    }

    public Vector MultiplicateNumber(double a){
        x=x*a;y=y*a;
        return this;
    }

    public Vector getLine_normal(){
        return new Vector(-y,x,"normal");
    }

    public double lenght(){
        return sqrt(x*x+y*y);
    }

    public Vector summ(Vector a){
        Vector ret= new Vector(a.x+x,a.y+y,name);
        return ret;
    }
}
