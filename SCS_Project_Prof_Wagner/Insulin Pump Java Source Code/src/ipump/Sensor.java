package ipump;


public class Sensor {
    private Human user;


    public Sensor(Human user)
    {
            this.user = user;
    }

    public int getSugarLevel()
    {
            return user.getSugarLevel();
    }
}
