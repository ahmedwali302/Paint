package complete.project;

import java.awt.*;
import java.awt.geom.*;

public abstract class shape 
{
    public enum ShapesType { Triangle, Rectangle, RoundRectangle, Ovel, Line, Pencil, QuadCurve, CubicCurve, Star, Arrow, Arc, String };
    public enum Orders{ SelectAll, BrushColor, Fill, Delete, Undo, Redo, Save, Open };
    public enum ResizeType{ UpperLeft, UpperRight, DownLeft, DownRight, Upper, Down, Left, Right };
    public int[] x = new int [3];
    public int[] y = new int [3];
    public Point start ,end;
    public Color color;
    public Color FilledColor = Color.white;
    public ShapesType type;
    public ResizeType resizeType;
      
    public void draw(Graphics2D g,Color color)
    {
        this.color = color;
        g.setColor(color);
        draw(g);
    }
      
    public abstract void draw(Graphics2D g);
    public abstract boolean checkcontain(Point pt);
    public abstract void drawbounds(Graphics2D g );
    public abstract void fillclip(Graphics2D g, shape e ,Color cl);
}