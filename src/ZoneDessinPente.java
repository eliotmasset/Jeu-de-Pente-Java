import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessinPente extends JPanel
{
	private Partie game;
	private JLabel joueur;
	private JLabel joueurs_score;
	private int size;
	private boolean finPartie;

	ZoneDessinPente(Partie _game, int _size)
	{
		//setLayout(null);
		finPartie = false;
		game = _game;
		size=_size;
		setSize(size,size);
		joueur = new JLabel("Joueur 1(noir) : A toi de jouer");
		joueurs_score = new JLabel("<html><pre>Score :<br>    joueur 1(noir)  : 0 pions capturés<br>    joueur 2(blanc) : 0 pions capturés</pre></html>");
		bottomPanel();
	}

	public void bottomPanel()
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
		else if(game.getCurrentJoueur()==game.getJoueur(2).getNom() && !finPartie)
		{
			joueur.setText(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") : A toi de jouer");
		}
		else if(joueur.getText().equals(game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : A toi de jouer"))
		{
			joueur.setText(game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") a gagné");
			joueurs_score.setText("<html><pre>Score :<br>  "+game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+")  : " + game.getJoueur(1).getNbPoint() + 
			"/"+game.getNbPointToWin()+" pions capturés<br>  "+game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		}
		else if(joueur.getText().equals(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") : A toi de jouer"))
		{
			joueur.setText(game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+") a gagné");
			joueurs_score.setText("<html><pre>Score :<br>  "+game.getJoueur(1).getNom()+"("+game.getJoueur(1).getCouleur()+")  : " + game.getJoueur(1).getNbPoint() + 
			"/"+game.getNbPointToWin()+" pions capturés<br>  "+game.getJoueur(2).getNom()+"("+game.getJoueur(2).getCouleur()+") : " + game.getJoueur(2).getNbPoint() + "/"+game.getNbPointToWin()+" pions capturés</pre></html>");
		}
	}

	public void affichePionsJoueur(Graphics2D g)
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

	public void afficheEstGagne()
	{
		finPartie=true;

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		affiche_plateau(g2);
		affichePionsJoueur(g2);
		bottomPanel();
	}
	
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
	}
}
