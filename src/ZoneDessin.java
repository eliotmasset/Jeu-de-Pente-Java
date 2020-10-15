import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessin extends JPanel
{
	Partie game;

	ZoneDessin(Partie _game)
	{
		game = _game;
		setSize(800,800);
	}
	
	public void paint(Graphics g)
	{
		affiche_plateau(g);
	}
	
	public void affiche_plateau(Graphics g)
	{
		Image img=null;
		for(int i=0;i<game.getPlateau().getNbCaseX()*game.getPlateau().getNbCaseY();i++)
		{
			try 
			{
				img = ImageIO.read(new File(game.getPlateau().getCases().elementAt(i).getPath()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX() , game.getPlateau().getCases().elementAt(i).getY() , 800/game.getPlateau().getNbCaseX(), 800/game.getPlateau().getNbCaseY(), this);
		}
	}
}
