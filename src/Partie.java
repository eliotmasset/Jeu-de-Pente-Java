import java.util.*;
import javax.swing.*;
import java.net.URL;
import java.io.*;
import java.lang.Math;


class Partie
{  
    private Plateau plate;
    private int eventMouseX,eventMouseY;
    private final int decalMenu=25;
    private Joueur joueur1, joueur2;
    private String current_Joueur;
    private GameWindow fenetre;
    private boolean finPartie;
    private int pointToWin;
    private int NbSameColorToWin;
    private String theme;
    private int nbCase;
    private String[] themes;
    private int nbTour;
    

	Partie(String[] _themes,int size, int _nbCase, int _pointToWin, int _NbSameColorToWin, String _theme)
	{
        themes=_themes;
        pointToWin=_pointToWin;
        NbSameColorToWin=_NbSameColorToWin;
        nbCase=_nbCase;
        theme=_theme;
        nbTour=0;
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(nbCase,nbCase, size, theme);
        fenetre = new GameWindow("Jeu de pente", this, size);
        eventMouseX=0;
        eventMouseY=0;
        finPartie=false;
        current_Joueur=joueur2.getNom();
        plate.getCaseAt(fenetre.getSizeFenetre()/2, fenetre.getSizeFenetre()/2+25).setPath(plate.getPathBy(theme, "clair"));
    }

    public String getTheme()
    {
        return theme;
    }

    public String getPathByTheme(String Theme)
    {
        String rep="";
        switch(Theme)
        {
            case "normal":
                rep="../son/partie1.wav";
                break;
            case "sombre":
                rep="../son/partie1.wav";
                break;
            case "clair":
                rep="../son/partie1.wav";
                break;
            default:
                break;
        }
        return rep;
    }

    public String[] getThemes()
    {
        return themes;
    }

    public String getThemesAt(int index)
    {
        return themes[index];
    }

    public void setTheme(String _theme)
    {
        theme=_theme;
        plate.setThemePlate(theme);
        for(Case c : plate.getCases())
        {
            c.setPath(plate.getPathBy(theme, plate.getStatuBy(c.getPath())));
        }
        fenetre.repaint();
    }

    public Joueur getJoueur(int i)
    {
        if(i==1)
            return joueur1;
        else
            return joueur2;
    }

    public GameWindow getFenetre()
    {
        return fenetre;
    }

    public Plateau getPlateau()
    {
        return plate;
    }

    public String getCurrentJoueur()
    {
        return current_Joueur;
    }

    public void setCurrentJoueur(String c)
    {
        current_Joueur=c;
    }

    public int getNbPointToWin()
    {
        return pointToWin;
    }

    public boolean isFinish()
    {
        return finPartie;
    }

    public int getNbTour()
    {
        return nbTour;
    }

