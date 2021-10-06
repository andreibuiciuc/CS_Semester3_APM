package Photographer;

import Film.FilmStock;
import Exception.FilmException;

public class Photographer {

    private String name;
    private Double budget;

    public Photographer() {}

    public Photographer(String name, Double budget) {
        this.name = name;
        this.budget = budget;
    }

    public Double getBudget() {
        return this.budget;
    }

    public String toString() {
        return this.name + ": " + this.budget;
    }

    public void buyFilm(FilmStock filmChoice, int quantity) throws FilmException{
        System.out.println(this.name + " wants to purchase " + filmChoice.getName());

        if (filmChoice.computePrice() * quantity > this.budget) {
            throw new FilmException("Not enough money.");
        }
        else {
            System.out.println(this.name + " bought " + "" + quantity + " " + filmChoice.getName());
            this.budget = this.budget - filmChoice.computePrice();
        }
    }

}
