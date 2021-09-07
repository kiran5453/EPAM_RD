class Square{
    int s=10;
    public void getArea(){
       System.out.println(s*s);
     }
     public void getPerimeter(){
       System.out.println(4*s);
     }
}
public class YAGNIPrincipleBad{
       public static void main(String[] args){
          Square obj=new Square();
          obj.getArea();
    }
}