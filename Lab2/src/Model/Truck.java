package Model;

public final class Truck implements InterfaceVehicle {

    private final String vehicleID;
    private final String type;
    private final String model;
    private final Integer repairCost;

    public Truck(String vehicleID, String type, String model, Integer repairCost) {
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

    @Override
    public String toString() {
        return vehicleID + " | " + model + " | " + repairCost + " | " + "("+ type + ")";
    }
}
