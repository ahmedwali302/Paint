package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class Pencil extends shape
{
    private Point Start;
    private static Point[] p = new Point[1000];
    private static int index=0;
    
    public Pencil( Point Start)
    {
        super.type = ShapesType.Pencil;
        super.color = Color.BLACK;
        this.Start=Start;
    }
    
    public boolean addpoint(Point pt)
    {
        if(index < 1000) 
        { 
            p[index++] = pt;  
            return true;
        }
        
        return false;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
       if(index == 1)   g.drawLine((int)Start.getX(),(int)Start.getY(),(int)p[0].getX(),(int)p[0].getY());
       else             g.drawLine((int)this.p[index-2].getX(),(int)this.p[index-2].getY(),(int)this.p[index-1].getX(),(int)this.p[index -1].getY());
    }

    public void drawall(Graphics2D g)
    {
        g.drawLine((int)Start.getX(),(int)Start.getY(),(int)p[0].getX(),(int)p[0].getY());
        
        for(int i = 0 ; i < index ; i++)
        {
             g.drawLine((int)p[i-1].getX(),(int)p[i-1].getY(),(int)p[i].getX(),(int)p[i].getY());
        }
    }

    public void reset(Point[] a , int n)
    {
        Pencil.index = n;
        Pencil.p = a;
    }
    
    @Override
    public boolean checkcontain(Point pt)
    {
        for(int i = 0 ; i < Pencil.index ; i ++)
        {
            if(pt.getX() == p[i].getX() && pt.getY() == p[i].getY()) return true;
        }
        
        return false;
    }
    
    @Override
    public void drawbounds(Graphics2D g)
    {
       g.setColor(Color.BLACK);
       g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));  
    }
    
    @Override
    public void fillclip(Graphics2D g, shape e , Color cl){}
}