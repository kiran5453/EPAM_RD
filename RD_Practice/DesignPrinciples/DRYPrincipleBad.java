class Rectangle
{
    public void type()
    {
        System.out.println("Quadrilateral");
    }
}
class Square
{
    public void type()
    {
        System.out.println("Quadrilateral");
    }	
}
public class DRYPrincipleBad
{
    public static void main(String[] args)
    {
        Rectangle r=new Rectangle();
        Square s=new Square();
        r.type();
        s.type();
    }
}