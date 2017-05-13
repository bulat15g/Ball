package com.company;

import java.awt.*;

/**
 * Created by mctf on 07.05.17.
 */
public class Field {
    public int xcoord, ycoord, width, height;

    public Field(int xcoord, int ycoord, int width, int height) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.width = width;
        this.height = height;
    }

    public int getXCenter(){
        return (xcoord + width)/2;
    }
    public int getYCenter(){
        return (ycoord + height)/2;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public void Paintthis(Graphics g){
        g.drawRect(xcoord, ycoord, width, height);
    }
}