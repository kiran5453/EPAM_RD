public class Singleton
{
    int value;
    private Singleton(int val)
    {
        value=val;
    }
    static Singleton instance=null;
    public static Singleton getInstance(int x)
    {
        if(instance == null)
            instance=new Singleton(x);
        return instance;
    }
}