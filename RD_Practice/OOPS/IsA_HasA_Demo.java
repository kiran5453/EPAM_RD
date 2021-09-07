class Bike  
{  
    private String color;  
    private int maxSpeed;  
    public void bikeInfo()  
    {  
        System.out.println("Bike Color = "+color + "\nMax Speed = " + maxSpeed);  
    }  
    public void setColor(String color)  
    {  
        this.color = color;  
    }  
    public void setMaxSpeed(int maxSpeed)  
    {  
        this.maxSpeed = maxSpeed;  
    }  
}
class Engine  // HAS-A Relationship(Composition)
{  
    public void start()  
    {  
        System.out.println("Started:");  
    }  
    public void stop()  
    {  
        System.out.println("Stopped:");  
    }  
}  
class Pulsar extends Bike  // IS-A Relationship(Inheritance)
{  
    public void PulsarStartDemo()  
    {  
        Engine PulsarEngine = new Engine();  
        PulsarEngine.stop();  
    }  
}
public class IsA_HasA_Demo 
{  
    public static void main(String[] args)  
    {  
        Pulsar myPulsar = new Pulsar();  
        myPulsar.setColor("BLACK");  
        myPulsar.setMaxSpeed(136);  
        myPulsar.bikeInfo();  
        myPulsar.PulsarStartDemo();  
    }  
}  