import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;


/**
 * Classe de la zone de dessin du menu
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class ZoneDessinMenu extends JPanel implements ActionListener
{
	/**
    * Taille de la fenetre
    */
	private int size;
	/**
	* musique de fond
	* @see Sons
    */
	private Sons son;
	/**
    * boutons du menu
    */
	private JButton lancerPartie, scoreboard, options, quitter;
	/**
    * listener des actions de la souris
    */
	private MouseAdapter mouseListener;
	/**
	* Fenetre du ScoreBoard
	* @see ScoreBoardWindow
    */
	private ScoreBoardWindow sc;
	/**
	* Partie de pente
	* @see Partie
    */
	private Partie game;
	/**
    * paramètres de partie et liste des themes existant
    */
	private String paramPartie[],themes[];
	/**
    * fenetre du menu
    */
	private MenuWindow mw;
	
	/**
    * Constructeur de la zone de dessin
	* @param _size int qui stoque la taille de la fenetre
	* @param _son qui permet de lancer des sons
	* @param _mw Fenetre du menu
    */
	ZoneDessinMenu(int _size, Sons _son, MenuWindow _mw)
	{
		mw=_mw;
		son=_son;
		setLayout(null);
		size=_size;
		setSize(size,size);
		paramPartie = new String[5];
		paramPartie[0]="19";
		paramPartie[1]="10";
		paramPartie[2]="5";
		paramPartie[3]="normal";
		paramPartie[4]=String.valueOf(size);
		themes = new String[8];
        themes[0]="normal";
        themes[1]="sombre";
		themes[2]="clair";
		themes[3]="halloween";
		themes[4]="noel";
		themes[5]="zelda";
		themes[6]="harry potter";
		themes[7]="os";
		setButtons();
	}

	/**
    * Getter sur la liste des thèmes
	* @return la liste des thèmes
    */
	public String[] getThemes()
    {
        return themes;
    }

	/**
	* getter sur le X theme
	* @param index int qui stoque l'index
    * @return un theme choisi celon l'index
    */
    public String getThemesAt(int index)
    {
        return themes[index];
    }
	
	/**
    * fonction qui rafraichi la fenetre 
    * @param g Graphics qui permet de dessiner la fenetre
    */
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
		affiche_Menu(g);
		reloadSong();
	}

	/**
    * Met à jour la musique de fond
    */
	private void reloadSong()
	{
		if(game==null || !game.getFenetre().isVisible())
			son.setPath("../son/start.wav");
		else
			son.setPath(game.getPathByTheme(paramPartie[3],0));
	}
	
	/**
    * Affiche le menu
    * @param g Graphics qui permet de dessiner le menu
    */
    private void affiche_Menu(Graphics g)
	{
		Image img=null;
		try 
		{
			img = ImageIO.read(new File("../img/Menu-Pente.jpg"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        g.drawImage(img,0,0,size,size,this);
	}

	/**
    * Met en place les boutons
    */
	private void setButtons()
	{
		if(lancerPartie!=null)
		{
			remove(lancerPartie);
			remove(options);
			remove(scoreboard);
			remove(quitter);
		}
		lancerPartie = new JButton("");
		lancerPartie.setBounds(size/8, (23*size)/64, (3*size)/4, size/10);
		lancerPartie.setOpaque(false);
		lancerPartie.setContentAreaFilled(false);
		lancerPartie.setBorderPainted(false);
		lancerPartie.setFocusPainted(false);
		lancerPartie.addActionListener(this);
		add(lancerPartie);

		scoreboard = new JButton("");
		scoreboard.setBounds(size/8, (59*size)/128, size/2, size/10);
		scoreboard.setOpaque(false);
		scoreboard.setContentAreaFilled(false);
		scoreboard.setBorderPainted(false);
		scoreboard.setFocusPainted(false);
		scoreboard.addActionListener(this);
		add(scoreboard);

		options = new JButton("");
		options.setBounds(size/8, (73*size)/128, size/2, size/10);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		options.setFocusPainted(false);
		options.addActionListener(this);
		add(options);

		quitter = new JButton("");
		quitter.setBounds(size/3, (87*size)/128, (5*size)/12, size/10);
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setBorderPainted(false);
		quitter.setFocusPainted(false);
		quitter.addActionListener(this);
		add(quitter);

		mouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evenement) 
			{
				if (evenement.getSource()==lancerPartie || evenement.getSource()==scoreboard || evenement.getSource()==options || evenement.getSource()==quitter)
				{
					try {
						JButton temp = (JButton)evenement.getSource();
						Image img = ImageIO.read(new File("../img/select.png"));
						temp.setIcon(new ImageIcon(img));
						temp.setHorizontalAlignment(SwingConstants.LEFT);
					} catch (IOException ex) {
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent evenement) 
			{
				if (evenement.getSource()==lancerPartie)
				{
					lancerPartie.setIcon(null);
				}
				if (evenement.getSource()==scoreboard)
				{
					scoreboard.setIcon(null);
				}
				if (evenement.getSource()==options)
				{
					options.setIcon(null);
				}
				if (evenement.getSource()==quitter)
				{
					quitter.setIcon(null);
				}
			}
		};
		lancerPartie.addMouseListener(mouseListener);
		scoreboard.addMouseListener(mouseListener);
		options.addMouseListener(mouseListener);
		quitter.addMouseListener(mouseListener);
	}

	/**
    * fonction qui detecte un evenement sur la fenetre
    * @param evenement ActionEvent qui stoque l'évenement capté
    */
	public void actionPerformed(ActionEvent evenement)
	{
		if (evenement.getSource()==lancerPartie)
		{
			if(son!=null)
				son.setPath(game.getPathByTheme(paramPartie[3],0));
			if(game==null)
			{
				System.out.println(paramPartie[3]);
				game = new Partie(themes,Integer.parseInt(paramPartie[4]),Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3],son);
			}
			else if(!game.getFenetre().isVisible())
				game = new Partie(themes,Integer.parseInt(paramPartie[4]),Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3],son);
		}
		if (evenement.getSource()==scoreboard)
		{
			sc = new ScoreBoardWindow(size);
		}
		else if (evenement.getSource()==options)
		{
			JComboBox<String> themes = new JComboBox<>(getThemes());
			themes.setSelectedItem(paramPartie[3]);;
			JLabel lab0 = new JLabel("Theme : ");
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel lab1 = new JLabel("Nombre de Case : ");
			JSpinner nbCase = new JSpinner(new SpinnerNumberModel(Integer.parseInt(paramPartie[0]),8,25,2));
			JLabel lab2 = new JLabel("Objectif de point : ");
			JSpinner pointToWin = new JSpinner(new SpinnerNumberModel(Integer.parseInt(paramPartie[1]),2,100,2));
			JLabel lab3 = new JLabel("Objectif de pions à aligner : ");
			JSpinner nbSameColorToWin = new JSpinner(new SpinnerNumberModel(Integer.parseInt(paramPartie[2]),4,10,1));
			JLabel lab4 = new JLabel("Taille de la fenetre de jeu : ");
			JSpinner tailleFenetre = new JSpinner(new SpinnerNumberModel(Integer.parseInt(paramPartie[4]),400,1200,100));
			JLabel lab5 = new JLabel("Volume : ");
			JSlider choixson = new JSlider(JSlider.HORIZONTAL,0, 100, (int)(Math.round(son.getVolume())));
			panel.add(lab0);
			panel.add(themes);
			panel.add(lab1);
			panel.add(nbCase);
			panel.add(lab2);
			panel.add(pointToWin);
			panel.add(lab3);
			panel.add(nbSameColorToWin);
			panel.add(lab4);
			panel.add(tailleFenetre);
			panel.add(lab5);
			panel.add(choixson);
    		int result = JOptionPane.showConfirmDialog(null, panel, "Options", 
    			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
    		if (result == JOptionPane.OK_OPTION) { 
				paramPartie[0]=String.valueOf(nbCase.getValue());
				paramPartie[1]=String.valueOf(pointToWin.getValue());
				paramPartie[2]=String.valueOf(nbSameColorToWin.getValue());
				paramPartie[3]=(String)themes.getSelectedItem();
				paramPartie[4]=String.valueOf(tailleFenetre.getValue());
				son.setVolume((float)Integer.parseInt(String.valueOf(choixson.getValue())));
    		} else { 
    		  	
			}
			size=Integer.parseInt(String.valueOf(tailleFenetre.getValue()));
			setSize(size,size);
			mw.setSize(size,size);
			setButtons();
		}
		else if (evenement.getSource()==quitter)
		{
			System.exit(0);
		}
		else if (evenement.getActionCommand().equals("menu_quitter"))
		{
			if( JOptionPane.showConfirmDialog(null,"Voulez vous quitter ?",
			"Quitter",
			JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION )
			{
				System.exit(0);
			}
		}	
	}
}
