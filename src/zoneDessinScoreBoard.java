import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.util.regex.*;

class zoneDessinScoreBoard extends JPanel
{
    private int size;
    private JLabel titre, Scores;
    
	zoneDessinScoreBoard(int _size)
	{
        setLayout(null);
		size=_size;
        setSize(size,size);
        titre = new JLabel("ScoreBoard : ");
        Scores = new JLabel("");
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        ScorePlayers();
        affiche_ScoreBoard();
    }
    
    private void affiche_ScoreBoard()
	{
        Font font1 = new Font("Arial",Font.BOLD,40);
        Font font2 = new Font("Arial",Font.BOLD,18);

        setBackground(new Color(34,0,111));

        titre.setText("ScoreBoard : ");
        titre.setForeground(new Color(255,255,255));
        titre.setOpaque(false);
        titre.setBounds(size/2-titre.getText().length()*11, size/20,titre.getText().length()*30,titre.getText().length()*4);
        titre.setFont(font1);

        
        Scores.setForeground(new Color(255,255,255));
        Scores.setOpaque(false);
        Scores.setBounds(size/4, size/10,titre.getText().length()*60,titre.getText().length()*40);
        Scores.setFont(font2);

        add(titre);
        add(Scores);
    }
    
    public void ScorePlayers()
    {
        File file = new File("ScoreBoard.txt");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String reader= br.readLine();
            StringBuilder builder = new StringBuilder();
            while(reader!=null)
            {
                Pattern p = Pattern.compile("    ");
                String[] items = p.split(reader, 10);
                if(items.length>=3)
                {
                    String addBuilder="";
                    addBuilder+=items[0]+"               ";
                    for(int i=0;i<9-items[0].length();i++)
                        addBuilder+=" ";
                    addBuilder+=items[2]+"<br><br>";
                    builder.append(addBuilder);
                }
                reader = br.readLine();
            }
            Scores.setText("<html><pre>Pseudos :        Nombre de points :<br><br><br>"+builder.toString()+"</pre></html>");
            br.close();
            
        }
        catch(IOException ioe)
        {
            System.err.println("IOException:" + ioe.getMessage());
        }
        
    }
}
