package View;

import java.util.Scanner;
import Controller.Controller;
import Exceptions.FullRepoException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidTypeException;

public class UI implements InterfaceUI{

    private final Controller controller;

    public UI(Controller controller) {
        this.controller = controller;
    }

    private void printMenu() {
        System.out.println("\nHello and welcome to the Garage. Available options: ");
        System.out.println("1. See the repository.");
        System.out.println("2. Add a vehicle.");
        System.out.println("3. Delete a vehicle.");
        System.out.println("4. Get the expensive repairs.");
        System.out.println("0. Exit");
    }


    private void showRepo() {
        for(int i = 0; i < this.controller.getRepository().getLength(); i ++) {
            System.out.println(this.controller.getRepository().getData()[i]);
        }
    }

    private void addUI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String vehicleID = scan.nextLine();
        System.out.println("Enter the type of vehicle: ");
        String type = scan.nextLine();
        System.out.println("Enter the model: ");
        String model = scan.nextLine();
        System.out.println("Enter the repair cost: ");
        Integer repairCost = scan.nextInt();

        try {
            this.controller.add(vehicleID, type, model, repairCost);
        } catch (InvalidIDException | InvalidTypeException | FullRepoException error) {
            System.out.println(error.getMessage());
        }
    }

    private void deleteUI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ID for removal: ");
        String vehicleID = scan.nextLine();

        try {
            this.controller.remove(vehicleID);
        } catch (InvalidIDException error) {
            System.out.println(error.getMessage());
        }
    }

    private void getExpensiveRepairs() {
        for(int i = 0; i < this.controller.getRepository().getLength(); i ++) {
            if(this.controller.getRepository().getData()[i].getRepairCost() > 1000) {
                System.out.println(this.controller.getRepository().getData()[i]);
            }
        }
    }

    @Override
    public void startApp() {
        Scanner scan = new Scanner(System.in);
        boolean done = false;

        String command;
        this.printMenu();

        while(!done) {
            System.out.println("\nEnter a command: ");
            command = scan.nextLine();

            switch (command) {
                case "1" -> this.showRepo();
                case "2" -> this.addUI();
                case "3" -> this.deleteUI();
                case "4" -> this.getExpensiveRepairs();
                case "0" -> done = true;
                default -> System.out.println("Invalid command.");
            }
        }
    }
}
