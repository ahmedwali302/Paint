package complete.project;

import java.awt.*;

public class Anyshape extends shape 
{
    private Polygon plo;
    
    public Anyshape()
    {
        super.polygonIndex=0;
        super.type = ShapesType.Anyshape;
    }
    
    @Override
    public  void draw(Graphics2D g)
    {
       this.plo = new Polygon(super.polygonx, super.polygony, super.polygonIndex);
       g.drawPolygon(this.plo);
    }
    
    public boolean addpoints(Point pt)
    {
        System.out.println("Yes");
        
        if(super.polygonIndex < 100)
        { 
            super.polygonx[super.polygonIndex] = pt.x;  
            super.polygony[super.polygonIndex] = pt.y; 
            super.polygonIndex++;
            System.out.println("Yes");
            return true;
        } 
        
        return false;
    }
    
    @Override
    public boolean checkcontain(Point pt){
        return false;//this.plo.contains(pt);
    }
    
    @Override
    public void drawbounds(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        this.plo = new Polygon(super.polygonx, super.polygony, super.polygonIndex);
        g.draw(plo.getBounds2D());
    }
    
    Override
    public  void fillclip(Graphics2D g, shape e ,Color cl){
         super.FilledColor = cl ;
         g.setColor(super.FilledColor);
         g.fill(this.plo);
    }
}
