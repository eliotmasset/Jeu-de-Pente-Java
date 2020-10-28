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

/**
 * Classe de la fenetre de ScoreBoard
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class ScoreBoardWindow extends JFrame// implements ActionListener
{
	/**
    * taille de la fenetre
    */
	private int size;
	/**
    * zone de dessin du scoreboard
    */
    private zoneDessinScoreBoard zone;

	/**
    * Constructeur de la fenetre du scoreboard
	* @param _size int qui stoque la taille de la fenetre
    */
	ScoreBoardWindow(int _size)
	{
		size=_size;
		setSize(size,size);
        setLocationRelativeTo(null);
        setResizable(false);
        zone = new zoneDessinScoreBoard(size);
		setContentPane(zone);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
    }
}
