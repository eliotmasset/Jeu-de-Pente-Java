import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Evenement implements MouseListener
{
    Partie game;
    public Evenement(Partie _game)
    {
        game=_game;
    }
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if (e.getButton()==MouseEvent.BUTTON1 && e.getX()>=0 && e.getX()<=800 && e.getY()>=25 && e.getY()<=825)
        {
            System.out.println(" x = " + e.getX() + " | y = " + e.getY());
            game.clicEvent(e.getX(),e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
