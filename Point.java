public class Point{
    private int x,y;
    private int rot;
    
    private int val;
    
    private int rX,rY;
    
    public Point(int a, int b){
        x = a;
        y = b;
        
        rX = 0;
        rY = 0;
        
        val = b;
        
        rot = 0;
    }
    public Point(int a, int b, Point p){
        x = a;
        y = b;
        
        val = b;
        
        rot = p.getRot();
        
        copy(p);
    }
    public int getX(){
        return x;
    }
    public void setX(int a){
        x = a;
    }
    public int getY(){
        return y;
    }
    public void setY(int a){
        y = a;
    }
    public int getRefX(){
        return rX;
    }
    public int getRefY(){
        return rY;
    }
    public void setRef(int a, int b){
        rX = a;
        rY = b;
    }
    public int getRot(){
        return rot;
    }
    public void setRot(int r){
        rot = r;
    }
    public int getVal(){
        return val;
    }
    public void copy(Point p){

        rX = p.getX();
        rY = p.getY();
    }
    public String toString(){
        return "(" + x + ", " + y + ") r:" + rot;
    }
}