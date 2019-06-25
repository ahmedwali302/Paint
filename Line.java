package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class Line extends shape 
{
    public Line(Point Start , Point End ,Color color)
    {
        super.color = Color.BLACK;
        super.color = color;
        super.type = ShapesType.Line;
        
        if(Start.distance(End) < 0)
        {
           super.start = End;
           super.end = Start;
        }
        
        else
        {
           super.start = Start;
           super.end   = End;
        }
    }
    
    @Override
    public void draw(Graphics2D g)
    {   
        g.drawLine((int)super.start.getX(),(int)super.start.getY(),(int)super.end.getX(),(int)super.end.getY());
    }
    
    @Override
    public boolean checkcontain(Point pt)
    {
        Line2D L = new Line2D.Float(super.start, super.end);
        return L.ptLineDist(pt) < 15; 
    }
    
    @Override
    public void drawbounds(Graphics2D g )
    {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        Line2D L = new Line2D.Float(super.start, super.end);
        g.draw(L.getBounds2D());
    }
    
    @Override
    public void fillclip(Graphics2D g, shape e , Color cl){}
}