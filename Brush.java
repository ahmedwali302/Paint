package complete.project;

import java.awt.*;
import java.awt.geom.*;

public class Brush 
{
    private shape[] shapes ; private shape[] realcontainers = new shape[1000];
    private Area area = null;
    private int index , realindex = 0 ,shapeindex ;
    
    public Brush(shape[] shapes , int index)
    {
        this.shapes = shapes;
        this.index = index;
    }
    
    public void fillshape(Point pt,Color color,Graphics2D g)
    {        
        for(int i= 0; i < this.index;i++)
        {
            if(this.shapes[i].checkcontain(pt))
            {
                realcontainers[realindex] = this.shapes[i];
                shapeindex = i;
                realindex++;
            }
        }
        
        if(realindex == 0) return ;
        
        else
        {
            g.setClip(getrithshape(this.realcontainers[0],g));
            area = new Area(g.getClip());
            
            if(realindex == 1)
            {
                realcontainers[0].FilledColor = color;
                
                for(int i = 0 ; i < index ; i++)
                {
                    if(i != shapeindex)
                    {
                        g.setClip(getrithshape(this.shapes[i],g));
                        area.subtract(new Area(g.getClip()));
                    }
               }
            }
            
            else
            {
                for(int i = 0 ; i < realindex ; i++)
                {
                    g.setClip(getrithshape(this.realcontainers[i],g));
                    area.intersect(new Area(g.getClip()));
                }
            }
        }
        
        realindex = 0;
        g.setClip(area);
    }
    
    Shape getrithshape(shape v , Graphics2D g)
    {
        if(v.type == shape.ShapesType.Ovel)
        {
            return new Ellipse2D.Float(v.start.x, v.start.y , v.end.x - v.start.x , v.end.y - v.start.y);
        }
        
        else if(v.type == shape.ShapesType.RoundRectangle)
        {
            return new RoundRectangle2D.Float(v.start.x, v.start.y , v.end.x - v.start.x , v.end.y - v.start.y,25,25);
        }
        else if(v.type == shape.ShapesType.Triangle)
        {
            return new Polygon(v.x, v.y ,3);
        }
        
        else if(v.type == shape.ShapesType.Rectangle)
        {
            return new Rectangle2D.Float(v.start.x, v.start.y , v.end.x - v.start.x , v.end.y - v.start.y);
        }
        
        return null;
    }
    
    public void fillshape(shape myshape ,Graphics2D g , Color color)
    {
        g.setColor(color);
        myshape.FilledColor =color;
        g.setClip(getrithshape(myshape, g));
        g.fill(g.getClip());
    }
}