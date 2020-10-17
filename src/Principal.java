import java.util.*;
import java.lang.Math;

class Principal
{
        public static void main (String [] arg)
	{
                Partie game = new Partie();
                Window fenetre = new Window("Jeu de pente", game);
                game.lancePartie(fenetre);
                fenetre.setVisible(true);
	}
}
