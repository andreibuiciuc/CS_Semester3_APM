package Film;

public class BWFilm extends FilmStock {

    private Double price;

    public BWFilm(String manufacturer, String name, String developMethod, Double price) {
        super(manufacturer, name, developMethod);
        this.price = price;
    }

    @Override
    public Double computePrice() {
        if (this.developMethod.equals("C41") == Boolean.FALSE) {
            return this.price * 1.25;
        }
        return this.price;
    }
}
