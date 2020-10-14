import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Joueur
{
    String nom;
    String couleur;

    Joueur(String _nom, String _couleur)
	{
        couleur = _couleur;
        nom = _nom;
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

}
