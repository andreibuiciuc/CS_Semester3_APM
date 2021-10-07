package Repository;

import Exceptions.FullRepoException;
import Exceptions.InvalidIDException;
import Model.InterfaceVehicle;

public interface InterfaceRepository {

    void add(InterfaceVehicle vehicle) throws FullRepoException;

    void remove(String vehicleId) throws InvalidIDException;

    InterfaceVehicle[] getData();

    int getLength();

    int getIndexInRepo(String vehicleID);
}
