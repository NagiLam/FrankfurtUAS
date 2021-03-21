package ipump;


public class Controller {
    public Alarm alarm;
    private Sensor sensor;
    private Injector injector;
    private Battery battery;
    boolean criticalLow;
    boolean criticalHigh;
    boolean glucagonLevelDose;
    boolean canGlucagon = true;
    boolean givenGlucagon = false;
    private boolean on = false;

   
    int sugarLevel;
    private int sugarLevelPast;
    private int sugarLevelPast2;

   
    private final int NEW_RESERVOIR = 100;
    private int currentReservoir;
    private boolean reservoirIn;
    
 
    private boolean manualPump;


    private long timeDose;

    private boolean sugarLevelDose;
    private boolean sugarLevelPastDose;
    private boolean sugarLevelPast2Dose;

    private int currentDose;        

    private int lastInjectedDose;

   
    private final int DOSE_MIN = 1;
  
    private final int DOSE_MAX = 4;
  
    private final int DOSE_DAILY_MAX = 25;

    private int currentDailyDose;
    
    private final int REFRESH_RATE = 10;
    private int minute = 0;

 
    private final int SAFE_ZONE_MIN = 75;
    private final int SAFE_ZONE_MAX = 135;


    public Controller(Human user)
    {
        sensor = new Sensor(user);
        injector = new Injector(user);
        alarm = new Alarm();
        battery =  new Battery();

        currentDose = 0;
        lastInjectedDose = 0;

  
        currentReservoir = NEW_RESERVOIR;
        reservoirIn = true;

 
        manualPump = false;

        resetAccumulativeDose();
    }

  
    private void resetAccumulativeDose()
    {
        currentDailyDose = 0;
        alarm.maxDose = false;
    }


    public void update()
    {
     
        if(Clock.midnightPassed())
        {
            resetAccumulativeDose();
        }
                    
        battery.update(on);        
        

        if (!on)
            return;
        
      
        minute --;
        if (minute <= 0)
        {
            minute += REFRESH_RATE;
            
  
            if(battery.getPower() > 5)
            {
   
                if (!alarm.errorSensor)
                {
                    activateSensor();
                    battery.sensor();

            
                    if (!alarm.errorDelivery && !alarm.errorPump && !alarm.errorNeedle)
                    {
                     
                        if (reservoirIn && currentReservoir > 0)
                        {
                           
                            currentDose = computeDose();

                         
                            if (currentDose > 0 || manualPump)
                            {
                                administerInsulin(currentDose);
                            }	
                        }
                        int currentGlucagonDose = computeGlucagonDose();
                        if((currentGlucagonDose > 0) &&canGlucagon) {
                        	injector.giveGlucagon(currentGlucagonDose);
                        	givenGlucagon = true;
                        }
                        if(sugarLevel>160) {
                        	criticalHigh = true;
                        }
                        if(sugarLevel<50) {
                        	criticalLow = true;
                        }
                        
                    }
                }
            }
         
            else
            {
                on = false;
                Wav.stop();
            }
        }
        
     
        if (battery.getPower() < 15)
        {
            alarm.lowBattery = true;
        }
        else
        {
            alarm.lowBattery = true;
        }
    }



    private int computeDose()
    {
        int compDose;

       
        if (sugarLevel <= SAFE_ZONE_MAX)
        {
            compDose = 0;
        }

  
        else if (sugarLevel < sugarLevelPast || (sugarLevel - sugarLevelPast) < (sugarLevelPast - sugarLevelPast2))
        {
            compDose = 0;
        }
        
        else 
        {

            compDose = ((sugarLevel - sugarLevelPast) / 3); 
            
          
            if ( compDose < DOSE_MIN )
            {
                compDose = DOSE_MIN ;
            }

            else if ( compDose > DOSE_MAX )
            {
                compDose = DOSE_MAX ;
            }
        }

        return compDose;
    }


    private void administerInsulin(int dose)
    {
 
        if (currentDailyDose >= DOSE_DAILY_MAX && !manualPump)
        {
                alarm.maxDose = true;
        }

        else
        {

            if (manualPump)
            {
                if (dose <= 0)
                {
                    dose = DOSE_MIN;
                }
            }
    
            else if (currentDailyDose + dose > DOSE_DAILY_MAX)
            {
                dose = DOSE_DAILY_MAX - currentDailyDose;
            }
                

            if (currentDailyDose + dose >= DOSE_DAILY_MAX)
            {
                alarm.maxDose = true;
            }

            if (currentReservoir - dose <= 0)
            {
                dose = currentReservoir;
                currentReservoir = 0;
                alarm.emptyReservoir = true;
            } else {
                currentReservoir -= dose;
            }
            

            injector.inject(dose);
            
           
            sugarLevelDose = true;
      
            timeDose = Clock.getTotalTime();
            

            currentDailyDose += dose;

            lastInjectedDose = dose;
    
            battery.doseInection(dose);

            manualPump = false;
        }
    }
    

