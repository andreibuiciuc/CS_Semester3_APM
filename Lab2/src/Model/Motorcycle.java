package Model;

public class Motorcycle implements InterfaceVehicle{

    private final String vehicleID;
    private final String type;
    private final String model;
    private final Integer repairCost;

    public Motorcycle(String vehicleID, String type, String model, Integer repairCost) {
        this.vehicleID = vehicleID;
        this.type = type;
        this.model = model;
        this.repairCost = repairCost;
    }

    @Override
    public String getID() {
        return vehicleID;
    }

    @Override
    public Integer getRepairCost() {
        return repairCost;
    }

    public String toString() {
        return vehicleID + " | " + model + " | " + repairCost + " | " + "(" + type + ")";
    }
}
