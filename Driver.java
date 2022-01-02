import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener{
    private int WIDTH;
    private int HEIGHT;
    
    private ArrayList<Point> points;
    private double scale;
    private boolean run;
    
    private double xPos, yPos;
    private double speed;
    
    public Driver(){
        WIDTH = 800;
        HEIGHT = 800;
        
        points = new ArrayList<>();
        Point p = new Point(0,1);
        points.add(p);
        
        scale = 1;
        run = true;
        
        xPos = 0;
        yPos = 0;
        
        speed = 1;
        
        //Setting up the GUI
        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Collatz Conjecture");
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));
        gui.setResizable(false);
        gui.getContentPane().add(this);
        
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        gui.addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.blue);
        
        double x,y;
        double rX,rY;
        for(int i=0;i<points.size();i++){
            x = points.get(i).getX()/scale + (WIDTH/2);
            y = points.get(i).getY()/scale + (HEIGHT/2);
            
            int w = (int)(1/scale)+1;
            g.fillRect((int)x - w/2, (int)y - w/2, w, w);
            
            rX = points.get(i).getRefX()/scale + (WIDTH/2);
            rY = points.get(i).getRefY()/scale + (HEIGHT/2);
            
            g.drawLine((int)x, (int)y, (int)rX, (int)rY);
            
            
            //Camera movements
            
            points.get(i).setX(points.get(i).getX() + (int)xPos);
            points.get(i).setY(points.get(i).getY() + (int)yPos);
            
            rX = points.get(i).getRefX();
            rY = points.get(i).getRefY();
            
            points.get(i).setRef((int)(rX+xPos), (int)(rY+yPos));
        }
    }
    public void loop(){
        if(run){
            Point coll, mult;
            double a,b;

            boolean a_valid, b_valid;

            int len = points.size();
            for(int i=0;i<len;i++){
                a_valid = false;
                b_valid = true;

                a = (points.get(i).getVal() - 1) / 3;
                b = 2 * points.get(i).getVal();

                if(a%1.0 == 0 && a > 0){
                    a_valid = true;
                }

                for(int j=0;j<points.size();j++){
                    if(points.get(j).getY() == a){
                        a_valid = false;
                    }

                    if(points.get(j).getY() == b){
                        b_valid = false;
                    }
                }
                
                double c,d;
                double x,y;
                
                if(a_valid){
                    c = 0;
                    d = a;
                    x = points.get(i).getX();
                    y = points.get(i).getY();

                    Rotate r = new Rotate(c,d,x,y);
                    r.rotate(points.get(i));

                    c = r.getA();
                    d = r.getB();
                    x = r.getX();
                    y = r.getY();
                
                    coll = new Point((int)c,(int)d,points.get(i));
                    coll.setRot(points.get(i).getRot()+30);
                    points.add(coll);
                    
//                    System.out.println(coll.toString());
                }

                if(b_valid){
                    c = 0;
                    d = b;
                    x = points.get(i).getX();
                    y = points.get(i).getY();

                    Rotate r = new Rotate(c,d,x,y);
                    r.rotate(points.get(i));

                    c = r.getA();
                    d = r.getB();
                    x = r.getX();
                    y = r.getY();
                    
                    mult = new Point((int)c,(int)d,points.get(i));
                    mult.setRot(points.get(i).getRot()+15);
                    points.add(mult);
//                    System.out.println(mult.toString());
                }
                
                int lim = 100;
                if(a > lim || b > lim){
                    run = false;
                }
            }
        }
        
        repaint();
    }
    public void keyPressed(KeyEvent e){
        int k = e.getKeyCode();
        
        if(k == 87){
            yPos = speed;
        }else if(k == 65){
            xPos = speed;
        }else if(k == 83){
            yPos = -speed;
        }else if(k == 68){
            xPos = -speed;
        }
    }
    public void keyReleased(KeyEvent e){
        int k = e.getKeyCode();
        
        if(k == 87){
            yPos = 0;
        }else if(k == 65){
            xPos = 0;
        }else if(k == 83){
            yPos = 0;
        }else if(k == 68){
            xPos = 0;
        }
    }
    public void keyTyped(KeyEvent e){
    }
    public void mousePressed(MouseEvent e){
        int k = e.getButton();
        
        if(k == 1){
            scale*=2;
        }else if(k == 3){
            scale/=2;
        }
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseDragged(MouseEvent e){
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }
    public static void main(String[] args){
        Driver g = new Driver();
        g.start(60);
    }
}
