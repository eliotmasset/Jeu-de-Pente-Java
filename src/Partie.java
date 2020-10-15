import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Partie
{
	Plateau plate;
	Partie()
	{
        Joueur joueur1 = new Joueur("Joueur 1", "noir");
        Joueur joueur2 = new Joueur("Joueur 2", "blanc");
        plate = new Plateau(10,10);
    }

    public Plateau getPlateau()
    {
        return plate;
    }
}
