import Controller.Controller;
import Exceptions.FullRepoException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidTypeException;
import Repository.InterfaceRepository;
import Repository.MemoryRepository;
import View.InterfaceUI;
import View.UI;

public class MainApp {

    public static void main(String[] args) {

        InterfaceRepository vehicleRepository = new MemoryRepository(5);
        Controller controller = new Controller(vehicleRepository);

        try {
            controller.add("1", "Car", "Octavia", 1200);
            controller.add("2", "Truck", "Volvo", 1500);
            controller.add("3", "Car", "Chevrolet", 700);
            controller.add("4", "Motorcycle", "Hyundai", 1050);
        } catch (InvalidTypeException | InvalidIDException | FullRepoException error) {
            System.out.println(error.toString());
        }

        InterfaceUI ui = new UI(controller);
        ui.startApp();
    }
}
