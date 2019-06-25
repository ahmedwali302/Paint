package complete.project;

import complete.project.shape.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompleteProject 
{
    static PaintPanel pp = new PaintPanel();
    
    public static void main(String[] args) 
    {
        GUI CreateMYGUI = new GUI("GKMH Paint");
        
        ActionListener AL1 = (ActionEvent e) -> 
        {
            switch (((JButton)e.getSource()).getText()) 
            {
                case "Line":
                    pp.setshape(ShapesType.Line);
                    break;
                case "Rectangle":
                    pp.setshape(ShapesType.Rectangle);
                    break;
                case "Round Rectangle":
                    pp.setshape(ShapesType.RoundRectangle);
                    break;
                case "Oval":
                    pp.setshape(ShapesType.Ovel);
                    break;
                case "Pencil":
                    pp.setshape(ShapesType.Pencil);
                    break;
                case "String":
                    pp.setshape(ShapesType.String);
                    break;
                case "Triangle":
                    pp.setshape(ShapesType.Triangle);
                    break;
                default:
                    break;
            }
        };
        
        ActionListener AL2 = (ActionEvent e) -> 
        {
            switch(((JButton)e.getSource()).getText())
            {
                case "Brush Color":
                    pp.setorder(Orders.BrushColor);
                    break;
                case "Fill":
                    pp.setorder(Orders.Fill);
                    break;
                case "Select All":
                    pp.setorder(Orders.SelectAll);
                    break;
                case "Delete":
                    pp.setorder(Orders.Delete);
                    break;
                case "Undo":
                    pp.setorder(Orders.Undo);
                    break;
                case "Redo":
                    pp.setorder(Orders.Redo);
                    break;
                case "Open":
                    pp.setorder(Orders.Open);
                    break;
                case "Save":
                    pp.setorder(Orders.Save);
                    break;
                default:
                    break;
            }
        };
        
        //Add the action Listener to the shapes
        CreateMYGUI.Arc.addActionListener(AL1);
        CreateMYGUI.Arrow.addActionListener(AL1);
        CreateMYGUI.CubicCurve.addActionListener(AL1);
        CreateMYGUI.Ellipse.addActionListener(AL1);
        CreateMYGUI.Line.addActionListener(AL1);
        CreateMYGUI.Pencil.addActionListener(AL1);
        CreateMYGUI.QuadCurve.addActionListener(AL1);
        CreateMYGUI.Rectangle.addActionListener(AL1);
        CreateMYGUI.RoundRectangle.addActionListener(AL1);
        CreateMYGUI.Star.addActionListener(AL1);
        CreateMYGUI.Triangle.addActionListener(AL1);
        
        //Add the action Listener to the properties
        CreateMYGUI.SelectAll.addActionListener(AL2);
        CreateMYGUI.BrushColor.addActionListener(AL2);
        CreateMYGUI.FillShape.addActionListener(AL2);
        CreateMYGUI.Delete.addActionListener(AL2);
        CreateMYGUI.Undo.addActionListener(AL2);
        CreateMYGUI.Redo.addActionListener(AL2);
        CreateMYGUI.Save.addActionListener(AL2);
        CreateMYGUI.Open.addActionListener(AL2);

        //set and add Action Listener to fill with/without draw       
        ActionListener ALS1 = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {pp.setdesion(((JRadioButton)e.getSource()).getText());}
        };
        CreateMYGUI.fillwithdraw.addActionListener(ALS1);
        CreateMYGUI.fillwithoutdraw.addActionListener(ALS1);
                
        //set the paint panel
        pp.setLocation(0, 20);
        pp.setSize(CreateMYGUI.MyPanel.getSize());
        CreateMYGUI.addComponent(pp);
    }
}