package complete.project;

import complete.project.shape.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PaintPanel extends JPanel 
{
    private GUI vx;
    private ShapesType shape;
    private String q = "";
    private ResizeType Resizetype;
    private BufferedImage image, secondimage;
    private shape MyShape , selectshape; 
    int[] x = new int [2]; 
    int[] y = new int [2];
    private shape[] containers = new shape[1000];
    private shape[] newcontainers = new shape[1000];
    private int xstart , ystart , index = 0 , shapeindex ,tflag = 0 , selectedindex = 0;
    private Graphics2D g;
    private Color selectedcolor = Color.BLACK;
    private Orders order;
    private Move mshape;
    private Point mainpoint;
    private boolean shapecatched = false , dragged = true; 
    private Resize Resizable;
    private Brush brush;
    private Area area;
    private Stack<shape> shapes = new Stack<>();
    private shape[] selectedshapes = new shape[1000];
    
    AffineTransform k = new AffineTransform();
    
    //Mouse Listener
    MouseListener ML  = new MouseListener() 
    {          
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            dragged =false;
            System.out.println(order);
            
            if(order == Orders.Fill)
            {  
                g = image.createGraphics();
                brush = new Brush(containers, index);
                brush.fillshape(new Point(e.getX(),e.getY()), selectedcolor, g);
                g.setColor(selectedcolor);
                g.fill(g.getClip());
                repaint();
                order = null;
            }
            
            else
            {
               if(shape == ShapesType.Triangle && tflag == 1)
               {
                    image = copyimage(secondimage);
                    MyShape = containers[index-1];
                    ((Triangle)MyShape).addpoint(new Point(e.getX() , e.getY()));
                    MyShape.draw(image.createGraphics(),selectedcolor);
                    if(q.equals("Fill  with draw") && q != "")   MyShape.fillclip(image.createGraphics(), MyShape,selectedcolor);
                    index--;
                    tflag = 0;
                    repaint();
                }
                
                else if(index != 0)
                {
                    System.out.println(index);
                    
                    for(int i = 0 ; i < index ; i++ )
                    {
                        if(containers[i].checkcontain(new Point(e.getX() , e.getY())))
                        {
                            shapecatched = true;
                            shapeindex = i;
                            break;
                        }
                    }
                    
                    if(shapecatched)
                    {
                        if(order == Orders.Delete)
                        {
                            image = copyimage(secondimage);
                            shapes.push(containers[shapeindex]);
                            containers[shapeindex] = containers[index - 1];
                            containers[index - 1] = null;
                            index--;
                            repaint();
                            order = null;
                        }
                        
                        else
                        {
                            containers[shapeindex].drawbounds(image.createGraphics());
                            repaint();
                        }
                    }
                } 
            }
        }
              
        @Override
        public void mousePressed(MouseEvent e) 
        {
            dragged = false;
            
            for(int i = 0 ; i < index ; i++ )
            {
                if(containers[i].checkcontain(new Point(e.getX() , e.getY())))
                {
                  shapecatched = true;
                  shapeindex = i;
                  break;
                }
            }
            
            if(shapecatched)
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                mainpoint= new Point(e.getX() , e.getY());
                secondimage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
                
                for(int  i = 0 ; i < index ; i ++)
                {
                    if(i != shapeindex)
                    {
                        containers[i].draw(secondimage.createGraphics(),containers[i].color);                
                        containers[i].fillclip(secondimage.createGraphics(),containers[i], containers[i].FilledColor);             
                    }
                }
                repaint();
            }
            
            else
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                shapeindex = -1;
                shapecatched = false;
                secondimage = copyimage(image);
                xstart = e.getX();
                ystart = e.getY();
                mainpoint= new Point(xstart,ystart);
                
                if(shape == ShapesType.Pencil)
                {
                   image = copyimage(secondimage);
                   MyShape = new Pencil(null);
                }
            }        
        }
        
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if(!shapecatched && dragged)
            {
                order = null;
                containers[index] = MyShape;
                index++;
                
                // reset the containers of the pecil 
                if(shape == ShapesType.Pencil)
                {
                    Point[] a =new Point [1000];
                    int n = 0;
                    ((Pencil)MyShape).reset(a,n);
                }
            }
            
            else
            { 
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                shapecatched = false;   
            }
            
            dragged =false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));}

        @Override
        public void mouseExited(MouseEvent e) {setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));}
    };
   
    //Mouse Motion
    MouseMotionListener MML = new MouseMotionListener() 
    {
        //g = image.createGraphics();
        @Override
        public void mouseDragged(MouseEvent e) 
        {    
            dragged =true;
            
            if(order == Orders.SelectAll)
            {
                    dragged =false;
                    image = copyimage(secondimage);
                    selectshape = new Rectangle( new Point(xstart,ystart), new Point(e.getX(), e.getY()));
                    selectshape.color = selectedcolor;
                    selectshape.draw(image.createGraphics(), selectedcolor);
                    
                    for(int i = 0 ; i < index ; i++)
                    {
                        if(((Rectangle)selectshape).checkintersection(containers[i]))   containers[i].drawbounds(image.createGraphics());
                    }
                    repaint();
            }  
            
            else if(shapecatched)
            {
                Resizable = new Resize(containers[shapeindex]);
                
                if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkDown(new Point(e.getX(),e.getY())) ) 
                {
                    image = copyimage(secondimage);
                    Resizable.resizeDown(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }  
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkUpperLeft(new Point( e.getX() , e.getY() )))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeUpperLeft(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkLeft(new Point(e.getX(),e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeLeft(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkUpper(new Point(e.getX(),e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeUpper(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkDownLeft(new Point(e.getX() , e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeDownLeft(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkRight(new Point(e.getX(),e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeRight(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkUpperRight(new Point(e.getX() , e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeUpperRight(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else if(containers[shapeindex].type!=ShapesType.Triangle&&Resizable.checkDownRight(new Point(e.getX() , e.getY())))
                {
                    image = copyimage(secondimage);
                    Resizable.resizeDownRight(new Point(e.getX() , e.getY()));
                    containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);   
                    
                    if(containers[shapeindex].FilledColor != Color.WHITE)   
                        containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                    repaint();
                }
                
                else
                {
                    if(containers[shapeindex].type == ShapesType.Triangle)
                    {
                        if(Resizable.fristpoint( new Point(e.getX() , e.getY()))
                         ||Resizable.secondpoint(new Point(e.getX() , e.getY()))
                         ||Resizable.thirdpoint(new Point(e.getX() , e.getY())))
                        {
                            image = copyimage(secondimage);
                            Resizable.resizetriangle(new Point(e.getX() , e.getY()));
                            containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                            
                            if(containers[shapeindex].FilledColor != Color.WHITE)   
                                containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);

                               repaint();
                        }
                        
                        else
                        {
                            image = copyimage(secondimage);
                            mshape = new Move(containers[shapeindex], mainpoint);
                            mshape.resetshapepoints(new Point(e.getX() , e.getY()));
                            containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                            
                            if(containers[shapeindex].FilledColor != Color.WHITE)   
                                containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                            
                            mainpoint = new Point(e.getX() , e.getY());
                            repaint();
                        }
                    }
                    else
                    {
                        image = copyimage(secondimage);
                        mshape = new Move(containers[shapeindex], mainpoint);
                        mshape.resetshapepoints(new Point(e.getX() , e.getY()));
                        containers[shapeindex].draw(image.createGraphics(),containers[shapeindex].color);
                        
                        if(containers[shapeindex].FilledColor != Color.WHITE)   
                            containers[shapeindex].fillclip(image.createGraphics(),containers[shapeindex], containers[shapeindex].FilledColor);
                        
                        mainpoint = new Point(e.getX() , e.getY()); 
                        repaint();
                    }
                }
            }
            else    
            switch(shape)
            {
                case Line:
                    image = copyimage(secondimage);
                    MyShape = new Line( new Point(xstart,ystart), new Point(e.getX(), e.getY()),selectedcolor);
                    MyShape.draw(image.createGraphics(), selectedcolor);
                    repaint();
                    break;
                case Ovel:
                    image = copyimage(secondimage);
                    MyShape = new Oval(new Point(xstart,ystart), new Point(e.getX(), e.getY()));
                    MyShape.color = selectedcolor;
                    MyShape.draw(image.createGraphics(), selectedcolor);
                    System.out.println(q);
                    if(q.equals("Fill  with draw") && q!= "")   MyShape.fillclip(image.createGraphics(), MyShape,selectedcolor);
                    repaint();
                    break;
                case Pencil:
                    MyShape = new Pencil(new Point( xstart , ystart ));
                    ((Pencil)MyShape).addpoint(new Point(e.getX(),e.getY()));
                    MyShape.color = selectedcolor;
                    MyShape.draw(image.createGraphics(), selectedcolor);
                    repaint();
                    break;
                case Rectangle:
                    image = copyimage(secondimage);
                    MyShape = new Rectangle( new Point(xstart,ystart), new Point(e.getX(), e.getY()));
                    MyShape.color = selectedcolor;
                    MyShape.draw(image.createGraphics(), selectedcolor);
                     if(q.equals("Fill  with draw") && q!= ""){
                        MyShape.fillclip(image.createGraphics(), MyShape,selectedcolor);
                    }
                    repaint();
                    break;
                case RoundRectangle:
                    image = copyimage(secondimage);
                    MyShape = new RoundRectanlge(new Point(xstart,ystart), new Point(e.getX(), e.getY()));
                    MyShape.color = selectedcolor;
                    MyShape.draw(image.createGraphics(), selectedcolor);
                    if(q.equals("Fill  with draw") && q!= ""){
                        MyShape.fillclip(image.createGraphics(), MyShape,selectedcolor);
                    }
                    repaint();
                    break;
                case Triangle:
                    image = copyimage(secondimage);
                    x[0] = xstart;   y[0] = ystart;
                    x[1] = e.getX(); y[1] = e.getY();
                    MyShape = new Triangle(x, y);
                    MyShape.color = selectedcolor;
                    MyShape.draw(image.createGraphics(), selectedcolor);
                    tflag = 1;
                    repaint();
                    break;
                case String:
                    image = copyimage(secondimage);
                    java.lang.String yu = JOptionPane.showInputDialog("Enter your String");
                    MyShape = new string(new Point(e.getX() , e.getY()), yu);
                    MyShape.draw(image.createGraphics(),selectedcolor);                   
                    repaint();
                    break;
                case Arc:
                    break;
                case Arrow:
                    break;
                case CubicCurve:
                    break;
                case QuadCurve:
                    break;
                case Star:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) 
        {
            boolean getin = false;
            
            for(int i = 0 ; i < index ; i++)
            {  
                if(containers[i] .checkcontain(new Point(e.getX(), e.getY())))
                {
                    getin = true;
                    MyShape = containers[i];
                    break;
                }
            }
            
            if(getin)
            {              
                if(MyShape.type != ShapesType.Triangle && MyShape.end.y - 15 < e.getY() && MyShape.end.x - 15 < e.getX())
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));                
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.end.x - 15 < e.getX() && MyShape.end.y - 15 > e.getY() && MyShape.start.y + 15 < e.getY())                
                    setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));                
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.end.y - 15 < e.getY() && MyShape.end.x - 15 > e.getX() && MyShape.start.x + 15 < e.getX()               
                    setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));                
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.start.x + 15 > e.getX() && MyShape.start.y + 15 < e.getY() && MyShape.end.y - 15 > e.getY())                
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));             
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.start.y + 15 > e.getY() && MyShape.end.x - 15 > e.getX() )
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));              
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.start.y + 15 > e.getY() && MyShape.end.x - 15 < e.getX())
                    setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));                
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.start.y + 15 > e.getY() && MyShape.start.x + 15 > e.getX())
                    setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));               
                
                else if(MyShape.type != ShapesType.Triangle && MyShape.end.y - 15 < e.getY() && MyShape.start.x + 15 > e.getX())
                    setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));               
                
                else
                {
                    Resizable = new Resize(MyShape);
                    
                    if(MyShape.type == ShapesType.Triangle )
                    {
                        if (Resizable.fristpoint(new Point (e.getX(),e.getY()))
                         ||Resizable.secondpoint(new Point (e.getX(),e.getY()))
                         ||Resizable.thirdpoint(new Point (e.getX(),e.getY())))
                        {     
                           setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        }
                        
                        else    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
                    }
                    
                    else  setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }
            
            else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    };

    //contractor
    public PaintPanel()
    {
        super();
        this.setBackground(Color.white);
        this.addMouseListener(ML);
        this.addMouseMotionListener(MML);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(1, 1, this.getWidth() -2, this.getHeight() -2);
        if(image == null) image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        g.drawImage(image, 0, 0, null);
    }
    
    //get the a copy of data to anthor image
    public BufferedImage copyimage(BufferedImage image)
    {
        if(image == null) return null;
        ColorModel c = image.getColorModel();
        //System.out.println(c.isAlphaPremultiplied());
        boolean v = c.isAlphaPremultiplied();
        WritableRaster rs  =  image.copyData(null);
        return new BufferedImage(c, rs, v, null);
    } 
    
    //clear all shapes
    public void clear()
    {
        image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        secondimage = copyimage(image);
        newcontainers = containers;
        repaint();
    }
    
    //get type of shape what i 'll draw 
    public void setshape(ShapesType x)
    {
        this.shape = x;
        this.order = null;
    }
    
    //get order
    public void setorder(Orders order) 
    {
        this.order = order;
        System.out.println(this.order );
        
        if(this.order == Orders.BrushColor)     selectedcolor = JColorChooser.showDialog(this, "pick a color", Color.yellow);
        
        else if(this.order == Orders.Undo && index != 0)
        {
            shapes.push(containers[index - 1]);
            containers[index - 1] = null;
            index--;
            secondimage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
            
            for(int  i = 0 ; i < index ; i ++)
            {
                containers[i].draw(secondimage.createGraphics(),containers[i].color);                
                containers[i].fillclip(secondimage.createGraphics(),containers[i], containers[i].FilledColor);             
            }
            
            image = copyimage(secondimage);
            repaint();
        }
        
        else if(this.order == Orders.Redo && !shapes.empty())
        {
            //image = copyimage(secondimage);
            containers[index ] =  shapes.pop();
            //containers[index] .draw(image.createGraphics(),containers[index].color );
            index++;
            secondimage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
            
            for(int  i = 0 ; i < index ; i ++)
            {
                containers[i].draw(secondimage.createGraphics(),containers[i].color);                
                containers[i].fillclip(secondimage.createGraphics(),containers[i], containers[i].FilledColor);             
            }
            
            image = copyimage(secondimage);
            repaint();
        }
        
        else if (this.order == Orders.Save)
        {
            DocumentBuilderFactory a = DocumentBuilderFactory.newInstance() ;
            DocumentBuilder f = null ;
            
            try 
            {
               f = a.newDocumentBuilder() ;
            } 
            
            catch (ParserConfigurationException ex) 
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Document v = (Document) f.newDocument();
            Element element  = v.createElement("Developer");
            v.appendChild(element);
            
            for(int i = 0 ; i < index ; i++)
            {
                Element type  = v.createElement("type");
                type.appendChild(v.createTextNode(containers[i].type.toString()));
                element.appendChild(type);
                
                if(containers[i].type != ShapesType.Triangle )
                {
                    Element startpoint = v . createElement("start");
                    startpoint.appendChild(v.createTextNode(Integer.toString(containers[i].start.x )+ "," +Integer.toString(containers[i].start.y ) ));
                    element.appendChild(startpoint);  
                    Element endpoint = v . createElement("end");
                    endpoint.appendChild(v.createTextNode(Integer.toString(containers[i].end.x )+ "," +Integer.toString(containers[i].end.y ) ));
                    element.appendChild(endpoint);
                }
                
                else
                {
                    Element pointsarray = v . createElement("startarray");
                    pointsarray.appendChild(v.createTextNode(Integer.toString(containers[i].x[0] )     +
                                                           "," + Integer.toString(containers[i].x[1] ) +
                                                           "," + Integer.toString(containers[i].x[2] ) +
                                                           "," + Integer.toString(containers[i].y[0] ) +
                                                           "," + Integer.toString(containers[i].y[1])  +
                                                           "," + Integer.toString(containers[i].y[2] ) + ",3" ));
                    element.appendChild(pointsarray);
                }
                
                Element Color = v . createElement("Color");
                Color.appendChild(v.createTextNode( containers[i].color.toString() ));
                element.appendChild(Color); 
                Element fillcolor = v . createElement("fillcolor");
                fillcolor.appendChild(v.createTextNode( containers[i].FilledColor.toString() ));
                element.appendChild(fillcolor);
            }
            
            TransformerFactory o = TransformerFactory.newInstance();
            Transformer k = null;
            
            try 
            {
                k = o.newTransformer();
            } 
            
            catch (TransformerConfigurationException ex) 
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DOMSource t = new DOMSource(v);
            StreamResult iom = new StreamResult(new File("C:\\Users\\mahmoud\\Documents\\NetBeansProjects\\Complete Project\\paint.xml"));
            
            try 
            {
                k.transform(t, iom);
            } 
            
            catch (TransformerException ex) 
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        else if(this.order == Orders.Open)
        {
            File xml = new File("C:\\Users\\mahmoud\\Documents\\NetBeansProjects\\Complete Project\\paint.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            
            try 
            {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
            } 
            
            catch (ParserConfigurationException ex)
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Document document = null;
            
            try 
            {
                document = documentBuilder.parse(xml);
            } 
            
            catch (SAXException ex) 
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(PaintPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            NodeList list = document.getElementsByTagName("types");
            this.index = 0;
           
            for(int i = 0 ; i < list.getLength() ; i++)
            {
                Node node = list.item(i);
                
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    String ty = element.getElementsByTagName("type").item(0).getTextContent();
                    if(!ty.equals("Triangle")){
                    String s =  element.getElementsByTagName("start").item(0).getTextContent();
                    String e =  element.getElementsByTagName("end").item(0).getTextContent();
                    String c =  element.getElementsByTagName("Color").item(0).getTextContent();
                    String fc = element.getElementsByTagName("fillcolor").item(0).getTextContent();          
                    ReadShape Reader = new ReadShape(ty, s, e, c, fc);
                    this.containers[this.index] = Reader.creatshape(this.containers[this.index] , image.createGraphics());
                    System.out.println(this.containers[this.index].type);
                    this.containers[this.index].draw(image.createGraphics(), this.containers[this.index].color); 
                    this.containers[this.index].fillclip(image.createGraphics(), this.containers[this.index], this.containers[this.index].FilledColor);
                    System.out.println(this.containers[this.index].color);
                    this.index++;
                    repaint();}
                    else 
                    {
                        String s =  element.getElementsByTagName("startarray").item(0).getTextContent();
                        String c =  element.getElementsByTagName("Color").item(0).getTextContent();
                        String fc = element.getElementsByTagName("fillcolor").item(0).getTextContent();          
                        ReadShape Reader = new ReadShape(ty, s, null, c, fc);
                        this.containers[this.index] = Reader.creatshape(this.containers[this.index] , image.createGraphics());
                        this.containers[this.index].draw(image.createGraphics(), this.containers[this.index].color); 
                        this.containers[this.index].fillclip(image.createGraphics(), this.containers[this.index], this.containers[this.index].FilledColor);
                        this.index++;
                        repaint();
                    }
                }
            }
        }
        
        System.out.println(this.order);
        shape = null;
    }

    //get desion
    public void setdesion(String q)
    {
        this.q = q;
    }
}