    private void activateSensor()
    {          
        sugarLevelPast2 = sugarLevelPast;
        sugarLevelPast = sugarLevel;
        sugarLevel = sensor.getSugarLevel();
        givenGlucagon = false;
        sugarLevelPast2Dose = sugarLevelPastDose;
        sugarLevelPastDose = sugarLevelDose;
        sugarLevelDose = false;
        glucagonLevelDose = false;
        criticalLow = false;
        criticalHigh = false;
        sugarLevelAlarm();
        sugarLevelRising();
    }
    

    private void sugarLevelAlarm()
    {
        if (sugarLevel < SAFE_ZONE_MIN)
        {
                alarm.lowBloodSugar = true;
                alarm.highBloodSugar = false;
                Wav.play("alarm.wav");
        }
        else if (sugarLevel > SAFE_ZONE_MAX)
        {
                alarm.lowBloodSugar = false;
                alarm.highBloodSugar = true;
                Wav.play("alarm.wav");
        } else {
                Wav.stop();
        }
    }
    

    private void sugarLevelRising()
    {
        if (sugarLevel > sugarLevelPast)
        {
            alarm.sugarRising = true;
        }
        else
        {
            alarm.sugarRising = false;
        }
    }

    

    public void reservoirRemoved()
    {
        if (reservoirIn)
        {
            currentReservoir = 0;
            reservoirIn = false;
            alarm.missingReservoir = true;
            alarm.incorrectReservoir = false;
            Wav.play("beep.wav");   
        }
    }


    public void replaceCartridge(boolean seal)
    {
        if (reservoirIn == false)
        {
            if (seal)
            {
                alarm.incorrectReservoir = false;
                currentReservoir = NEW_RESERVOIR;
                reservoirIn = true;
                alarm.missingReservoir = false;
                Wav.play("beep.wav");
            } else {
                reservoirIn = true;
                alarm.incorrectReservoir = true;
                alarm.missingReservoir = false;
                Wav.play("beep.wav");   
            }
        }
    }


    public void manualInject() {
        manualPump = true;
    }
    

    public void power() {
        on = !on;
        
        minute = 0;
    }
    

    public boolean isOn() {
        return on;
    }
    

    public int getRemaningReservoir() {
        return currentReservoir;
    }


    public int getSugarLevel(int i) {
        switch(i)
        {
            case 0: return sugarLevel;
            case 1: return sugarLevelPast;
            case 2: return sugarLevelPast2;
            default: return sugarLevel;
        }
    }
    

    public boolean getSugarLevelDose(int i) {
        switch(i)
        {
            case 0: return sugarLevelDose;
            case 1: return sugarLevelPastDose;
            case 2: return sugarLevelPast2Dose;
            default: return sugarLevelDose;
        }
    }
    
    public boolean getGlucagonLevelDose() {
   
            return glucagonLevelDose;
   
        
    }

    public int getTotalDailyDose() {
        return currentDailyDose;
    }
    

    public int getTimePreviousDose() {
        return (int)timeDose;
    }
    

    public int getLastDose() {
        return lastInjectedDose;
    }


    public int getMaxDailyDose() {
        return DOSE_DAILY_MAX;
    }
    

    public boolean isReservoirIn() {
        return reservoirIn;
    }
    

    public boolean isReservoirCorret() {
        return alarm.incorrectReservoir;
    }
    

    public int getBatteryPower() {
        return battery.getPower();
    }
    

    public Battery getBattery() {
        return battery;
    }
    private int computeGlucagonDose()
    {
        int compDose;

       
        if (sugarLevel >= SAFE_ZONE_MIN)
        {
            compDose = 0;
        }

  
        else if (sugarLevel > sugarLevelPast || ( sugarLevelPast - sugarLevel) < ( sugarLevelPast2 - sugarLevelPast ))
        {
            compDose = 0;
        }
        
        else 
        {

            compDose = (( sugarLevelPast- sugarLevel ) / 3); 
            
          
            if ( compDose < DOSE_MIN )
            {
                compDose = DOSE_MIN ;
            }

            else if ( compDose > DOSE_MAX )
            {
                compDose = DOSE_MAX ;
            }
            glucagonLevelDose = true;
        }

        return compDose;
    }
    
    public boolean getCritHigh() {
    	return criticalHigh;
    }
    
    public boolean getCritLow() {
    	return criticalLow;
    }
}
