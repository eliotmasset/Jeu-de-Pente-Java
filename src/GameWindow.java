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

class GameWindow extends JFrame implements ActionListener
{
	ZoneDessinPente zone;
	Partie game;
	int size;

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
		FenetrePrincipal();
		addMouseListener(new Evenement(game));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public int getSizeFenetre()
	{
		return size;
	}

	public ZoneDessinPente getZoneDessin()
	{
		return zone;
	}
	
	public void _repaint()
	{
		zone.repaint();
	}
    
    public void FenetrePrincipal()
    {
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
		
		JMenu menuEdit = new JMenu("Edit");
		JMenuItem itemTheme =new JMenuItem("Choisir theme");
		itemTheme.setActionCommand("menu_theme");
		itemTheme.addActionListener(this);
		menuEdit.add(itemTheme);
		menuBar.add(menuEdit);
	}

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
		else if (evenement.getActionCommand().equals("menu_theme"))
		{
			String rep=(String)JOptionPane.showInputDialog(
				null,
				"Veuillez choisir le theme :\n",
				"Choix du theme",
				JOptionPane.QUESTION_MESSAGE,
				null,
				game.getThemes(),
				game.getThemesAt(0));
			if(rep != null)
			{
				game.setTheme(rep);
			}
		}
    }
}
