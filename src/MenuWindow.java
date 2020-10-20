import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.text.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MenuWindow extends JFrame implements ActionListener
{
    int size;
	ZoneDessinMenu zone;
	JButton lancerPartie, options, quitter;
	Partie game;
	String paramPartie[];
	String themes[];

	MenuWindow(String s, int _size)
	{
		super(s);
		paramPartie = new String[4];
		paramPartie[0]="19";
		paramPartie[1]="10";
		paramPartie[2]="5";
		paramPartie[3]="normal";
		themes = new String[3];
        themes[0]="normal";
        themes[1]="sombre";
        themes[2]="clair";
		size=_size;
		setSize(size,size);
        setLocationRelativeTo(null);
        setResizable(false);
		zone = new ZoneDessinMenu(size);
		setContentPane(zone);
		setButtons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setButtons()
	{
		lancerPartie = new JButton("");
		lancerPartie.setBounds(100, 280, 600, 80);
		lancerPartie.setOpaque(false);
		lancerPartie.setContentAreaFilled(false);
		lancerPartie.setBorderPainted(false);
		lancerPartie.addActionListener(this);
		add(lancerPartie);

		options = new JButton("");
		options.setBounds(100, 405, 400, 80);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		options.addActionListener(this);
		add(options);

		quitter = new JButton("");
		quitter.setBounds(100, 530, 300, 80);
		quitter.setOpaque(false);
		quitter.setContentAreaFilled(false);
		quitter.setBorderPainted(false);
		quitter.addActionListener(this);
		add(quitter);
	}

	public int getSizeFenetre()
	{
		return size;
	}

	public String[] getThemes()
    {
        return themes;
    }

    public String getThemesAt(int index)
    {
        return themes[index];
    }

    public void Init_Menu()
    {
        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);
		JMenuItem itemCharger =new JMenuItem("Charger");
		JMenuItem itemSave =new JMenuItem("Sauvegarder");
		JMenuItem itemQuitter =new JMenuItem("Quitter");
		//itemCharger.setActionCommand("menu_charger");
		//itemCharger.addActionListener(this);
		//itemSave.setActionCommand("menu_sauvegarder");
		//itemSave.addActionListener(this);
		itemQuitter.setActionCommand("menu_quitter");
		itemQuitter.addActionListener(this);
		menuFichier.add(itemCharger);
		menuFichier.add(itemSave);
		menuFichier.add(new JSeparator());
        menuFichier.add(itemQuitter);
	}

    public void actionPerformed(ActionEvent evenement)
	{
		if (evenement.getSource()==lancerPartie)
		{
			if(game==null)
			{
				game = new Partie(themes,size,Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3]);
			}
			else if(!game.getFenetre().isVisible())
				game = new Partie(themes,size,Integer.parseInt(paramPartie[0]),Integer.parseInt(paramPartie[1]),Integer.parseInt(paramPartie[2]),paramPartie[3]);
		}
		else if (evenement.getSource()==options)
		{
			JComboBox<String> themes = new JComboBox<>(getThemes());
			JLabel lab0 = new JLabel("Theme : ");
			JPanel panel = new JPanel(new GridLayout(0, 1));
			JLabel lab1 = new JLabel("Nombre de Case : ");
			JSpinner nbCase = new JSpinner(new SpinnerNumberModel(19,8,25,1));
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
