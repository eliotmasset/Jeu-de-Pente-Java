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
import javax.sound.sampled.*;

class MenuWindow extends JFrame
{
    private int size;
	private ZoneDessinMenu zone;
	private Sons son;

	MenuWindow(String s, int _size)
	{
		super(s);
		size=_size;
		setSize(size,size);
        setLocationRelativeTo(null);
		setResizable(false);
		son = new Sons();
		son.setPath("../son/start.wav");
		Thread thread = new Thread(son);
		thread.start();
		zone = new ZoneDessinMenu(size,son);
		setContentPane(zone);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	

	public int getSizeFenetre()
	{
		return size;
	}

	public String[] getThemes()
    {
        return zone.getThemes();
    }

    public String getThemesAt(int index)
    {
        return zone.getThemesAt(index);
    }

}
