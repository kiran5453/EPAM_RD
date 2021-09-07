public class KISSPrincipleBad
{
    public static String dayOfWeek(int day) 
    {
         switch(day) 
	   {
         	case 1 : return "Monday";
         	case 2 : return "Tuesday";
         	case 3 : return "Wednesday";
         	case 4 : return "Thursday";
         	case 5 : return "Friday"; 
        	case 6 : return "Saturday";
         	case 7 : return "Sunday";
         	default : return "Invalid range";
	   }
      }
     public static void main(String[] args)
      {
	System.out.println(dayOfWeek(5));
	System.out.println(dayOfWeek(0));
	System.out.println(dayOfWeek(10));
      }
}
