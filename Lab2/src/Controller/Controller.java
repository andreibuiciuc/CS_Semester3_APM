package Controller;

import Exceptions.FullRepoException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidTypeException;
import Model.Car;
import Model.InterfaceVehicle;
import Model.Motorcycle;
import Model.Truck;
import Repository.InterfaceRepository;

public class Controller {
    InterfaceRepository repository;

    public Controller(InterfaceRepository repository) {
        this.repository = repository;
    }

    private boolean isID(String vehicleID) {
        for(int i = 0; i < this.repository.getLength(); i ++) {
            if(this.repository.getData()[i].getID().equals(vehicleID)) {
                return true;
            }
        }
        return false;
    }

    public void add(String vehicleId, String type, String model, Integer repairCost) throws InvalidTypeException,
            InvalidIDException, FullRepoException {

        InterfaceVehicle vehicle;

        // Check if the ID already exist in the repository.
        if(this.isID(vehicleId)) {
            throw new InvalidIDException("ID provided already exists.");
        }

        // Check if the type is correct and create the vehicle.
        switch (type) {
            case "Car" -> vehicle = new Car(vehicleId, type, model, repairCost);
            case "Truck" -> vehicle = new Truck(vehicleId, type, model, repairCost);
            case "Motorcycle" -> vehicle = new Motorcycle(vehicleId, type, model, repairCost);
            default -> throw new InvalidTypeException("Type provided is invalid.");
        }

        this.repository.add(vehicle);
    }

    public void remove(String vehicleID) throws InvalidIDException {
        if(!this.isID(vehicleID)) {
            throw new InvalidIDException("ID provided does not exist.");
        }

        this.repository.remove(vehicleID);
    }

    public InterfaceRepository getRepository() {
        return this.repository;
    }
}
