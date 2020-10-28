import java.util.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Classe d'une case de plateau
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class Case
{
    /**
    * cordonnées x et y
    */
    private int coord_X, coord_Y;
    /**
    * chemin vers l'image de la case
    */
    private String path;

    /**
    * Constructeur de la classe Case
	* @param _coord_X int qui stoque la coordonnée X
	* @param _coord_Y int qui stoque la coordonnée Y
	* @param _path String qui stoque le chemin vers l'image de la case
    */
    Case(int _coord_X, int _coord_Y, String _path)
    {
        coord_X = _coord_X;
        coord_Y = _coord_Y;
        path=_path;
    }

    /**
    * getter sur la coordonnée X
	* @return la coordonnée X de la case
    */
    public int getX()
    {
        return coord_X;
    }

    /**
    * getter sur la coordonnée Y
	* @return la coordonnée Y de la case
    */
    public int getY()
    {
        return coord_Y;
    }

    /**
    * getter sur le chemin de l'image
	* @return le chemin vers l'image de la case
    */
    public String getPath()
    {
        return path;
    }

    /**
    * setter sur le chemin de l'image
	* @param _path String qui stoque le chemin de l'image de la case
    */
    public void setPath(String _path)
    {
        path=_path;
    }

}
