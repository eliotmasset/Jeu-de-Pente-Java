import java.util.*;
import javax.swing.*;
import java.lang.Math;


/**
 * Classe d'un joueur
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class Joueur
{
    /**
	* nom du joueur
    */
    private String nom;
    /**
	* couleur du joueur
    */
    private String couleur;
    /**
	* nombre de point du joueur
    */
    private int nbPoint;

    /**
    * Constructeur d'un joueur
	* @param _nom qui stoque le nom du joueur
	* @param _couleur qui stoque la couleur du joueur
    */
    Joueur(String _nom, String _couleur)
	{
        couleur = _couleur;
        nom = _nom;
        nbPoint=0;
    }

    /**
    * Getter sur le nom du joueur
	* @return le nom du joueur
    */
    public String getNom()
    {
        return nom;
    }

    /**
    * Getter sur la couleur du joueur
	* @return la couleur du joueur
    */
    public String getCouleur()
    {
        return couleur;
    }

    /**
    * Setter sur le nom du joueur
	* @param n String qui stoque le nom du joueur
    */
    public void setNom(String n)
    {
        nom=n;
    }

    /**
    * Setter sur la couleur du joueur
	* @param c String qui stoque la couleur du joueur
    */
    public void setCouleur(String c)
    {
        couleur=c;
    }

    /**
    * Getter sur le nombre de points du joueur
	* @return le nombre de points du joueur
    */
    public int getNbPoint()
    {
        return nbPoint;
    }

    /**
    * Setter sur le nombre de points du joueur
	* @param p String qui stoque le nombre de points du joueur
    */
    public void setNbPoint(int p)
    {
        nbPoint=p;
    }

}
