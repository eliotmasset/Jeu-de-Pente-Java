import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Partie
{
    Plateau plate;
    private int eventMouseX,eventMouseY;
    final int decalMenu=25;
    Joueur joueur1, joueur2;
    String current_Joueur;
    GameWindow fenetre;
    boolean finPartie;
    int pointToWin;
    int NbSameColorToWin;
    String theme;
    int nbCase;

	Partie(int size, int _nbCase, int _pointToWin, int _NbSameColorToWin)
	{
        pointToWin=_pointToWin;
        NbSameColorToWin=_NbSameColorToWin;
        nbCase=_nbCase;
        theme="sombre";
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(nbCase,nbCase, size, theme);
        fenetre = new GameWindow("Jeu de pente", this, size);
        eventMouseX=0;
        eventMouseY=0;
        finPartie=false;
        current_Joueur="joueur 2";
    }

    Partie(int size, int _nbCase, int _pointToWin)
    {
        this(size, _nbCase, _pointToWin, 5);
    }
    Partie(int size, int _nbCase)
    {
        this(size, _nbCase, 10);
    }
    Partie(int size)
    {
        this(size, 19);
    }

    public String getTheme()
    {
        return theme;
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

    public int getNbPointToWin()
    {
        return pointToWin;
    }

    public boolean isFinish()
    {
        return finPartie;
    }

    private void tourdejeu(Joueur j)
    {
        current_Joueur=j.getNom();
        fenetre.getZoneDessin().setJoueur(j);
        System.out.println(j.getNom() + " => " + ((((eventMouseX)*plate.getNbCaseX())/fenetre.getSizeFenetre())+(((eventMouseY-decalMenu)*plate.getNbCaseY())/fenetre.getSizeFenetre())*plate.getNbCaseY()));
        if(j.getCouleur()=="noir" && !(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(plate.getPathBy(theme, "noir"));
        else if(!(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath(plate.getPathBy(theme, "blanc"));
        algo();
        fenetre._repaint();
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
		if(Color==plate.getPathBy(theme, "noir"))
			ColorAdversaire=plate.getPathBy(theme, "blanc");
		else
            ColorAdversaire=plate.getPathBy(theme, "noir");
        
        if(eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))<decalMenu || eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))<0 
        || eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))>fenetre.getSizeFenetre()+decalMenu)
        {}
        else 
        {
            if(plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(2*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(2*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==ColorAdversaire
                && plate.getCaseAt(eventMouseX+(3*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(3*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==Color)
            {
                plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                plate.getCaseAt(eventMouseX+(2*i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(2*j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).setPath(plate.getPathBy(theme, "vide"));
                if(current_Joueur=="joueur 1")
                    joueur1.setNbPoint(joueur1.getNbPoint()+2);
                else
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

    public void clicEvent(int x,int y)
    {
        this.eventMouseX=x;
        this.eventMouseY=y;
        if(finPartie)
        {
            fenetre.dispose();
        }
        else if(current_Joueur=="joueur 1"
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath()==plate.getPathBy(theme, "vide"))
        {
            tourdejeu(joueur2);
        }
        else if (plate.getCaseAt(eventMouseX, eventMouseY).getPath()==plate.getPathBy(theme, "vide"))
        {
            tourdejeu(joueur1);
        }
    }

}
