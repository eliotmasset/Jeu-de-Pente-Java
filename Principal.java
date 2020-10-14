import java.util.*;
import java.lang.Math;

class Principal
{
	public static void main (String [] arg)
	{
        Window fenetre = new Window("Jeu de pente");
        fenetre.FenetrePente();
        Partie game = new Partie(fenetre);
	}
}
