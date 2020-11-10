import java.util.*;
import javax.swing.*;
import java.net.URL;
import java.io.*;
import java.lang.Math;
import java.nio.file.Files; 
import java.nio.file.*;

/**
 * Classe d'une partie
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class Partie
{  
    /**
    * plateau de jeu
    * @see Plateau
    */
    private Plateau plate;
    /**
	* position x et y de la souris
    */
    private int eventMouseX,eventMouseY;
    /**
    * joueur 1 et 2
    * @see Joueur
    */
    private Joueur joueur1, joueur2;
    /**
	* nom du joueur qui est entrain de joueur
    */
    private String current_Joueur;
    /**
    * fenetre de jeu
    * @see GameWindow
    */
    private GameWindow fenetre;
    /**
	* Boolean pour savoir si la partie est terminée
    */
    private boolean finPartie;
    /**
	* nombre de points pour gagner
    */
    private int pointToWin;
    /**
	* nombre de pion de même couleurs côte à côte pour gagner
    */
    private int NbSameColorToWin;
    /**
	* theme d'affichage
    */
    private String theme;
    /**
	* nombre de cases du plateau
    */
    private int nbCase;
    /**
	* liste des themes
    */
    private String[] themes;
    /**
	* nombre de tours effectués
    */
    private int nbTour;
    /**
    * différens sons de partie
    * @see Sons
    */
    private transient Sons bruit1,bruit2,son;
    /**
	* boolean pour savoir si des pions ont été capturés
    */
    private boolean capture;
    

    /**
    * Constructeur d'une partie
	* @param _themes qui stoque la liste de themes
    * @param size qui stoque la taille de la fenetre
    * @param _nbCase qui stoque le nombre de case du plateau
    * @param _pointToWin qui stoque le nombre de poins pour gagner
    * @param _NbSameColorToWin qui stoque le nombre de pion de même couleurs côte à côte pour gagner
    * @param _theme qui stoque le theme d'affichage
    * @param _son qui stoque la musique de fond
    * @see Sons
    */
	Partie(String[] _themes,int size, int _nbCase, int _pointToWin, int _NbSameColorToWin, String _theme, Sons _son)
	{
        themes=_themes;
        pointToWin=_pointToWin;
        NbSameColorToWin=_NbSameColorToWin;
        nbCase=_nbCase;
        theme=_theme;
        son=_son;
        nbTour=0;
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(nbCase,nbCase, size, theme);
        fenetre = new GameWindow("Jeu de pente", this, size);
        eventMouseX=0;
        eventMouseY=0;
        finPartie=false;
        bruit1 = new Sons();
        bruit1.setPath(getPathByTheme(theme, 2));
        bruit1.setLoop(false);
        bruit2 = new Sons();
        bruit2.setPath(getPathByTheme(theme, 1));
        bruit2.setLoop(false);
        current_Joueur=joueur2.getNom();
        plate.getCaseAt(fenetre.getSizeFenetre()/2, fenetre.getSizeFenetre()/2).setPath(Plateau.getPathBy(theme, "clair"));
    }

    /**
    * Getter sur la musique de fond
	* @return la musique de fond
    */
    public Sons getSons()
    {
        return son;
    }

    /**
    * Getter sur le theme
	* @return le theme
    */
    public String getTheme()
    {
        return theme;
    }

    /**
    * Getter sur le chemin d'un son en fonction du theme et d'un index
    * @param Theme String qui stoque le theme
    * @param index String qui stoque l'index
	* @return le chemin d'un son
    */
    public static String getPathByTheme(String Theme, int index)
    {
        String rep="";
        switch(Theme)
        {
            case "normal":
                if(index==0)
                    rep="../son/partie1.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "sombre":
                if(index==0)
                    rep="../son/partie1.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "clair":
                if(index==0)
                    rep="../son/partie1.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "halloween":
                if(index==0)
                    rep="../son/halloween.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "noel":
                if(index==0)
                    rep="../son/noel.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "zelda":
                if(index==0)
                    rep="../son/link.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "harry potter":
                if(index==0)
                    rep="../son/harry.wav";
                else if(index==1)
                    rep="../son/bruitage1.wav";
                else if(index==2)
                    rep="../son/bruitage2.wav";
                break;
            case "os":
                if(index==0)
                    rep="../son/os.wav";
                else if(index==1)
                    rep="../son/bruitage2-os.wav";
                else if(index==2)
                    rep="../son/bruitage1-os.wav";
                break;
            default:
                break;
        }
        return rep;
    }

    /**
    * Getter sur la liste des themes
	* @return la liste des themes
    */
    public String[] getThemes()
    {
        return themes;
    }

    /**
    * Getter sur un theme de la liste des themes à un X index
    * @param index int qui stoque l'index vers un X theme
	* @return le theme à l'index X
    */
    public String getThemesAt(int index)
    {
        return themes[index];
    }

    /**
    * Setter sur le theme
	* @param _theme String qui stoque le nouveau theme à mettre à jour
    */
    public void setTheme(String _theme)
    {
        theme=_theme;
        plate.setThemePlate(theme);
        for(Case c : plate.getCases())
        {
            c.setPath(Plateau.getPathBy(theme, plate.getStatuBy(c.getPath())));
        }
        fenetre.repaint();
    }

    /**
    * Getter sur le joueur à un index X
    * @param i int qui stoque l'index X d'u joueur
	* @return le joueur X
    */
    public Joueur getJoueur(int i)
    {
        if(i==1)
            return joueur1;
        else
            return joueur2;
    }

    /**
    * Getter sur la fenetre de jeu
    * @return la fenetre de jeu
    * @see GameWindow
    */
    public GameWindow getFenetre()
    {
        return fenetre;
    }

    /**
    * Getter sur le plateau
    * @return le plateau
    * @see Plateau
    */
    public Plateau getPlateau()
    {
        return plate;
    }

    /**
    * Getter sur le nom du joueur entrain de jouer
    * @return le nom d'un joueur
    */
    public String getCurrentJoueur()
    {
        return current_Joueur;
    }

    /**
    * Setter sur le nom du joueur entrain de jouer
    * @param c String qui stoque le nom d'un joueur
    */
    public void setCurrentJoueur(String c)
    {
        current_Joueur=c;
    }

    /**
    * Getter sur le nombre de point pour gagner
    * @return le nombre de point pour gagner
    */
    public int getNbPointToWin()
    {
        return pointToWin;
    }

    /**
    * Getter sur un boolean indiquant si la partie est terminer
    * @return si la partie est terminer
    */
    public boolean isFinish()
    {
        return finPartie;
    }

    /**
    * Getter sur le nombre de tours effectué
    * @return le nombre de tours effectué
    */
    public int getNbTour()
    {
        return nbTour;
    }

    /**
    * Fonction qui gère un tour de jeu
    * @param j Joueur qui stoque le joueur qui doit jouer
    */
    private void tourdejeu(Joueur j)
    {
        nbTour++;
        current_Joueur=j.getNom();
        if(j==joueur1 && !(eventMouseX==0 && eventMouseY==0))
        {
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(Plateau.getPathBy(theme, "sombre"));
            fenetre._repaint();
            algo();
            fenetre._repaint();
        }
        else if(j==joueur2 && !(eventMouseX==0 && eventMouseY==0))
        {
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(Plateau.getPathBy(theme, "clair"));
            fenetre._repaint();
            algo();
            fenetre._repaint();
        }
        if(isWin()==joueur1 || isWin()==joueur2)
        {
            fenetre.getZoneDessin().afficheEstGagne();
            finPartie=true;
        }
    }

    /**
    * Foncion indiquant si un joueur à gagner
    * @return le joueur qui à gagner ou null si aucun n'a gagné
    */
    private Joueur isWin()
    {
        if(joueur1.getNbPoint()>=pointToWin)
            return joueur1;
        else if(joueur2.getNbPoint()>=pointToWin)
            return joueur2;
        else
            return null;
    }

    /**
    * Fonction qui lance l'algo de calcul sur tout les vecteurs autour d'un pions
    */
    private void algo()
    {
        capture=false;
		for(int i=-1;i<=1;i++)
		{
			for(int j=-1;j<=1;j++)
			{
                if(!(i==0 && j==0))
                    vecteur(i,j);
			}
        }
        Thread th;
        if(capture)
            th = new Thread(bruit2);
        else
            th = new Thread(bruit1);
        th.start();
    }
    
    /**
    * Fonction qui lance les calculs sur les vecteurs
    * @param i int qui stoque la coordonée X du vecteur
    * @param j int qui stoque la coordonée Y du vecteur
    */
    private void vecteur(int i, int j)
    {
		String ColorAdversaire;
		String Color=plate.getCaseAt(eventMouseX, eventMouseY).getPath();
		if(Color==Plateau.getPathBy(theme, "sombre"))
			ColorAdversaire=Plateau.getPathBy(theme, "clair");
		else
            ColorAdversaire=Plateau.getPathBy(theme, "sombre");
        
        if(eventMouseY+(3*j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))<0 || eventMouseX+(3*i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX()))<0 
        || eventMouseX+(3*i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(3*j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))>fenetre.getSizeFenetre())
        {}
        else 
        {
            if(plate.getCaseAt(eventMouseX+(i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX())), eventMouseY+(j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(2*i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX())), eventMouseY+(2*j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(3*i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX())), eventMouseY+(3*j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))).getPath()==Color)
            {
                plate.getCaseAt(eventMouseX+(i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX())), eventMouseY+(j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                plate.getCaseAt(eventMouseX+(2*i*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseX())), eventMouseY+(2*j*Math.round((float)fenetre.getSizeFenetre()/(float)plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                if(current_Joueur==joueur1.getNom())
                    joueur1.setNbPoint(joueur1.getNbPoint()+2);
                else if(current_Joueur==joueur2.getNom())
                    joueur2.setNbPoint(joueur2.getNbPoint()+2);
                capture=true;
            }
        }
        boolean suite=false;
        int nbPions=0;
        for(int d=2+(-1*(NbSameColorToWin));d<NbSameColorToWin;d++)
        {
            if(eventMouseY+(d*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))<0 || eventMouseX+(d*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))<0 
            || eventMouseX+(d*i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(d*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))>fenetre.getSizeFenetre())
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
                    capture=true;
                }
            }
        }
    }

    /**
    * Fonction qui sauvegarde le score d'un joueur
    * @param j Joueur qui stoque le joueur qui à gagné
    * @see Joueur
    */
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
            br.close();
            Path p= Paths.get(file.getPath());
            Files.delete(p);
            fileTemp.renameTo(file);
        }
        catch(IOException ioe)
        {
            System.err.println("IOException:" + ioe.getMessage());
        }
        
    }

    /**
    * Fonction qui se lance au clique d'une souris(lancé par la classe Evenement)
    * @see Evenement
    * @param x int qui stoque la coordonée X de la position de la souris
    * @param y int qui stoque la coordonée Y de la position de la souris
    */
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
        else if(nbTour==1 && plate.getCaseAt(eventMouseX, eventMouseY).getPath().equalsIgnoreCase(Plateau.getPathBy(theme, "vide")))
        {
            if((eventMouseX>=fenetre.getSizeFenetre()/2+((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2)
            || (eventMouseX<=fenetre.getSizeFenetre()/2-((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2)
            || (eventMouseY>=fenetre.getSizeFenetre()/2+((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2)
            || (eventMouseY<=fenetre.getSizeFenetre()/2-((fenetre.getSizeFenetre()/plate.getNbCaseX())*7)/2))
                tourdejeu(joueur2);

        }
        else if(current_Joueur.equalsIgnoreCase(joueur2.getNom())
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath().equalsIgnoreCase(Plateau.getPathBy(theme, "vide")))
        {
            tourdejeu(joueur1);
        }
        else if (current_Joueur.equalsIgnoreCase(joueur1.getNom())
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath().equalsIgnoreCase(Plateau.getPathBy(theme, "vide")))
        {
            tourdejeu(joueur2);
        }
    }

}
