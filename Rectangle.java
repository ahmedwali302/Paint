package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class Rectangle extends shape
{
    private final int width ,height , xposition , yposition;
  
    public Rectangle(Point start , Point end)
    {
        super.start = start;
        super.end = end;
        super.color = Color.BLACK;
        super.type = ShapesType.Rectangle;
        
        if (super.end.getX() > super.start.getX())
            this.xposition = (int) super.start.getX(); this.width = (int) (super.end.getX() - super.start.getX());
        
        else 
            this.xposition = (int) super.end.getX(); this.width = (int) (super.start.getX() - super.end.getX()); 
        
        if (super.end.getY() < super.start.getY())
        {
            this.yposition = (int) super.end.getY(); 
            this.height = (int) (super.start.getY() - super.end.getY());
        }   
        
        else 
        {
            this.yposition = (int) super.start.getY();
            this.height = (int) (super.end.getY() - super.start.getY()); 
        } 
        
        super.start = new Point(xposition ,yposition);
        super.end = new Point(xposition + width ,yposition + height);
    }
    
    @Override
    public void draw (Graphics2D g)
    {     
       g.drawRect( super.start.x,  super.start.y, super.end.x -super.start.x, super.end.y - super.start.y);
    }
    
    
    @Override
    public boolean checkcontain(Point p)
    {
        return super.start.x < p.getX() && super.start.y < p.getY()
             &&super.end.x > p.getX() &&super.end.y > p.getY();
    }
    
    @Override
    public void drawbounds(Graphics2D g )
    {
       g.setColor(Color.BLACK);
       g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
       Rectangle2D r = new Rectangle2D.Float(super.start.x,  super.start.y,super.end.x -super.start.x, super.end.y - super.start.y);
       g.draw(r.getBounds2D());
    }
    
    @Override
    public void fillclip(Graphics2D g, shape e , Color cl)
    {
        g.setClip(e.start.x, e.start.y, e.end.x - e.start.x, e.end.y - e.start.y);
        super.FilledColor = cl ;
        g.setColor(super.FilledColor);
        g.fill(g.getClip());
    }
    
    public boolean checkintersection(shape x )
    {
        Rectangle2D r = new Rectangle2D.Float(super.start.x,  super.start.y,super.end.x -super.start.x, super.end.y - super.start.y);
        
        if(x.type == ShapesType.Line)
        {
            if(r.intersectsLine(x.start.x, x.start.y, x.end.x, x.end.y))    return true;
        }
        
        else if(x.type == ShapesType.Triangle)
        {
            if((new Polygon(x.x, x.y, 3)).intersects(r))    return true;
        }
        
        else if(x.type == ShapesType.Ovel)
        {
            if((new Ellipse2D.Float(x.start.x, x.start.y, x.end.x - x.start.x, x.end.y - x.start.y)).intersects(r))     return true;
        }
        
        else if(x.type == ShapesType.RoundRectangle)
        {
            if((new RoundRectangle2D.Float(x.start.x, x.start.y, x.end.x - x.start.x, x.end.y - x.start.y , 25 , 25)).intersects(r))    return true;
        }
        
        else if(x.type == ShapesType.Rectangle)
        {
            if(r.intersects(x.start.x, x.start.y, x.end.x - x.start.x, x.end.y - x.start.y))    return true;
        }
        
        return false;
    }
}