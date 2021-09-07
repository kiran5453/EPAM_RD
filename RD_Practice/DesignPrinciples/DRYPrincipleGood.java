class Rectangle
{
    public void type()
    {
        System.out.println("Quadrilateral");
    }
}
class Square extends Rectangle
{
	// Other Properties
}
public class DRYPrincipleGood
{
    public static void main(String[] args)
    {
        Rectangle r=new Rectangle();
        Square s=new Square();
        r.type();
        s.type();
    }
}