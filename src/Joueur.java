import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Joueur
{
    private String nom;
    private String couleur;
    private int nbPoint;

    Joueur(String _nom, String _couleur)
	{
        couleur = _couleur;
        nom = _nom;
        nbPoint=0;
    }

    public String getNom()
    {
        return nom;
    }

    public String getCouleur()
    {
        return couleur;
    }

    public void setNom(String n)
    {
        nom=n;
    }

    public void setCouleur(String c)
    {
        couleur=c;
    }

    public int getNbPoint()
    {
        return nbPoint;
    }

    public void setNbPoint(int p)
    {
        nbPoint=p;
    }

}
