package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mctf on 21.05.17.
 */
public class MoveObjects {
    ArrayList<Ball> ballArrayList;
    Field field;
    Cue cue;
    public Boolean shootMode=false;

    /**
     * constructor
     */
    public MoveObjects() {
        ballArrayList=new ArrayList<>();
        field=new Field(0,0,0,0);
        cue=new Cue();
    }

    //очищает шары вне столы
    public void clearUselessObjects(){
        for (int i = 0; i < ballArrayList.size(); i++) {
            boolean xBound=(ballArrayList.get(i).getxBallcenter()<field.xcoord||
                    ballArrayList.get(i).getxBallcenter()>field.xcoord+field.width+1*ballArrayList.get(i).clashRadius);
            boolean yBound=((ballArrayList.get(i).getyBallcenter()<field.ycoord)||
                    (ballArrayList.get(i).getyBallcenter()>field.height +field.ycoord +1*ballArrayList.get(i).clashRadius));
            if(yBound||xBound){
                ballArrayList.remove(i);
            }
        }
    }

    //добавление объекта
    public void addObject(Object object){
        if(object instanceof Ball){
            ballArrayList.add((Ball) object);
        }
        if(object instanceof Field){
            field=(Field)object;
        }
        if(object instanceof Cue){
            cue=(Cue)object;
        }
    }

    //действие,выполняющееся каждую минуту
    public void moveTimer(){

        int count=ballArrayList.size();
        shootMode=true;
        for (int i = 0; i < count; i++) {
            if(!ballArrayList.get(i).isSpeedZero()){
                shootMode=false;
                break;
            }
        }

        if(!shootMode) {
            cue.cueIsNesessary=false;
            for (int i = 0; i < count; i++) {
                ballArrayList.get(i).Bound_Rect(field);
            }

            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    ballArrayList.get(i).ClashWithBall(
                            ballArrayList.get(j)
                    );
                }
            }
        }

        else {
            cue.cueIsNesessary=true;
        }
    }

    //рисовка
    public void paintObjects(Graphics G){
        for (int i = 0; i < ballArrayList.size(); i++) {
            ballArrayList.get(i).Paintthis(G);
        }
        field.Paintthis(G);
        if(cue.cueIsNesessary){
            cue.Paintthis(G);
        }
    }

    //get Center
    public Dimension getCenter(){
        return new Dimension(field.getXCenter(),field.getYCenter());
    }

    //получение шара
    public Ball getBall(int i) {
        return ballArrayList.get(i);
    }
}
