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

    //нормализация данного вектора
    public void normal(){
        double len=sqrt(x*x+y*y);
        x/=len;y/=len;

    }

    //показ давнного вектора
    public void show(){
        System.out.println(name+"   x:="+x+"      "+"y=    "+y+"   module:="+lenght());
    }

    //скалядное произведение
    public double scalar(Vector a){
        return a.x*x+a.y*y;

    }

    //получает нормированный вектор не изменяя текцщий
    public static Vector getNormal(Vector a){
        double len=sqrt(a.x*a.x+a.y*a.y);
        a.x/=len;a.y/=len;
        return a;
    }

    //получает усноженный вектор не изменяя текцщий
    public Vector getMultiplicateNumber(double a){
        return new Vector(x*a,y*a,"Mult");
    }

    //получает нормированный вектор ИЗМЕНЯЯ текцщий
    public Vector MultiplicateNumber(double a){
        x=x*a;y=y*a;
        return this;
    }

    //получает ортогональный вектор не изменяя текцщий
    public Vector getLine_normal(){
        return new Vector(-y,x,"normal");
    }

    //получает длину вектора
    public double lenght(){
        return sqrt(x*x+y*y);
    }

    //получает сумму векторов не изменяя текцщий
    public Vector summ(Vector a){
        Vector ret= new Vector(a.x+x,a.y+y,name);
        return ret;
    }

}
