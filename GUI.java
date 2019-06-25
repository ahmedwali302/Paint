package complete.project;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

public class GUI extends JFrame
{    
    public JPanel MyPanel   = new JPanel();
    
    JMenuBar MyMenu  = new JMenuBar();
    JMenu File       = new JMenu("File");
    JMenu Edit       = new JMenu("Edit");
    JMenu Shapes     = new JMenu("Shapes");
    JMenu properties = new JMenu("properties");
    
    public JButton Open ;
    public JButton Save ;
    public JButton ZoomIn ;
    public JButton ZoomOut;
    public JButton Rectangle ;
    public JButton RoundRectangle ;
    public JButton Ellipse ;
    public JButton Line ;
    public JButton Pencil ;
    public JButton QuadCurve ;
    public JButton CubicCurve ;
    public JButton Arc ;
    public JButton Triangle ;
    public JButton Arrow ;
    public JButton Star ;
    public JButton BrushColor ;
    public JButton FillShape ;
    public JButton Delete ;
    public JButton Undo ;
    public JButton Redo ;
    public JButton SelectAll ;
    public JRadioButton fillwithdraw;
    public JRadioButton fillwithoutdraw;
    
    public GUI(String title)
    {
       //Frame
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);    
        
        //File Menu 
        ButtonGroup FileGroup = new ButtonGroup();
        Open = new JButton("Open");
        Save = new JButton("Save");
        FileGroup.add(Open);
        FileGroup.add(Save);
        File.add(Open);
        File.add(Save);
        
        //Edit Menu
        ButtonGroup EditGroup = new ButtonGroup();
        ZoomIn  = new JButton("ZoomIn ");
        ZoomOut = new JButton("ZoomOut");
        EditGroup.add(ZoomIn);
        EditGroup.add(ZoomOut);
        Edit.add(ZoomIn);
        Edit.add(ZoomOut);
        
        //Shapes Menu
        ButtonGroup Shape = new ButtonGroup();
        Rectangle      = new JButton("Rectangle");
        RoundRectangle = new JButton("Round Rectangle");
        Ellipse        = new JButton("Oval");
        Line           = new JButton("Line");
        Pencil         = new JButton("Pencil");
        QuadCurve      = new JButton("Quad Curve");
        CubicCurve     = new JButton("Cubic Curve");
        Arc            = new JButton("Arc");
        Triangle       = new JButton("Triangle");
        Arrow          = new JButton("Arrow");
        Star           = new JButton("String");
        fillwithdraw   = new JRadioButton("Fill  with draw");
        fillwithoutdraw   = new JRadioButton("Fill  without draw");
        Shape.add(Arc);
        Shape.add(Ellipse);
        Shape.add(Rectangle);
        Shape.add(RoundRectangle);
        Shape.add(Line);
        Shape.add(Pencil);
        Shape.add(QuadCurve);
        Shape.add(CubicCurve);
        Shape.add(Triangle);
        Shape.add(Arrow);
        Shape.add(Star);
        Shapes.add(Arc);
        Shapes.add(Ellipse);
        Shapes.add(Rectangle);
        Shapes.add(RoundRectangle);
        Shapes.add(Line);
        Shapes.add(Pencil);
        Shapes.add(QuadCurve);
        Shapes.add(CubicCurve);
        Shapes.add(Triangle);
        Shapes.add(Arrow);
        Shapes.add(Star);
        ButtonGroup b = new ButtonGroup();
        b.add(fillwithdraw);
        b.add(fillwithoutdraw);
        Shapes.add(fillwithdraw);
        Shapes.add(fillwithoutdraw);
        
        //properties
        ButtonGroup propertie = new ButtonGroup();
        BrushColor = new JButton("Brush Color");
        FillShape = new JButton("Fill");
        Delete = new JButton("Delete") ;
        Undo = new JButton("Undo");
        Redo = new JButton("Redo");
        SelectAll =new JButton("Select All");
        propertie.add(BrushColor);
        propertie.add(FillShape);
        propertie.add(Delete);
        propertie.add(Undo);
        propertie.add(Redo);
        propertie.add(SelectAll);
        properties.add(BrushColor);
        properties.add(FillShape);
        properties.add(Delete);
        properties.add(Undo);
        properties.add(Redo);
        properties.add(SelectAll);
        
        //MenuBar MyMenu
        MyMenu.add(File);
        MyMenu.add(Edit);
        MyMenu.add(Shapes);
        MyMenu.add(properties);
        MyMenu.setLocation(0, 0);
        MyMenu.setVisible(true);
        MyMenu.setSize(MyPanel.getWidth(), 20);

        //Panel MyPanel
        MyPanel.setBackground(Color.white);
        MyPanel.setLayout(null);   
        MyPanel.setLocation(0, 20);
        MyPanel.add(MyMenu);
        MyPanel.setVisible(true);
        MyPanel.setSize(this.getWidth(), this.getHeight());
        
        //JPanel
        this.add(MyPanel);
        this.setVisible(true);
    }
}