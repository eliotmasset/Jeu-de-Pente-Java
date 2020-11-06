import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.text.*;
import javax.sound.sampled.*;

/**
 * Classe de la fenetre du menu (JFrame)
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class MenuWindow extends JFrame
{
	/**
    * Taille de la fenetre
    */
	private int size;
	/**
    * zone de dessin du menu
    * @see ZoneDessinMenu
    */
	private ZoneDessinMenu zone;
	/**
    * son qui permet de lancé des sons
    * @see Sons
    */
	private Sons son;

	/**
    * Constructeur de la classe MenuWindow
	* @param s String qui stoque le nom de la fenetre
	* @param _size int qui stoque la taille de la fenetre
    */
	MenuWindow(String s, int _size)
	{
		super(s);
		size=_size;
		setSize(size,size+25);
        setLocationRelativeTo(null);
		setResizable(false);
		son = new Sons();
		son.setPath("../son/start.wav");
		Thread thread = new Thread(son);
		thread.start();
		zone = new ZoneDessinMenu(size,son,this);
		setContentPane(zone);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	

	/**
    * getter sur la taille de la fenetre
    * @return la taille de la fenetre
    */
	public int getSizeFenetre()
	{
		return size;
	}

	/**
    * getter sur le tableau de theme
    * @return le tableau de themes
    */
	public String[] getThemes()
    {
        return zone.getThemes();
    }

	/**
	* getter sur le X theme
	* @param index int qui stoque l'index
    * @return un theme choisi celon l'index
    */
    public String getThemesAt(int index)
    {
        return zone.getThemesAt(index);
    }

}
