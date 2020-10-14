import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Partie
{
	
	Partie(Window fenetre)
	{
        Joueur joueur1 = new Joueur("Joueur 1", "noir");
        Joueur joueur2 = new Joueur("Joueur 2", "blanc");
        Plateau plate = new Plateau(10,10);
    }
}
