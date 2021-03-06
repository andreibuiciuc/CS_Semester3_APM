package Repository;

import Exceptions.FullRepoException;
import Exceptions.InvalidIDException;
import Model.InterfaceVehicle;

public class MemoryRepository implements InterfaceRepository {

    private final InterfaceVehicle[] data;
    private Integer length;
    private final Integer capacity;

    public MemoryRepository(Integer capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.data = new InterfaceVehicle[this.capacity];
    }

    @Override
    public int getIndexInRepo(String vehicleId){
        int index = -1;
        for (int i = 0; i < length; i ++) {
            if (vehicleId.equals(data[i].getID())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public InterfaceVehicle[] getData() {
        return data;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void add(InterfaceVehicle vehicle) throws FullRepoException{
        if(length < capacity) {
            data[length] = vehicle;
            length = length + 1;
        }
        else {
            throw new FullRepoException("Repo is full.");
        }
    }

    @Override
    public void remove(String vehicleId) throws InvalidIDException {
        int index = this.getIndexInRepo(vehicleId);
        if(index == -1) {
            throw new InvalidIDException("Vehicle not in the repo.");
        }

        data[index] = data[length-1];
        data[length-1] = null;
        length = length - 1;
    }
}
