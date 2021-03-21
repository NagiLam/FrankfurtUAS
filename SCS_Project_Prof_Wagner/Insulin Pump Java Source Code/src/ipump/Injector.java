package ipump;


public class Injector {
    Human user;


    public Injector(Human user)
    {
            this.user = user;
    }

    public void inject(int dose)
    {
            user.setInsulin(dose);
    }
    
    public void giveGlucagon(int dose) {
    	user.setGlucagon(dose);
    }
}
