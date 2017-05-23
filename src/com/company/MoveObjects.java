package com.company;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mctf on 21.05.17.
 */
public class MoveObjects {
    ArrayList<Ball> ballArrayList;
    Field field;
    Cue cue=new Cue();

    /**
     * constructor
     */
    public MoveObjects() {
        ballArrayList=new ArrayList<>();
        field=new Field(0,0,0,0);
    }

    public void clearUselessObjects(){
        for (int i = 0; i < ballArrayList.size(); i++) {
            Boolean xBound=(ballArrayList.get(i).getxBallcenter()<field.xcoord+2||
                    ballArrayList.get(i).getxBallcenter()>field.xcoord+field.width+1*ballArrayList.get(i).clashRadius);
            Boolean yBound=((ballArrayList.get(i).getyBallcenter()<field.ycoord)||
                    (ballArrayList.get(i).getyBallcenter()>field.height +field.ycoord +1*ballArrayList.get(i).clashRadius));
            if(yBound||xBound){
                ballArrayList.remove(i);
            }
        }
    }

    public void addObject(Object object){
        if(object instanceof Ball){
            ballArrayList.add((Ball) object);
        }
        if(object instanceof Field){
            field=(Field)object;
        }
    }

    public void moveTimer(){

        int count=ballArrayList.size();
        for (int i = 0; i < count; i++) {
            ballArrayList.get(i).Bound_Rect(field);
        }

        for (int i = 0; i < count; i++) {
            for (int j = i+1; j < count; j++) {
                ballArrayList.get(i).ClashWithBall(
                        ballArrayList.get(j)
                );
            }
        }
    }

    public void paintObjects(Graphics G){
        for (int i = 0; i < ballArrayList.size(); i++) {
            ballArrayList.get(i).Paintthis(G);
        }
        field.Paintthis(G);
    }

    public Ball getBall(int i) {
        return ballArrayList.get(i);
    }
}
