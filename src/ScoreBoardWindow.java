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

class ScoreBoardWindow extends JFrame// implements ActionListener
{
    private int size;
    private zoneDessinScoreBoard zone;

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
