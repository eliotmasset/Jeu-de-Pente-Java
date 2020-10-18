import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Partie
{
    Plateau plate;
    private boolean estTerminee;
    private int eventMouseX,eventMouseY;
    Joueur joueur1, joueur2;
    String current_Joueur;
    GameWindow fenetre;
    boolean finPartie;
    int pointToWin;

	Partie(int size)
	{
        pointToWin=10;
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(19,19, size);
        fenetre = new GameWindow("Jeu de pente", this, size);
        estTerminee=false;
        eventMouseX=0;
        eventMouseY=0;
        finPartie=false;
        current_Joueur="joueur 2";
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

    public void tourdejeu(Joueur j)
    {
        current_Joueur=j.getNom();
        fenetre.getZoneDessin().setJoueur(j);
        System.out.println(j.getNom() + " => " + ((((eventMouseX)*plate.getNbCaseX())/fenetre.getSizeFenetre())+(((eventMouseY-25)*plate.getNbCaseY())/fenetre.getSizeFenetre())*plate.getNbCaseY()));
        if(j.getCouleur()=="noir" && !(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath("../img/case_noir.jpg");
        else if(!(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath("../img/case_blanc.jpg");
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

    public boolean isFinish()
    {
        return finPartie;
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
		int j_init=j;
		int i_init=i;
        boolean end=false;
        boolean estInverse=false;
		String ColorAdversaire;
		String Color=plate.getCaseAt(eventMouseX, eventMouseY).getPath();
        int compt=0;
        int comptSameColor=1;
		if(Color=="../img/case_noir.jpg")
			ColorAdversaire="../img/case_blanc.jpg";
		else
			ColorAdversaire="../img/case_noir.jpg";
		while(!end)
		{
            if(eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))<25 || eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))<0 
            || eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX()))>fenetre.getSizeFenetre()  || eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))>fenetre.getSizeFenetre()+25)
            {
                end=true;
            }
			else if(plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==ColorAdversaire)
			{
                if(comptSameColor==1)
                {
                    i+=i_init;
				    j+=j_init;
				    compt++;
                }
                else
                {
                    end=true;
                }
			}
			else if (plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()==Color)
			{
                if(compt >=2)
                {
                    for(int c=0;c<compt;c++)
				    {
					    i-=i_init;
                        j-=j_init;
                        if(current_Joueur=="joueur 1")
                            joueur1.setNbPoint(joueur1.getNbPoint()+1);
                        else
                            joueur2.setNbPoint(joueur2.getNbPoint()+1);
					    plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).setPath("../img/case_vide.jpg");
                    }
                    end=true;
                }
                if(comptSameColor!=0)
                {
                    i+=i_init;
                    j+=j_init;
                    comptSameColor++;
                    if(comptSameColor>=5)
                    {
                        fenetre.getZoneDessin().afficheEstGagne();
                        finPartie=true;
                        end=true;
                    }
                }
            }
			else if (plate.getCaseAt(eventMouseX+(i*(fenetre.getSizeFenetre()/plate.getNbCaseX())), eventMouseY+(j*(fenetre.getSizeFenetre()/plate.getNbCaseY()))).getPath()=="../img/case_vide.jpg")
			{
                end=true;
                if(comptSameColor>1 && !estInverse)
                {
                    estInverse=true;
                    j_init*=-1;
                    i_init*=-1;
                    i=i_init;
                    j=j_init;
                    end=false;
                }
            }
        }
	}

    public void clicEvent(int x,int y)
    {
        this.eventMouseX=x;
        this.eventMouseY=y;
        if(finPartie)
        {}
        else if(current_Joueur=="joueur 1" 
        && plate.getCaseAt(eventMouseX, eventMouseY).getPath()=="../img/case_vide.jpg")
        {
            tourdejeu(joueur2);
        }
        else if (plate.getCaseAt(eventMouseX, eventMouseY).getPath()=="../img/case_vide.jpg")
        {
            tourdejeu(joueur1);
        }
    }

}
