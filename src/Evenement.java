import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Classe de gestion des evenements
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
public class Evenement implements MouseListener, MouseMotionListener
{  
    /**
    * game qui stoque la partie en cours
    * @see Partie
    */
    private Partie game;

    /**
    * Constructeur de la classe Evenemenent
    * @param _game   Partie qui stoque la partie en cours
    */
    public Evenement(Partie _game)
    {
        game=_game;
    }

    /**
    * Fonction qui se lance au click de la souris
    * @param e   Evenement qui stoque l'evenement de click
    */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if (e.getButton()==MouseEvent.BUTTON1 && e.getX()>=0 && e.getX()<=game.getFenetre().getSizeFenetre() && e.getY()>=game.getDecalMenu()*2 && e.getY()<=game.getFenetre().getSizeFenetre()+(game.getDecalMenu()*2))
        {
            game.clicEvent(e.getX(),e.getY());
        }
    }

    /**
    * Fonction qui se lance à la pression de la souris
    * @param e   Evenement qui stoque l'evenement de pression
    */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
    * Fonction qui se lance au relachement de la souris
    * @param e   Evenement qui stoque l'evenement de relachement
    */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
    * Fonction qui se lance à l'entrée de la souris dans une zone
    * @param e   Evenement qui stoque l'evenement d'entrée
    */
    @Override
    public void mouseEntered(MouseEvent e) 
    {}

    /**
    * Fonction qui se lance à la sortie de la souris dans une zone
    * @param e   Evenement qui stoque l'evenement de sortie
    */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
    * Fonction qui se lance au déplacement de la souris
    * @param e   Evenement qui stoque l'evenement de sortie
    */
    @Override
    public void mouseMoved(MouseEvent e) 
    {
        game.getFenetre().getZoneDessin().selection(e.getX(),e.getY());
    }

    /**
    * Fonction qui se lance lorsque la souris attrape un élément
    * @param e   Evenement qui stoque l'evenement de sortie
    */
    @Override
    public void mouseDragged(MouseEvent e) {}
}
