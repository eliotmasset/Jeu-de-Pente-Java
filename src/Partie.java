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

	Partie()
	{
        joueur1 = new Joueur("joueur 1", "noir");
        joueur2 = new Joueur("joueur 2", "blanc");
        plate = new Plateau(10,10);
        estTerminee=false;
        eventMouseX=0;
        eventMouseY=0;
    }

    public void lancePartie(Window _fenetre)
    {
        fenetre=_fenetre;
        current_Joueur="joueur 1";
        tourdejeu(joueur1);
        while(!estTerminee)
        {
        }
    }
    public Plateau getPlateau()
    {
        return plate;
    }

    public void tourdejeu(Joueur j)
    {
        current_Joueur=j.getNom();
        if(j.getCouleur()=="noir" && !(eventMouseX==0 && eventMouseY==0))
            plate.getCases().elementAt((((eventMouseX-25)*plate.getNbCaseX())/800)+(((eventMouseY-50)*plate.getNbCaseY())/800)*plate.getNbCaseY()).setPath("../img/case_noir.jpg");
        else if(!(eventMouseX==0 && eventMouseY==0))
            plate.getCases().elementAt((((eventMouseX-25)*plate.getNbCaseX())/800)+(((eventMouseY-50)*plate.getNbCaseY())/800)*plate.getNbCaseY()).setPath("../img/case_blanc.jpg");
        algo();
        fenetre._repaint();
    }

    private void algo()
    {

    }

    public void clicEvent(int x,int y)
    {
        this.eventMouseX=x;
        this.eventMouseY=y;
        if(current_Joueur=="joueur 1")
        {
            tourdejeu(joueur2);
        }
        else
        {
            tourdejeu(joueur1);
        }
    }

}
