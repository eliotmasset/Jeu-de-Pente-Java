import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe de la fenetre de jeu
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class GameWindow extends JFrame implements ActionListener, Serializable
{
	/**
	* fenetre du ScoreBoard
	* @see ScoreBoardWindow
    */
	private ScoreBoardWindow w;
	/**
    * zone de dessin de la fenetre de jeu
	* @see ZoneDessinPente
    */
	private ZoneDessinPente zone;
	/**
    * Partie en cours
	* @see Partie
    */
	private Partie game;
	/**
    * Taille de la fenetre
    */
	private int size;

	/**
    * Constructeur de la fenetre de jeu
	* @param s qui stoque le nom de la fenetre
	* @param _game qui stoque la partie en cours
	* @param _size int qui stoque la taille de la fenetre
    */
	GameWindow(String s, Partie _game, int _size)
	{
		super(s);
		game=_game;
		size=_size;
		setSize(size,size+100);
        setLocationRelativeTo(null);
		setResizable(false);
        Init_Menu();
		zone = new ZoneDessinPente(game,size);
		setContentPane(zone);
		Evenement e = new Evenement(game);
		addMouseListener(e);
		addMouseMotionListener(e);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
    * Getter sur la taille de la fenetre
	* @return la taille de la fenetre
    */
	public int getSizeFenetre()
	{
		return size;
	}

	/**
    * Getter sur la zone de dessin de la fenetre
	* @return la zone de dessin de la fenetre
    */
	public ZoneDessinPente getZoneDessin()
	{
		return zone;
	}
	
	/**
    * Fonction qui repaint la zone de dessin
    */
	public void _repaint()
	{
		zone.repaint();
	}

	/**
    * Fonction qui initialise le menu
    */
    public void Init_Menu()
    {
        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		JMenuItem itemQuitter =new JMenuItem("Quitter");
		itemQuitter.setActionCommand("menu_quitter");
		itemQuitter.addActionListener(this);
		menuFichier.add(itemQuitter);
		
		JMenu menuEdit = new JMenu("Edit");
		JMenuItem itemTheme =new JMenuItem("Choisir theme");
		JMenuItem itemNameJoueur1 =new JMenuItem("Choisir le nom du joueur 1");
		JMenuItem itemNameJoueur2 =new JMenuItem("Choisir le nom du joueur 2");
		itemTheme.setActionCommand("menu_theme");
		itemNameJoueur1.setActionCommand("menu_name_joueur_1");
		itemNameJoueur2.setActionCommand("menu_name_joueur_2");
		itemTheme.addActionListener(this);
		itemNameJoueur1.addActionListener(this);
		itemNameJoueur2.addActionListener(this);
		menuEdit.add(itemTheme);
		menuEdit.add(new JSeparator());
		menuEdit.add(itemNameJoueur1);
		menuEdit.add(itemNameJoueur2);
		menuBar.add(menuEdit);


		JMenu menuInfo = new JMenu("Informations");
		JMenuItem itemScoreBoard =new JMenuItem("ScoreBoard");
		itemScoreBoard.setActionCommand("menu_score_board");
		itemScoreBoard.addActionListener(this);
		menuInfo.add(itemScoreBoard);
		menuBar.add(menuInfo);
	}

	/**
    * fonction qui detecte un evenement sur la fenetre
    * @param evenement ActionEvent qui stoque l'évenement capté
    */
    public void actionPerformed(ActionEvent evenement)
	{
		if (evenement.getActionCommand().equals("menu_quitter"))
		{
			if( JOptionPane.showConfirmDialog(null,"Voulez vous quitter ?",
			"Quitter",
			JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION )
			{
				this.dispose();
			}
		}
		else if (evenement.getActionCommand().equals("menu_name_joueur_1"))
		{
			boolean end=false;
			do
			{
				end=true;
				String s = (String)JOptionPane.showInputDialog(
   						null,
   						"Veuillez Indiquez le nom du joueur 1 :  (9 caractères max)",
   						"Changer le nom du joueur 1",
   						JOptionPane.QUESTION_MESSAGE,
  						null,
   						null,
						   game.getJoueur(1).getNom());
				if (s!=null && (s.length() > 0) && (s.length() < 10))
				{
					game.getJoueur(1).setNom(s);
				}
				else if (s!=null)
					end=false;
			}while(!end);
		}
		else if (evenement.getActionCommand().equals("menu_name_joueur_2"))
		{
			boolean end=false;
			do
			{
				end=true;
				String s = (String)JOptionPane.showInputDialog(
   							null,
   							"Veuillez Indiquez le nom du joueur 2 :  (9 caractères max)",
   							"Changer le nom du joueur 2",
   							JOptionPane.QUESTION_MESSAGE,
  							null,
   							null, // c'est ouvert !!!
							   game.getJoueur(2).getNom()); // valeur initiale
				if (s!=null && (s.length() > 0) && (s.length() < 10))
				{
					game.getJoueur(2).setNom(s);
				}
				else if (s!=null)
					end=false;
			}while(!end);
		}
		else if (evenement.getActionCommand().equals("menu_theme"))
		{
			String rep=(String)JOptionPane.showInputDialog(
				null,
				"Veuillez choisir le theme :\n",
				"Choix du theme",
				JOptionPane.QUESTION_MESSAGE,
				null,
				game.getThemes(),
				game.getTheme());
			if(rep != null)
			{
				game.setTheme(rep);
				game.getSons().setPath(game.getPathByTheme(rep, 0));
			}
		}
		else if (evenement.getActionCommand().equals("menu_score_board"))
		{
			if(w==null || !w.isShowing())
				w = new ScoreBoardWindow(size);
		}
    }
}
