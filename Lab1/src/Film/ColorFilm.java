package Film;

public class ColorFilm extends FilmStock {

    private Boolean expired;
    private Double price;

    public ColorFilm(String manufacturer, String name, String developMethod, Boolean expired, Double price) {
        super(manufacturer, name, developMethod);
        this.expired = expired;
        this.price = price;
    }

    @Override
    public Double computePrice() {
        if (this.expired == Boolean.TRUE) {
            return this.price / 2;
        }
        return this.price;
    }

}