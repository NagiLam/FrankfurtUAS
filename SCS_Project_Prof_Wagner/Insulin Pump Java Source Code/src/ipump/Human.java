package ipump;


public class Human {
    private float sugarLevel;
    private float sugarNew;
    private final float SUGAR_MODIFIER = 0.02f;
    private float sugarMaxSpeed;
    
    private float insulin;
    private final float INSULIN_MODIFIER = 0.03f;
    private float insulinMaxSpeed;
    
    private final int SUGAR_LEVEL_MIN = 10;
    private final int SUGAR_LEVEL_MAX = 400;


    public Human()
    {
            sugarLevel = 100;
    }


    public void update()
    {
        sugar();
        insulin();

 
        if (sugarLevel < SUGAR_LEVEL_MIN)
        {
            sugarLevel = SUGAR_LEVEL_MIN;
        }
        else if (sugarLevel > SUGAR_LEVEL_MAX)
        {
            sugarLevel = SUGAR_LEVEL_MAX;
        }
    }
    
 
    private void sugar()
    {

  
        if (sugarMaxSpeed > 0 && -sugarNew < sugarMaxSpeed)
        {
  
            sugarLevel += sugarMaxSpeed - Math.abs(sugarNew);
    
            sugarNew -= SUGAR_MODIFIER;
        }    

        else
        {
            sugarMaxSpeed = 0;
            sugarNew = 0;
        }
    }


    private void insulin()
    {

        sugarLevel -= 0.032f;


        if (insulinMaxSpeed > 0 && -insulin < insulinMaxSpeed)
        {

        sugarLevel -= insulinMaxSpeed - Math.abs(insulin);

        insulin -= INSULIN_MODIFIER;
        }

        else
        {
            insulinMaxSpeed = 0;
            insulin = 0;
        }
    }


    public void eat()
    {
        sugarMaxSpeed = 0.75f;
        sugarNew = sugarMaxSpeed;
    }


    public void eatBig()
    {
        sugarMaxSpeed = 0.9f;
        sugarNew = sugarMaxSpeed;
    }


    public void setInsulin(int dose) {
            insulinMaxSpeed = (float) dose / 2;
            insulin = insulinMaxSpeed;
    }
    

    public int getSugarLevel() {
            return (int)sugarLevel;
    }
    
    public void setGlucagon(int dose) {
    	if(dose>2) {
    		eatBig();
    	}else if(dose>0){
    		eat();
    	}
    }
}
