package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class Move 
{
    private shape moveingshape ;
    private Point mypt, movingp;
    
    public Move( shape moveingshape , Point mypt)
    {
        this.moveingshape = moveingshape;
        this.mypt = mypt;
    }
    
    public void resetshapepoints(Point p)
    {
        this.movingp = p;
        int x = (int ) (-this.mypt.getX() + this.movingp .getX());
        int y = (int ) (-this.mypt.getY() + this.movingp .getY());
        
        if(moveingshape.type != shape.ShapesType.Triangle)
        {
            this.moveingshape.start.x += x;
            this.moveingshape.start.y += y;
            this.moveingshape.end.x += x;
            this.moveingshape.end.y += y;
        }
        
        else
        {
            this.moveingshape.x[0] += x;
            this.moveingshape.x[1] += x;
            this.moveingshape.x[2] += x;
            this.moveingshape.y[0] += y;
            this.moveingshape.y[1] += y;
            this.moveingshape.y[2] += y;           
        }
    }
}