    private void tourdejeu(Joueur j)
    {
        nbTour++;
        current_Joueur=j.getNom();
        if(nbTour==2 && ((eventMouseX>=fenetre.getSizeFenetre()/2+((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2)
        || (eventMouseX<=fenetre.getSizeFenetre()/2-((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2)
        || (eventMouseY>=fenetre.getSizeFenetre()/2+((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2+25)
        || (eventMouseY<=fenetre.getSizeFenetre()/2-((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2+25)))
        {
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(plate.getPathBy(theme, "clair"));
            algo();
            fenetre._repaint();
        }
        else if(nbTour!=2 && j==joueur1 && !(eventMouseX==0 && eventMouseY==0))
        {
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(plate.getPathBy(theme, "sombre"));
            algo();
            fenetre._repaint();
        }
        else if(nbTour!=2 && j==joueur2 && !(eventMouseX==0 && eventMouseY==0))
        {
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(plate.getPathBy(theme, "clair"));
            algo();
            fenetre._repaint();
        }
        if(isWin()==joueur1 || isWin()==joueur2)
        {
            fenetre.getZoneDessin().afficheEstGagne();
            finPartie=true;
        }
    }

    private Joueur isWin()
    {
        if(joueur1.getNbPoint()>=pointToWin)
            return joueur1;
        else if(joueur2.getNbPoint()>=pointToWin)
            return joueur2;
        else
            return null;
    }

    private void algo()
    {
		for(int i=-1;i<=1;i++)
		{
			for(int j=-1;j<=1;j++)
			{
                if(!(i==0 && j==0))
                    vecteur(i,j);
			}
		}
    }
    
    private void vecteur(int i, int j)
    {
		String ColorAdversaire;
		String Color=plate.getCaseAt(eventMouseX, eventMouseY).getPath();
		if(Color==plate.getPathBy(theme, "sombre"))
			ColorAdversaire=plate.getPathBy(theme, "clair");
		else
            ColorAdversaire=plate.getPathBy(theme, "sombre");
        
        if(eventMouseY+(3*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))<decalMenu || eventMouseX+(3*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))<0 
        || eventMouseX+(3*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(3*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))>fenetre.getSizeFenetre()+decalMenu)
        {}
        else 
        {
            if(plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(2*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(2*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(3*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(3*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==Color)
            {
                plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                plate.getCaseAt(eventMouseX+(2*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(2*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                if(current_Joueur==joueur1.getNom())
                    joueur1.setNbPoint(joueur1.getNbPoint()+2);
                else if(current_Joueur==joueur2.getNom())
                    joueur2.setNbPoint(joueur2.getNbPoint()+2);
            }
        }
        boolean suite=false;
        int nbPions=0;
        for(int d=2+(-1*(NbSameColorToWin));d<NbSameColorToWin;d++)
        {
            if(eventMouseY+(d*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))<decalMenu || eventMouseX+(d*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))<0 
            || eventMouseX+(d*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(d*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))>fenetre.getSizeFenetre()+decalMenu)
            {}
            else
            {
                if(!suite)
                    nbPions=0;
                if(plate.getCaseAt(eventMouseX+(d*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(d*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==Color)
                {
                    suite=true;
                    nbPions++;
                }
                else
                    suite=false;
                if(nbPions>=NbSameColorToWin)
                {
                    fenetre.getZoneDessin().afficheEstGagne();
                    finPartie=true;
                }
            }
        }
    }

    public void saveScorePlayer(Joueur j)
    {
        File file = new File("ScoreBoard.txt");
        File fileTemp = new File("ScoreBoardTemp.txt");
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
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String reader= br.readLine();
            boolean trouve=false;
            while(reader!=null)
            {
                if(j.getNom().equalsIgnoreCase(reader.substring(0,j.getNom().length())))
                {
                    trouve=true;
                    reader=reader.replaceAll(j.getNom()+"    |    "+reader.substring(j.getNom().length()+9, reader.length()),j.getNom()+"    |    "+String.valueOf(Integer.parseInt(reader.substring(j.getNom().length()+9, reader.length()))+1));
                    reader=reader.substring(0,reader.length()/2);
                }
                bw.write(reader+"\n");
                bw.flush();
                reader = br.readLine();
            }
            if(!trouve)
                bw.write(j.getNom()+"    |    1\n");
            bw.close();
            file.delete();
            fileTemp.renameTo(file);
            br.close();
            
        }
        catch(IOException ioe)
        {
            System.err.println("IOException:" + ioe.getMessage());
        }
        
    }

    public void clicEvent(int x,int y)
    {
        this.eventMouseX=x;
        this.eventMouseY=y;
        if(finPartie)
        {
            if(current_Joueur==joueur1.getNom())
                saveScorePlayer(joueur1);
            else if(current_Joueur==joueur2.getNom())
                saveScorePlayer(joueur2);
            fenetre.dispose();
        }
        else if(nbTour==0)
        {
            tourdejeu(joueur1);
        }
        else if(current_Joueur==joueur2.getNom()
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath()==plate.getPathBy(theme, "vide"))
        {
            tourdejeu(joueur1);
        }
        else if (current_Joueur==joueur1.getNom()
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath()==plate.getPathBy(theme, "vide"))
        {
            tourdejeu(joueur2);
        }
    }

}
