import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

class ZoneDessinMenu extends JPanel implements ActionListener
{
	private int size;
	private Sons son;
	private JButton lancerPartie, scoreboard, options, quitter;
	private MouseAdapter mouseListener;
	private ScoreBoardWindow sc;
	private Partie game;
	private String paramPartie[];
	private String themes[];
    
	ZoneDessinMenu(int _size, Sons _son)
	{
		son=_son;
		setLayout(null);
		size=_size;
		setSize(size,size);
		paramPartie = new String[4];
		paramPartie[0]="19";
		paramPartie[1]="10";
		paramPartie[2]="5";
		paramPartie[3]="normal";
		themes = new String[3];
        themes[0]="normal";
        themes[1]="sombre";
        themes[2]="clair";
		setButtons();
	}

	public String[] getThemes()
    {
        return themes;
    }

    public String getThemesAt(int index)
    {
        return themes[index];
    }
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
		affiche_Menu(g);
		reloadSong();
	}

	public void reloadSong()
	{
		if(game==null || !game.getFenetre().isVisible())
			son.setPath("../son/start.wav");
		else
			son.setPath(game.getPathByTheme(paramPartie[3]));
	}
    
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

	public void setButtons()
	{

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
				if (evenement.getSource()==lancerPartie)
				{
					try {
						Image img = ImageIO.read(new File("../img/select.png"));
						lancerPartie.setIcon(new ImageIcon(img));
						lancerPartie.setHorizontalAlignment(SwingConstants.LEFT);
					} catch (IOException ex) {
					}
				}
				if (evenement.getSource()==scoreboard)
				{
					try {
						Image img = ImageIO.read(new File("../img/select.png"));
						scoreboard.setIcon(new ImageIcon(img));
						scoreboard.setHorizontalAlignment(SwingConstants.LEFT);
					} catch (IOException ex) {
					}
				}
				if (evenement.getSource()==options)
				{
					try {
						Image img = ImageIO.read(new File("../img/select.png"));
						options.setIcon(new ImageIcon(img));
						options.setHorizontalAlignment(SwingConstants.LEFT);
					} catch (IOException ex) {
					}
				}
				if (evenement.getSource()==quitter)
				{
					try {
						Image img = ImageIO.read(new File("../img/select.png"));
						quitter.setIcon(new ImageIcon(img));
						quitter.setHorizontalAlignment(SwingConstants.LEFT);
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

	public void actionPerformed(ActionEvent evenement)
	{
		if (evenement.getSource()==lancerPartie)
		{
			if(son!=null)
				son.setPath(game.getPathByTheme(paramPartie[3]));
			if(game==null)
			{
				game = new Partie(themes,size,Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3]);
			}
			else if(!game.getFenetre().isVisible())
				game = new Partie(themes,size,Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3]);
		}
		if (evenement.getSource()==scoreboard)
		{
			sc = new ScoreBoardWindow(size);
		}
		else if (evenement.getSource()==options)
		{
			JComboBox<String> themes = new JComboBox<>(getThemes());
			JLabel lab0 = new JLabel("Theme : ");
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel lab1 = new JLabel("Nombre de Case : ");
			JSpinner nbCase = new JSpinner(new SpinnerNumberModel(19,8,25,2));
			JLabel lab2 = new JLabel("Objectif de point : ");
			JSpinner pointToWin = new JSpinner(new SpinnerNumberModel(10,2,100,2));
			JLabel lab3 = new JLabel("Objectif de pions à aligner : ");
			JSpinner nbSameColorToWin = new JSpinner(new SpinnerNumberModel(5,4,10,1));
			panel.add(lab0);
			panel.add(themes);
			panel.add(lab1);
			panel.add(nbCase);
			panel.add(lab2);
			panel.add(pointToWin);
			panel.add(lab3);
			panel.add(nbSameColorToWin);
    		int result = JOptionPane.showConfirmDialog(null, panel, "Options", 
    			JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); 
    		if (result == JOptionPane.OK_OPTION) { 
				paramPartie[0]=String.valueOf(nbCase.getValue());
				paramPartie[1]=String.valueOf(pointToWin.getValue());
				paramPartie[2]=String.valueOf(nbSameColorToWin.getValue());
    		  	paramPartie[3]=(String)themes.getSelectedItem();
    		} else { 
    		  	
    		}
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
