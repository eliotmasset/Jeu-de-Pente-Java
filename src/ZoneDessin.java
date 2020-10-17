import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessin extends JPanel
{
	Partie game;
	JLabel joueur;
	JLabel joueurs_score;
	ZoneDessin(Partie _game)
	{
		//setLayout(null);
		game = _game;
		setSize(800,800);
		joueur = new JLabel("Joueur 1(noir) : A toi de jouer");
		joueurs_score = new JLabel("<html><pre>Score :<br>    joueur 1(noir)  : 0 pions capturés<br>    joueur 2(blanc) : 0 pions capturés</pre></html>");
		bottomPanel();
	}

	public void bottomPanel()
    {
		joueur.setOpaque(true);
		Font font1 = new Font("Arial",Font.BOLD,20);
		joueur.setFont(font1);
		joueur.setLocation(20, 810);
		add(joueur);

		Font font2 = new Font("Arial",Font.BOLD,16);
		joueurs_score.setLocation(400, 800);
		joueurs_score.setFont(font2);
		joueurs_score.setText("<html><pre>Score :<br>    joueur 1(noir)  : " + game.getJoueur(1).getNbPoint() + 
		" pions capturés<br>    joueur 2(blanc) : " + game.getJoueur(2).getNbPoint() + " pions capturés</pre></html>");
		add(joueurs_score);
	}

	public void setJoueur(Joueur j)
	{
		if(j.getNom()=="joueur 1")
			joueur.setText("Joueur 2(blanc) : A toi de jouer");
		else
			joueur.setText("Joueur 1(noir) : A toi de jouer");
	}

	public void afficheEstGagne()
	{
		if(joueur.getText()=="Joueur 1(noir) : A toi de jouer")
			joueur.setText("Joueur 2(blanc) a gagné");
		else
			joueur.setText("Joueur 1(noir) a gagné");
		joueurs_score.setText("<html><pre>Score :<br>    joueur 1(noir)  : " + game.getJoueur(1).getNbPoint() + "<br>    joueur 2(blanc) : " + game.getJoueur(2).getNbPoint() + "</pre></html>");
		add(joueurs_score);
		add(joueur);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		affiche_plateau(g);
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
			g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX() , game.getPlateau().getCases().elementAt(i).getY() , 800/game.getPlateau().getNbCaseX(), 800/game.getPlateau().getNbCaseY(), this);
		}
	}
}
