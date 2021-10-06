import Film.BWFilm;
import Film.ColorFilm;
import Film.FilmStock;
import Photographer.Photographer;
import Exception.FilmException;

public class MainApp {

    public static void main(String[] args) {
        FilmStock[] films = new FilmStock[3];

        FilmStock Portra400 = new ColorFilm("Kodak", "Portra400", "C41", Boolean.FALSE, 50.);
        FilmStock Ilford400 = new BWFilm("Ilford", "Ilford400","ID1", 30.);
        FilmStock ColorPlus = new ColorFilm("Kodak", "ColorPlus",  "C41", Boolean.TRUE, 30.);
        films[0] = Portra400;
        films[1] = Ilford400;
        films[2] = ColorPlus;


        System.out.println("Film stocks available: ");
        for( int i = 0; i < 3; i ++) {
            System.out.println(films[i]);
        }
        System.out.println('\n');


        System.out.println("Photographers: ");
        Photographer Andrei = new Photographer("Andrei", 90.);
        System.out.println(Andrei.toString());
        Photographer John = new Photographer("John", 30.);
        System.out.println(John.toString());
        System.out.println('\n');

        // Andrei's purchase
        try {
            Andrei.buyFilm(Portra400, 1);
        }
        catch (FilmException e) {
            System.out.println(e.toString());
        }
        finally {
            System.out.println("Budget now: " + Andrei.getBudget());
        }
        System.out.println('\n');

        // John's purchase
        try {
            John.buyFilm(Ilford400, 2);
        }
        catch (FilmException e) {
            System.out.println(e.toString());
        }
        finally {
            System.out.println("Budget now: " + John.getBudget());
        }
    }

}
