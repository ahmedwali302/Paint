package complete.project;

import java.awt.*;
import java.text.AttributedCharacterIterator;

public class string extends shape 
{
    String j;
    
    public string(Point pt , java.lang.String j)
    {
        super.start = pt;
        this.j = j;
        super.type = ShapesType.String;
        super.color = Color.BLACK;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.drawString( this.j, super.start.x, super.start.y);
    }
    
    @Override
    public  boolean checkcontain(Point pt)
    {
        return false;
    }
            
    @Override
    public  void drawbounds(Graphics2D g ){}
    
    @Override
    public  void fillclip(Graphics2D g, shape e ,Color cl){}
}