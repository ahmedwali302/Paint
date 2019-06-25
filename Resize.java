package complete.project;

import java.awt.*;

public class Resize 
{
    private final shape Reshape;
    
    public Resize(shape Reshape)
    {
        this.Reshape = Reshape;
    }
    
    public boolean fristpoint(Point pt)
    {
        return this.Reshape.x[0] + 15 > pt.x  && this.Reshape.y[0] + 15 > pt.y
            && this.Reshape.x[0] - 15 < pt.x  && this.Reshape.y[0] - 15 < pt.y 
            && this.Reshape.x[0] + 15 > pt.x  && this.Reshape.y[0] - 15 < pt.y
            && this.Reshape.x[0] - 15 < pt.x  && this.Reshape.y[0] + 15 > pt.y;
    }
    
    public boolean secondpoint(Point pt)
    {
        return (this.Reshape.x[1] + 15 > pt.x  && this.Reshape.y[1] + 15 > pt.y) 
             &&(this.Reshape.x[1] - 15 < pt.x  && this.Reshape.y[1] - 15 < pt.y)
             &&(this.Reshape.x[1] + 15 > pt.x  && this.Reshape.y[1] - 15 < pt.y)
             &&(this.Reshape.x[1] - 15 < pt.x  && this.Reshape.y[1] + 15 > pt.y);
    }
     
    public boolean thirdpoint(Point pt)
    {
      return (this.Reshape.x[2] + 15 > pt.x  && this.Reshape.y[2] + 15 > pt.y) 
           &&(this.Reshape.x[2] - 15 < pt.x  && this.Reshape.y[2] - 15 < pt.y )
           &&(this.Reshape.x[2] + 15 > pt.x  && this.Reshape.y[2] - 15 < pt.y )
           &&(this.Reshape.x[2] - 15 < pt.x  && this.Reshape.y[2] + 15 > pt.y);
    }
     
    public void resizetriangle (Point pt)
    {
        int[] x = this.Reshape.x ; 
        int[] y = this.Reshape.y;
        
        //00 11 01 10
        if (x[0] + 15 > pt.x  && y[0] + 15 > pt.y 
         && x[0] - 15 < pt.x  && y[0] - 15 < pt.y 
         && x[0] + 15 > pt.x  && y[0] - 15 < pt.y 
         && x[0] - 15 < pt.x  && y[0] + 15 > pt.y)
        {
            x[0] = pt.x;
            y[0] = pt.y;
        }
        
        //00 01 10 11
        else if(x[1] + 15 > pt.x  && y[1] + 15 > pt.y 
             && x[1] + 15 > pt.x  && y[1] - 15 < pt.y 
             && x[1] - 15 < pt.x  && y[1] + 15 > pt.y 
             && x[1] - 15 < pt.x  && y[1] - 15 < pt.y)
        {
            x[1] = pt.x;
            y[1] = pt.y;
        }
        
        //00 01 10 11
        else if (x[2] + 15 > pt.x  && y[2] + 15 > pt.y
              && x[2] + 15 > pt.x  && y[2] - 15 < pt.y
              && x[2] - 15 < pt.x  && y[2] + 15 > pt.y
              && x[2] - 15 < pt.x  && y[2] - 15 < pt.y){
            x[2] = pt.x;
            y[2] = pt.y;
        }
        else return;
    }
    
    public boolean checkRight(Point pt)
    {
        if(Reshape.type == shape.ShapesType.Triangle){}
        return this.Reshape.end.x - 15 < pt.getX() && this.Reshape.end.y - 15 > pt.getY() && this.Reshape.start.y + 15 < pt.getY();
    }
    
    public boolean checkDown(Point pt)
    {
        return this.Reshape.end.y - 15 < pt.getY() && this.Reshape.end.x - 15 > pt.getX() && this.Reshape.start.x + 15 < pt.getX();
    }

    public boolean checkLeft(Point pt)
    {
        return this.Reshape.start.x + 15 > pt.getX() && this.Reshape.start.y + 15 < pt.getY() && this.Reshape.end.y - 15 > pt.getY();
    }
    
    public boolean checkUpper(Point pt)
    {
        return this.Reshape.start.y + 15 > pt.getY() && this.Reshape.end.x - 15 > pt.getX() && this.Reshape.start.x + 15 < pt.getX();
    }
    
     public boolean checkUpperLeft(Point pt)
    {
        return this.Reshape.start.y + 15 > pt.getY() && this.Reshape.start.x + 15 > pt.getX();
    }
     
     public boolean checkDownLeft(Point pt)
    {
        return this.Reshape.end.y - 15 < pt.getY() && this.Reshape.start.x + 15 > pt.getX();
    }
    
     public boolean checkUpperRight(Point pt)
    {
        return this.Reshape.start.y + 15 > pt.getY() && this.Reshape.end.x - 15 < pt.getX();
    }
     
      public boolean checkDownRight(Point pt)
    {
        return this.Reshape.end.y - 15 < pt.getY() && this.Reshape.end.x - 15 < pt.getX();
    }
     
    public void resizeDown(Point pt)
    {
        int y  = pt.y - this.Reshape.end.y;
        this.Reshape.end.y += y;
    }
    
    public void resizeLeft(Point pt)
    {
        int x  = pt.x - this.Reshape.start.x;
        this.Reshape.start.x += x;
    }
    
    public void resizeUpper(Point pt)
    {
        int y  = pt.y - this.Reshape.start.y;
        this.Reshape.start.y += y;
    }
    
    public void resizeRight(Point pt)
    {
        int x  = pt.x - this.Reshape.end.x;
        this.Reshape.end.x += x;
    }
    
    public void resizeUpperLeft(Point pt)
    {
        int y = pt.y - this.Reshape.start.y;
        int x = pt.x - this.Reshape.start.x;
        this.Reshape.start.y += y;
        this.Reshape.start.x += x;
    }
    
    public void resizeDownLeft(Point pt)
    {
        int y  = pt.y - this.Reshape.end.y;
        int x  = pt.x - this.Reshape.start.x;
        this.Reshape.end.y += y;
        this.Reshape.start.x += x;
    }
    
    public void resizeUpperRight(Point pt)
    {
        int x  = pt.x - this.Reshape.end.x;
        this.Reshape.end.x += x;
        int y  = pt.y - this.Reshape.start.y;
        this.Reshape.start.y += y;
    }
    
    public void resizeDownRight(Point pt)
    {
        int y  = pt.y - this.Reshape.end.y;
        this.Reshape.end.y += y;
        int x  = pt.x - this.Reshape.end.x;
        this.Reshape.end.x += x;
    }
}