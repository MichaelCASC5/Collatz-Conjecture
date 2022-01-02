public class Rotate{
    private double a,b,x,y;
    
    public Rotate(double f, double g, double h, double i){
        a = f;
        b = g;
        x = h;
        y = i;
    }
    public void rotate(Point p){
//        int rot = p.getRot();
//        rot%=360;
//        
//        x = x - a;
//        y = y - b;
//        
//        x = (x * Math.cos(rot)) - (b * Math.sin(rot));
//        y = (y * Math.cos(rot)) + (x * Math.sin(rot));
//        
//        x = x + a;
//        y = y + b;
        
        double rot = p.getRot();
        rot%=360;
        
        rot = Math.toRadians(rot);
        
        a = a - x;
        b = b - y;
        
        a = (a * Math.cos(rot)) - (b * Math.sin(rot));
        b = (b * Math.cos(rot)) + (a * Math.sin(rot));
        
        a = a + x;
        b = b + y;
        
//        System.out.println("===Inside");
//        System.out.println(rot);
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println();
    }
    public double getA(){
        return a;
    }
    public double getB(){
        return b;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
}
