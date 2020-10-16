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
    Window fenetre;
    boolean finPartie;

	Partie()
	{
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(19,19);
        estTerminee=false;
        eventMouseX=0;
        eventMouseY=0;
        finPartie=false;
    }

    public void lancePartie(Window _fenetre)
    {
        fenetre=_fenetre;
        current_Joueur="joueur 1";
    }
    public Plateau getPlateau()
    {
        return plate;
    }

    public void tourdejeu(Joueur j)
    {
        current_Joueur=j.getNom();
        System.out.println(j.getNom());
        if(j.getCouleur()=="noir" && !(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath("../img/case_noir.jpg");
        else if(!(eventMouseX==0 && eventMouseY==0))
            plate.getCaseAt(eventMouseX, eventMouseY).setPath("../img/case_blanc.jpg");
        algo();
        fenetre._repaint();
        if(isWin()==joueur1 || isWin()==joueur2)
            finPartie=true;
    }

    private Joueur isWin()
    {
        if(joueur1.getNbPoint()>=6)
            return joueur1;
        else if(joueur2.getNbPoint()>=6)
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
            if(eventMouseX+(i*(800/plate.getNbCaseX()))<25 || eventMouseX+(i*(800/plate.getNbCaseX()))>825 || eventMouseY+(j*(800/plate.getNbCaseY()))<25 || eventMouseY+(j*(800/plate.getNbCaseY()))>825)
            {
                end=true;
            }
			else if(plate.getCaseAt(eventMouseX+(i*(800/plate.getNbCaseX())), eventMouseY+(j*(800/plate.getNbCaseY()))).getPath()==ColorAdversaire)
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
			else if (plate.getCaseAt(eventMouseX+(i*(800/plate.getNbCaseX())), eventMouseY+(j*(800/plate.getNbCaseY()))).getPath()==Color)
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
					    plate.getCaseAt(eventMouseX+(i*(800/plate.getNbCaseX())), eventMouseY+(j*(800/plate.getNbCaseY()))).setPath("../img/case_vide.jpg");
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
                        finPartie=true;
                        end=true;
                    }
                }
            }
			else if (plate.getCaseAt(eventMouseX+(i*(800/plate.getNbCaseX())), eventMouseY+(j*(800/plate.getNbCaseY()))).getPath()=="../img/case_vide.jpg")
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
