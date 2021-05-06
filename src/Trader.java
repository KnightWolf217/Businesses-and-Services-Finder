
public class Trader {

    private final String companyName;
    private final String location;
    private final String services;
    private final int numEmployees;
    private final double dailyRate;
    private final String description;

    //Constructor
    public Trader(String companyName, String location, String services, int numEmployees, double dailyRate, String description) {
        this.companyName = companyName;
        this.location = location;
        this.services = services;
        this.numEmployees = numEmployees;
        this.dailyRate = dailyRate;
        this.description = description;

    }

    //Methods
    public String getCompanyName() {
        return companyName;
    }

    ;
    
    public String getLocation() {
        return location;
    }

    ;
    
    public String getServices() {
        return services;
    }

    ;
    
    public int getNumEmployees() {
        return numEmployees;
    }

    ;
    
    public double getDailyRate() {
        return dailyRate;
    }

    ;
    
    public String getDescription() {
        return description;
    }

    ;
    
    //toString method and overriding
    @Override
    public String toString() {
        return companyName + ":" + location + ":" + services + ":" + numEmployees + ":" + dailyRate + ":" + description;
    }

}
