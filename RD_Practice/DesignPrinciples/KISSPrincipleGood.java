public class KISSPrincipleGood
{
    public static String dayOfWeek(int day)
    {  
        if(day < 1 || day > 7) return "Invalid range";
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        return days[day-1];
    }
    public static void main(String args[])
    {
        System.out.println(dayOfWeek(5));
        System.out.println(dayOfWeek(0));
        System.out.println(dayOfWeek(10));
    }
}
