package Repository;

import Exceptions.FullRepoException;
import Model.InterfaceVehicle;

public interface InterfaceRepository {

    void add(InterfaceVehicle vehicle) throws FullRepoException;

    void remove(String vehicleId);

    InterfaceVehicle[] getData();

    int getLength();
}
