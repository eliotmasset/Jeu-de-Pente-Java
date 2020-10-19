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

class MenuWindow extends JFrame implements ActionListener
{
    int size;
	ZoneDessinMenu zone;
	JButton lancerPartie, options, quitter;

	MenuWindow(String s, int _size)
	{
		super(s);
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
			Partie game = new Partie(size);
		}
		else if (evenement.getSource()==options)
		{
			
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
