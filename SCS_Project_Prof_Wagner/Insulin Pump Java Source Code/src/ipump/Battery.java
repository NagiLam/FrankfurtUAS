package ipump;


public class Battery {
    private float power;
    private final int MAX_POWER = 100;
    private boolean charging = false;


    public Battery()
    {
            power = MAX_POWER;
    }


    public void update(boolean deviceOn)
    {
      
        if (charging)
        {
            power += 0.5f;
            if (power > MAX_POWER)
            {
                power = MAX_POWER;
            }
        }
       
        else if (deviceOn)
        {
            power -= 0.05f;
        }
    }

  
    public void sensor()
    {
        if (!charging)
        {
            power -= 0.2f;
        }
    }


    public void doseInection(int dose)
    {
        if (!charging)
        {
            power -= 0.2f * dose;
        }
    }


    public int getPower()
    {
            return (int) power;
    }

 
    public void charging()
    {
        charging = true;
    }

 
    public void chargingOff()
    {
        charging = false;
    }


    public boolean isCharging()
    {
        return charging;
    }
}
