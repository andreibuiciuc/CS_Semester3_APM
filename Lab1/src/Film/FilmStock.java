package Film;

public abstract class FilmStock {

    private String manufacturer;
    private String name;
    protected String developMethod;

    static String type = "35mm";

    public FilmStock() {}

    public FilmStock(String manufacturer, String name, String developMethod) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.developMethod = developMethod;
    }

    public String getName() {
        return this.name;
    }

    public abstract Double computePrice();

    public String toString() {
        return this.manufacturer + " " + this.name + " " + type + " - " +
                this.developMethod + ": " + this.computePrice();
    }

}

