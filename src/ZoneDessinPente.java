import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

/**
 * Classe de la zone de dessin de la fenetre de jeu
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class ZoneDessinPente extends JPanel
{

	/**
    * Coordonnées x et y  de la selections
    */
	private int XSelect,YSelect;
	/**
    * Partie en jeu
    */
	private Partie game;
	/**
    * Texte qui affiche le tour de jeu
    */
	private JLabel joueur;
	/**
    * Texte qui affiche les scores des joueurs
    */
	private JLabel joueurs_score;
	/**
    * taille de la fenetre
    */
	public int size;
	/**
    * boolean pour savoir si la partie est terminee
    */
	private boolean finPartie;

	/**
	* Constructeur de la zone de dession du jeu de pente
	* @param _game Partie qui stoque la partie en cours
	* @see Partie
	* @param _size int qui stoque la taille de la fenetre
    */
	ZoneDessinPente(Partie _game, int _size)
	{
		XSelect=0;
		YSelect=0;
		finPartie = false;
		game = _game;
		size=_size;
		setSize(size,size);
		joueur = new JLabel("Joueur 1(noir) : A toi de jouer");
		joueurs_score = new JLabel("<html><pre>Score :<br>    joueur 1(noir)  : 0 pions capturés<br>    joueur 2(blanc) : 0 pions capturés</pre></html>");
		bottomPanel();
	}

	/**
    * Affiche la partie basse de l'affichage (Informations de jeu)
    */
	private void bottomPanel()
    {
		if(game.getTheme()=="sombre")
		{
			joueur.setForeground(new Color(255,255,255));
			joueurs_score.setForeground(new Color(255,255,255));
			setBackground(new Color(0,0,0));
			game.getJoueur(1).setCouleur("noir");
			game.getJoueur(2).setCouleur("gris");
		}
		else if(game.getTheme()=="clair")
		{
			joueur.setForeground(new Color(150,199,199));
			joueurs_score.setForeground(new Color(150,199,199));
			setBackground(new Color(255,255,255));
			game.getJoueur(1).setCouleur("bleu");
			game.getJoueur(2).setCouleur("blanc");
		}
		else if(game.getTheme()=="normal")
		{
			setBackground(new Color(255,255,255));
			joueur.setForeground(new Color(0,0,0));
			joueurs_score.setForeground(new Color(0,0,0));
			game.getJoueur(1).setCouleur("noir");
			game.getJoueur(2).setCouleur("blanc");
		}
		else if(game.getTheme()=="halloween")
		{
			setBackground(new Color(0,0,0));
			joueur.setForeground(new Color(210,76,0));
			joueurs_score.setForeground(new Color(210,76,0));
			game.getJoueur(1).setCouleur("citrouille");
			game.getJoueur(2).setCouleur("fantome");
		}
		else if(game.getTheme()=="noel")
		{
			setBackground(new Color(255,255,255));
			joueur.setForeground(new Color(255,0,0));
			joueurs_score.setForeground(new Color(255,0,0));
			game.getJoueur(1).setCouleur("cadeau");
			game.getJoueur(2).setCouleur("pere noel");
		}
		else if(game.getTheme()=="zelda")
		{
			setBackground(new Color(0,255,0));
			joueur.setForeground(new Color(255,255,0));
			joueurs_score.setForeground(new Color(255,255,0));
			game.getJoueur(1).setCouleur("monstre");
			game.getJoueur(2).setCouleur("link");
		}
		else if(game.getTheme()=="harry potter")
		{
			setBackground(new Color(0,0,0));
			joueur.setForeground(new Color(0,0,255));
			joueurs_score.setForeground(new Color(0,0,255));
			game.getJoueur(1).setCouleur("serpentar");
			game.getJoueur(2).setCouleur("poufsouffle");
		}
		else if(game.getTheme()=="os")
		{
			setBackground(new Color(0,0,0));
			joueur.setForeground(new Color(0,255,0));
			joueurs_score.setForeground(new Color(0,255,0));
			game.getJoueur(1).setCouleur("linux");
			game.getJoueur(2).setCouleur("windows");
		}
		joueur.setOpaque(false);
		Font font1 = new Font("Arial",Font.BOLD,size/40);
		joueur.setFont(font1);
		joueur.setLocation(size/40, size);
		add(joueur);

		Font font2 = new Font("Arial",Font.BOLD,size/50);
		joueurs_score.setLocation((size/40)*19, size);
		joueurs_score.setFont(font2);
		joueurs_score.setText("<html><pre>Score :<br>  "+game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+")  : " + game.getJoueur(1).getNbPoint() + 
		"/"+game.getNbPointToWin()+" pions capturés<br>  "+game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		add(joueurs_score);

		if(game.getCurrentJoueur()==game.getJoueur(1).getNom() && !finPartie)
		{
			joueur.setText(game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : A toi de jouer");
		}
		else if (game.getCurrentJoueur()==game.getJoueur(2).getNom() && !finPartie)
		{
			joueur.setText(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") : A toi de jouer");
		}
		else if (joueur.getText().equals(game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : A toi de jouer"))
		{
			joueur.setText(game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") a gagné");
			joueurs_score.setText("<html><pre>Score :<br>  "+game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+")  : " + game.getJoueur(1).getNbPoint() + 
			"/"+game.getNbPointToWin()+" pions capturés<br>  "+game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		}
		else if (joueur.getText().equals(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") : A toi de jouer"))
		{
			joueur.setText(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") a gagné");
			joueurs_score.setText("<html><pre>Score :<br>  "+game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+")  : " + game.getJoueur(1).getNbPoint() + 
			"/"+game.getNbPointToWin()+" pions capturés<br>  "+game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		}
	}

	/**
	* Affiche les pions des joueurs à leurs positions
	* @param g Graphics2D qui permet de dessiner sur la fenetre
    */
	private void affichePionsJoueur(Graphics2D g)
	{
		Image img;
		img=null;

		if(game.getCurrentJoueur()==game.getJoueur(1).getNom())
		{
			try 
			{
				if(game.isFinish())
					img = ImageIO.read(new File(game.getPlateau().getPathBy(game.getTheme(),"pion_sombre")));
				else
					img = ImageIO.read(new File(game.getPlateau().getPathBy(game.getTheme(),"pion_clair")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(game.getCurrentJoueur()==game.getJoueur(2).getNom())
		{
			try 
			{
				if(game.isFinish())
					img = ImageIO.read(new File(game.getPlateau().getPathBy(game.getTheme(),"pion_clair")));
				else
					img = ImageIO.read(new File(game.getPlateau().getPathBy(game.getTheme(),"pion_sombre")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		g.drawImage(img, size/40, size+joueur.getHeight() , game.getFenetre().getSizeFenetre()/20, game.getFenetre().getSizeFenetre()/20, this);
	}

	/**
	* indique que la partie est terminer au programme
    */
	public void afficheEstGagne()
	{
		finPartie=true;
	}

	/**
	* indique les coordonnées de lasouris pour afficher la selection
	* @param x int qui stoque la coordonnée X
	* @param y int qui stoque la coordonnée Y
    */
	public void selection(int x, int y)
	{
		XSelect=x;
		YSelect=y-2*game.getDecalMenu();
	}
	
	/**
	* Affiche la sélection
	* @param g Graphics qui permet de dessiner sur la fenetre
    */
	public void afficheSelection(Graphics2D g)
	{
		g.setColor(new Color(0,255,0));
		if(XSelect>0 && YSelect>0 && XSelect<size && YSelect<size && !game.isFinish())
			g.drawRect(	game.getPlateau().getCaseAt(XSelect, YSelect).getX(), 
						game.getPlateau().getCaseAt(XSelect, YSelect).getY(),
						size/game.getPlateau().getNbCaseX(), 
						size/game.getPlateau().getNbCaseY());
	}

	/**
	* fonction qui met à jour l'affichage
	* @param g Graphics qui permet de dessiner sur la fenetre
    */
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		affiche_plateau(g2);
		affichePionsJoueur(g2);
		bottomPanel();
		afficheSelection(g2);
		repaint();
	}
	
	/**
	* Affiche le plateau
	* @param g Graphics2D qui permet de dessiner sur la fenetre
    */
	public void affiche_plateau(Graphics2D g)
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
			if(i%(game.getPlateau().getNbCaseX())>=(game.getPlateau().getNbCaseX()/2)+1 && i>=(game.getPlateau().getNbCaseX()*game.getPlateau().getNbCaseY()/2)+game.getPlateau().getNbCaseY())
				g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX()-1, game.getPlateau().getCases().elementAt(i).getY()-1, size/game.getPlateau().getNbCaseX()+1, size/game.getPlateau().getNbCaseY()+1, this);
			else if(i%(game.getPlateau().getNbCaseX())>=(game.getPlateau().getNbCaseX()/2)+1)
				g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX()-1 , game.getPlateau().getCases().elementAt(i).getY() , size/game.getPlateau().getNbCaseX()+1, size/game.getPlateau().getNbCaseY()+1, this);
			else if(i>=(game.getPlateau().getNbCaseX()*game.getPlateau().getNbCaseY()/2)+1)
				g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX() , game.getPlateau().getCases().elementAt(i).getY()-1 , size/game.getPlateau().getNbCaseX()+1, size/game.getPlateau().getNbCaseY()+1, this);
			else
				g.drawImage(img, game.getPlateau().getCases().elementAt(i).getX() , game.getPlateau().getCases().elementAt(i).getY() , size/game.getPlateau().getNbCaseX()+1, size/game.getPlateau().getNbCaseY()+1, this);
		}
		if(game.getNbTour()==1)
        {
			g.setColor(new Color(255,0,0));
			g.drawRect(size/2-((size/game.getPlateau().getNbCaseX())*7)/2, size/2-((size/game.getPlateau().getNbCaseY())*7)/2, size/game.getPlateau().getNbCaseX()*7, size/game.getPlateau().getNbCaseY()*7);
			g.setColor(new Color(255,0,0,20));
			g.fillRect(size/2-((size/game.getPlateau().getNbCaseX())*7)/2, size/2-((size/game.getPlateau().getNbCaseY())*7)/2, size/game.getPlateau().getNbCaseX()*7, size/game.getPlateau().getNbCaseY()*7);
		}
		if(finPartie)
		{
			try 
			{
				if(game.getCurrentJoueur()==game.getJoueur(1).getNom())
					img = ImageIO.read(new File(Plateau.getPathBy(game.getTheme(),"fin_sombre")));
				else
					img = ImageIO.read(new File(Plateau.getPathBy(game.getTheme(),"fin_clair")));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			g.drawImage(img, 0, 0, size, size, this);
		}
	}
}
