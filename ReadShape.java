package complete.project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadShape 
{
    String shapeType,startPoint, endPoint,color, filledColor;
    
    public ReadShape(String shapeType, String startPoint, String endPoint, String color, String filledColor)
    {
        this.color= color;
        this.endPoint = endPoint;
        this.filledColor = filledColor;
        this.shapeType = shapeType;
        this.startPoint = startPoint;
    }

    public shape creatshape(shape Myshape, Graphics2D g2)
    {
        if(this.shapeType.equals("Ovel"))
        {
            List<String> container = new ArrayList<>();
            container = Arrays.asList(this.startPoint.split(","));
            List<String> containr = new ArrayList<>();
            containr = Arrays.asList(this.endPoint.split(","));
            Myshape = new Oval(new Point(Integer.parseInt(container.get(0)),Integer.parseInt(container.get(1))), new Point(Integer.parseInt(containr.get(0)),Integer.parseInt(containr.get(1))));
            Myshape.type = shape.ShapesType.Ovel ;
            String[] Parts = new String[3];
            String CLO = this.color.substring(15,this.color.length() - 1);
            Parts = CLO.split(","); 
            String cr = Parts[0].substring( 2 , Parts[0].length()  );
            String cg = Parts[1].substring( 2 , Parts[1].length()  );
            String cb = Parts[2].substring( 2 , Parts[2].length()  );
            Myshape.color = new Color(Integer.parseInt(cr), Integer.parseInt(cg), Integer.parseInt(cb));
            String[] Part = new String[3];
            String CL = this.filledColor.substring(15,this.filledColor.length() - 1);
            Part = CL.split(","); 
            String r = Part[0].substring( 2 , Parts[0].length());
            String g = Part[1].substring( 2 , Parts[1].length());
            String b = Part[2].substring( 2 , Parts[2].length());
            Myshape.FilledColor = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
            return Myshape;
        }
        
        else if(this.shapeType.equals("Rectangle"))
        {
            List<String> container = new ArrayList<>();
            container = Arrays.asList(this.startPoint.split(","));
            List<String> containr = new ArrayList<>();
            containr = Arrays.asList(this.endPoint.split(","));
            Myshape = new Rectangle(new Point(Integer.parseInt(container.get(0)),Integer.parseInt(container.get(1))), new Point(Integer.parseInt(containr.get(0)),Integer.parseInt(containr.get(1))));
            Myshape.type = shape.ShapesType.Rectangle ;
            String[] Parts = new String[3];
            String CLO = this.color.substring(15,this.color.length() - 1);
            Parts = CLO.split(","); 
            String cr = Parts[0].substring( 2 , Parts[0].length()  );
            String cg = Parts[1].substring( 2 , Parts[1].length()  );
            String cb = Parts[2].substring( 2 , Parts[2].length()  );
            Myshape.color = new Color(Integer.parseInt(cr), Integer.parseInt(cg), Integer.parseInt(cb));
            String[] Part = new String[3];
            String CL = this.filledColor.substring(15,this.filledColor.length() - 1);
            Part = CL.split(","); 
            String r = Part[0].substring( 2 , Parts[0].length());
            String g = Part[1].substring( 2 , Parts[1].length());
            String b = Part[2].substring( 2 , Parts[2].length());
            Myshape.FilledColor = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
            return Myshape;
        }
        
        else if(this.shapeType.equals("RoundRectangle"))
        {
            
            List<String> container = new ArrayList<>();
            container = Arrays.asList(this.startPoint.split(","));
            List<String> containr = new ArrayList<>();
            containr = Arrays.asList(this.endPoint.split(","));
            Myshape = new RoundRectanlge(new Point(Integer.parseInt(container.get(0)),Integer.parseInt(container.get(1))), new Point(Integer.parseInt(containr.get(0)),Integer.parseInt(containr.get(1))));
            Myshape.type = shape.ShapesType.RoundRectangle ;
            String[] Parts = new String[3];
            String CLO = this.color.substring(15,this.color.length() - 1);
            Parts = CLO.split(","); 
            String cr = Parts[0].substring( 2 , Parts[0].length()  );
            String cg = Parts[1].substring( 2 , Parts[1].length()  );
            String cb = Parts[2].substring( 2 , Parts[2].length()  );
            Myshape.color = new Color(Integer.parseInt(cr), Integer.parseInt(cg), Integer.parseInt(cb));
            String[] Part = new String[3];
            String CL = this.filledColor.substring(15,this.filledColor.length() - 1);
            Part = CL.split(","); 
            String r = Part[0].substring( 2 , Parts[0].length());
            String g = Part[1].substring( 2 , Parts[1].length());
            String b = Part[2].substring( 2 , Parts[2].length());
            Myshape.FilledColor = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
            return Myshape;
        }
        
        if(this.shapeType.equals("Line"))
        {
            List<String> container = new ArrayList<>();
            container = Arrays.asList(this.startPoint.split(","));
            List<String> containr = new ArrayList<>();
            containr = Arrays.asList(this.endPoint.split(","));
            Myshape = new Line(new Point(Integer.parseInt(container.get(0)),Integer.parseInt(container.get(1))), new Point(Integer.parseInt(containr.get(0)),Integer.parseInt(containr.get(1))), Color.BLACK);
            Myshape.type = shape.ShapesType.Line ;
            String[] Parts = new String[3];
            String CLO = this.color.substring(15,this.color.length() - 1);
            Parts = CLO.split(","); 
            String cr = Parts[0].substring( 2 , Parts[0].length()  );
            String cg = Parts[1].substring( 2 , Parts[1].length()  );
            String cb = Parts[2].substring( 2 , Parts[2].length()  );
            Myshape.color = new Color(Integer.parseInt(cr), Integer.parseInt(cg), Integer.parseInt(cb));
            String[] Part = new String[3];
            String CL = this.filledColor.substring(15,this.filledColor.length() - 1);
            Part = CL.split(","); 
            String r = Part[0].substring( 2 , Parts[0].length());
            String g = Part[1].substring( 2 , Parts[1].length());
            String b = Part[2].substring( 2 , Parts[2].length());
            Myshape.FilledColor = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
            return Myshape;
        }
         if(this.shapeType.equals("Triangle"))
        {
            List<String> container = new ArrayList<>();
            container = Arrays.asList(this.startPoint.split(","));
            int[ ]x = new int[3];
            int[] y = new int[3];
            for(int i = 0 ; i < container.size() ; i++){
                if(i<3){
                   x[i] = Integer.parseInt(container.get(i));
                }
                else if(i<6){
                    y[i-3] = Integer.parseInt(container.get(i));
                }
            }
            Myshape = new Triangle(x,y);
            ((Triangle)Myshape).addpoint(new Point(x[2] , y[2]));
            Myshape.type = shape.ShapesType.Triangle ;
            String[] Parts = new String[3];
            String CLO = this.color.substring(15,this.color.length() - 1);
            Parts = CLO.split(","); 
            String cr = Parts[0].substring( 2 , Parts[0].length()  );
            String cg = Parts[1].substring( 2 , Parts[1].length()  );
            String cb = Parts[2].substring( 2 , Parts[2].length()  );
            Myshape.color = new Color(Integer.parseInt(cr), Integer.parseInt(cg), Integer.parseInt(cb));
            String[] Part = new String[3];
            String CL = this.filledColor.substring(15,this.filledColor.length() - 1);
            Part = CL.split(","); 
            String r = Part[0].substring( 2 , Parts[0].length());
            String g = Part[1].substring( 2 , Parts[1].length());
            String b = Part[2].substring( 2 , Parts[2].length());
            Myshape.FilledColor = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
            return Myshape;
        }
        
        return null;
        
    }
    
}
