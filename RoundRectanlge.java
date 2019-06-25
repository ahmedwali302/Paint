package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class RoundRectanlge extends shape 
{
    private final int width ,height , xposition , yposition;
    private RoundRectangle2D r;
    
    public RoundRectanlge(Point start , Point end)
    {
        super.start = start;
        super.end = end;
        super.color = Color.BLACK;
        super.type = ShapesType.RoundRectangle;
        
        if ( super.end.getX() > super.start.getX() )
        {
            this.xposition = (int) super.start.getX(); 
            this.width = (int) (super.end.getX() - super.start.getX());
        }  
        
        else 
        {
            this.xposition = (int) super.end.getX(); 
            this.width = (int) (super.start.getX() - super.end.getX()); 
        }
        
        if ( this.end.getY() < super.start.getY() )
        {
            this.yposition = (int) super.end.getY(); 
            this.height = (int) (super.start.getY() - super.end.getY());
        }   
        
        else 
        {
            this.yposition = (int) super.start.getY();
            this.height = (int) (super.end.getY() - super.start.getY()); 
        } 
        
        super.start = new Point(xposition, yposition);
        super.end = new Point(xposition+width, yposition+height);
    }
    
    @Override
    public void draw (Graphics2D g)
    {
        this.r = new RoundRectangle2D.Float(super.start.x,  super.start.y, super.end.x -super.start.x, super.end.y - super.start.y, 25, 25);
        g.drawRoundRect( super.start.x,  super.start.y, super.end.x -super.start.x, super.end.y - super.start.y, 25, 25);
    }
    
    
    @Override
    public boolean checkcontain(Point p)
    {
       return super.start.x < p.getX() && super.start.y < p.getY()
                &&super.end.x > p.getX() &&super.end.y > p.getY();
    }
    @Override
     public void drawbounds(Graphics2D g ){
       g.setColor(Color.BLACK);
       g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
       this.r = new RoundRectangle2D.Float(super.start.x,  super.start.y, super.end.x -super.start.x, super.end.y - super.start.y, 25, 25);
       g.draw(r.getBounds2D());
    }
     
    @Override
    public void fillclip(Graphics2D g, shape e , Color cl){
        g.setClip(this.r);
        super.FilledColor = cl ;
        g.setColor(super.FilledColor);
        g.fill(g.getClip());
    }
}