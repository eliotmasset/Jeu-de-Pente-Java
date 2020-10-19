import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessinPente extends JPanel
{
	Partie game;
	JLabel joueur;
	JLabel joueurs_score;
	int size;
	ZoneDessinPente(Partie _game, int _size)
	{
		//setLayout(null);
		game = _game;
		size=_size;
		setSize(size,size);
		setBackground(new Color(255,255,255));
		joueur = new JLabel("Joueur 1(noir) : A toi de jouer");
		joueurs_score = new JLabel("<html><pre>Score :<br>    joueur 1(noir)  : 0 pions capturés<br>    joueur 2(blanc) : 0 pions capturés</pre></html>");
		bottomPanel();
	}

	public void bottomPanel()
    {
		joueur.setOpaque(false);
		Font font1 = new Font("Arial",Font.BOLD,20);
		joueur.setFont(font1);
		joueur.setLocation(size/40, size+10);
		add(joueur);

		Font font2 = new Font("Arial",Font.BOLD,16);
		joueurs_score.setLocation((size/40)*19, size);
		joueurs_score.setFont(font2);
		joueurs_score.setText("<html><pre>Score :<br>  joueur 1(noir)  : " + game.getJoueur(1).getNbPoint() + 
		"/"+game.getNbPointToWin()+" pions capturés<br>  joueur 2(blanc) : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		add(joueurs_score);
	}

	public void affichePionsJoueur(Graphics g)
	{
		Image img;
		img=null;

		if(game.getCurrentJoueur()=="joueur 1")
		{
			try 
			{
				img = ImageIO.read(new File(game.getPlateau().getCaseAt(0, 0).getPathBy("normal","pion_blanc")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				img = ImageIO.read(new File(game.getPlateau().getCaseAt(0, 0).getPathBy("normal","pion_noir")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		g.drawImage(img, game.getFenetre().getSizeFenetre()/10 , (game.getFenetre().getSizeFenetre()*100)/96 , size/game.getPlateau().getNbCaseX(), size/game.getPlateau().getNbCaseY(), this);
	}

	public void setJoueur(Joueur j)
	{
		Image img;
		if(j.getNom()=="joueur 1")
		{
			joueur.setText("Joueur 2(blanc) : A toi de jouer");
		}
		else
		{
			joueur.setText("Joueur 1(noir) : A toi de jouer");
		}
	}

	public void afficheEstGagne()
	{
		if(joueur.getText()=="Joueur 1(noir) : A toi de jouer")
			joueur.setText("Joueur 2(blanc) a gagné");
		else
			joueur.setText("Joueur 1(noir) a gagné");
			joueurs_score.setText("<html><pre>Score :<br>  joueur 1(noir)  : " + game.getJoueur(1).getNbPoint() + 
			"/"+game.getNbPointToWin()+" pions capturés<br>  joueur 2(blanc) : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		add(joueurs_score);
		add(joueur);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		affiche_plateau(g);
		affichePionsJoueur(g);
		bottomPanel();
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
			g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX() , game.getPlateau().getCases().elementAt(i).getY() , size/game.getPlateau().getNbCaseX(), size/game.getPlateau().getNbCaseY(), this);
		}
	}
}
