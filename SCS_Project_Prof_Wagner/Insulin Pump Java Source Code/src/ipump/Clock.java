package ipump;


public class Clock {
    private static float minute = 0;

    private static long totalMinutes = 0;

    private static float speed;

    private static double startTime = System.currentTimeMillis();

    private static float deltaTime;

    private static long totalTMinutes = 0;
    public static boolean update()
    {

        deltaTime = (float)(System.currentTimeMillis() - startTime) / 1000;
     
        startTime = System.currentTimeMillis();

   
        minute -= deltaTime;
        ++totalTMinutes;
        if (minute <= 0) {
        
            minute += 1/speed;
          
            ++totalMinutes;
            
            return true;
        }
       
        return false;
    }


    public static long getTotalTime() {
        return totalMinutes;
    }
    public static long getTotalTTime() {
        return totalTMinutes;
    }

    public static boolean midnightPassed()
    {
        if(totalMinutes > 1440){
    
            totalMinutes -= 1440;
            return true;
        }	
        return false;
    }


    static void setSpeed(int speedNew) {
        speed = speedNew;
    }


    static float getSpeed() {
        return speed;
    }
}
