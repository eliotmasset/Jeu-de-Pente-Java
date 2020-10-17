import java.util.*;
import java.lang.Math;

class Principal
{
        public static void main (String [] arg)
	{
                int size=800;
                Partie game = new Partie(size);
                Window fenetre = new Window("Jeu de pente", game, size);
                game.lancePartie(fenetre);
                fenetre.setVisible(true);
	}
}
