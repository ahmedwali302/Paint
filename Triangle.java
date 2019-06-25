package complete.project;

import java.awt.geom.*;
import java.awt.*;

public class Triangle extends shape 
{
    private int index = 2;
    private Polygon plo;
    
    public Triangle(int[] x, int[] y)
    {
        super.color = Color.BLACK;
        super.type=ShapesType.Triangle;
        super.x[0] = x[0];
        super.x[1] = x[1];
        super.y[0] = y[0];
        super.y[1] = y[1];
    }
    
    public boolean addpoint(Point pt)
    {
        if(index < 3)
        { 
            super.x[2] = (int) pt.getX(); 
            super.y[2] = (int ) pt.getY(); 
            index++; 
            return true;
        }
        
        return false;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
       this.plo = new Polygon(super.x, super.y, this.index);
       g.drawPolygon(super.x, super.y, this.index);
    }
    
    @Override
    public boolean checkcontain(Point p)
    {
       return this.plo.contains(p);
    }
    
    @Override
    public void drawbounds(Graphics2D g )
    {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        this.plo = new Polygon(super.x, super.y, this.index);
        g.draw(plo.getBounds2D());
    }
     
    @Override
    public void fillclip(Graphics2D g, shape e , Color cl)
    {
        super.FilledColor = cl;
        g.setColor(super.FilledColor);
        g.fill(this.plo);
    }
}