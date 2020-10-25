import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.util.regex.*;

class zoneDessinScoreBoard extends JPanel
{
    private int size;
    private JLabel titre;
    private JTextArea Scores;
    private JScrollPane scrollPane;
    
	zoneDessinScoreBoard(int _size)
	{
        setLayout(null);
		size=_size;
        setSize(size,size);
        titre = new JLabel("ScoreBoard : ");
        Scores = new JTextArea("");
        scrollPane = new JScrollPane(Scores);
        affiche_ScoreBoard();
        ScorePlayers();
	}
    
    private void affiche_ScoreBoard()
	{
        Font font1 = new Font("Arial",Font.BOLD,40);
        Font font2 = new Font("Courier",Font.BOLD,18);

        setBackground(new Color(34,0,111));

        Scores.setForeground(new Color(255,255,255));
        Scores.setEditable(false);
        Scores.setOpaque(false);
        Scores.setFont(font2);
        Scores.setBounds(size/4, size/6,(6*size)/10,(8*size)/10);

        scrollPane.setBounds(size/4, size/6,(6*size)/10,(8*size)/10);
        scrollPane.setBackground(new Color(34,0,111));
        scrollPane.getViewport().setBackground(new Color(34,0,111));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
        

        titre.setText("ScoreBoard : ");
        titre.setForeground(new Color(255,255,255));
        titre.setOpaque(false);
        titre.setBounds(size/2-titre.getText().length()*11, size/20,titre.getText().length()*30,titre.getText().length()*4);
        titre.setFont(font1);

        add(titre);
        add(scrollPane);
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
                    String addBuilder="   ";
                    addBuilder+=items[0]+"               ";
                    for(int i=0;i<9-items[0].length();i++)
                        addBuilder+=" ";
                    addBuilder+=items[2]+"\n\n";
                    builder.append(addBuilder);
                }
                reader = br.readLine();
            }
            Scores.setText("Pseudos :           Nombre de points :\n\n\n"+builder.toString());
            Scores.setCaretPosition(0);
            br.close();
            
        }
        catch(IOException ioe)
        {
            System.err.println("IOException:" + ioe.getMessage());
        }
        
    }
}
