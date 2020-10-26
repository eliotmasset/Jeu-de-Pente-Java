import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessinMenu extends JPanel
{
	private int size;
    
	ZoneDessinMenu(int _size)
	{
		setLayout(null);
		size=_size;
		setSize(size,size);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        affiche_Menu(g);
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
}
