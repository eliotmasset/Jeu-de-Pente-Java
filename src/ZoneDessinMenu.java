import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.IOException;

class ZoneDessinMenu extends JPanel
{
    int size;
    
	ZoneDessinMenu(int _size)
	{
		//setLayout(null);
		size=_size;
        setSize(size,size);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        affiche_Menu(g);
    }
    
    public void affiche_Menu(Graphics g)
	{
		Image img=null;
		try 
		{
			img = ImageIO.read(new File("../img/Menu-Pente.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        g.drawImage(img,0,0,this);
	}
}
