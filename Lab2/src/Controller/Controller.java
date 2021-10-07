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

    public void add(String vehicleID, String type, String model, Integer repairCost) throws InvalidTypeException,
            InvalidIDException, FullRepoException {

        InterfaceVehicle vehicle;

        // Check if the ID already exist in the repository.
        if(repository.getIndexInRepo(vehicleID) > 0) {
            throw new InvalidIDException("ID provided already exists.");
        }

        // Check if the type is correct and create the vehicle.
        switch (type) {
            case "Car" -> vehicle = new Car(vehicleID, type, model, repairCost);
            case "Truck" -> vehicle = new Truck(vehicleID, type, model, repairCost);
            case "Motorcycle" -> vehicle = new Motorcycle(vehicleID, type, model, repairCost);
            default -> throw new InvalidTypeException("Type provided is invalid.");
        }

        repository.add(vehicle);
    }

    public void remove(String vehicleID) throws InvalidIDException {
        repository.remove(vehicleID);
    }

    public InterfaceRepository getRepository() {
        return repository;
    }

    public InterfaceVehicle[] getExpensiveRepairs() {
        InterfaceVehicle[] expensiveRepairs = new InterfaceVehicle[repository.getLength()+1];
        int index = 0;

        for(int i = 0; i < repository.getLength(); i ++) {
            if(repository.getData()[i].getRepairCost() > InterfaceVehicle.limitFee) {
                expensiveRepairs[index] = repository.getData()[i];
                index += 1;
            }
        }

        return expensiveRepairs;
    }
}
