import java.util.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Classe d'un plateau
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
class Plateau
{
    /**
    * nombre de case sur l'axe X et l'axe Y
    */
    private int nbCaseX, nbCaseY;
    /**
    * vecteurs stoquant des cases
    * @see Case
    */
    private Vector<Case> cases;
    /**
    * taille du plateau
    */
    private int size;
    /**
    * theme du plateau
    */
    private String themePlate;

    /**
    * Constructeur d'une partie
	* @param _nbCaseX qui stoque le nombre de case sur l'axe X
    * @param _nbCaseY qui stoque le nombre de case sur l'axe Y
    * @param _size qui stoque la taille du plateau
    * @param _themePlate qui stoque le thee du plateau
    */
    Plateau(int _nbCaseX, int _nbCaseY, int _size, String _themePlate)
	{
        size=_size;
        nbCaseX = _nbCaseX;
        nbCaseY = _nbCaseY;
        themePlate=_themePlate;
        cases = new Vector<Case>();
        for(int y =0;y<nbCaseY;y++)
        {
            for(int x =0;x<nbCaseX;x++)
            {
                cases.addElement(new Case((x*size)/nbCaseX,(y*size)/nbCaseY,getPathBy(themePlate, "vide")));
            }
        }
    }

    /**
    * Setter sur le theme du plateau
	* @param _themePlate String qui stoque le theme du plateau
    */
    public void setThemePlate(String _themePlate)
    {
        themePlate=_themePlate;
    }

    /**
    * Getter sur le nombre de case sur l'axe X
	* @return le nombre de case sur l'axe X
    */
    public int getNbCaseX()
    {
        return nbCaseX;
    }

    /**
    * Getter sur le nombre de case sur l'axe Y
	* @return le nombre de case sur l'axe Y
    */
    public int getNbCaseY()
    {
        return nbCaseY;
    }

    /**
    * Getter sur le vecteurs de cases
	* @return le vecteur de cases
    */
    public Vector<Case> getCases()
    {
        return cases;
    }
    
    /**
    * Getter sur la Case à l'index X
    * @param x coordonnée X de la souris
    * @param y coordonnée Y de la souris
    * @return une case
    * @see Case
    */
    public Case getCaseAt(int x, int y)
    {
		return cases.elementAt(((x*getNbCaseX())/size)+(((y)*getNbCaseY())/size)*getNbCaseY());
    }
    
    /**
    * Getter sur le chemin vers une image en fonction du theme et d'un index
    * @param theme String qui stoque le theme
    * @param statu String qui stoque le status
	* @return le chemin vers une image
    */
    public static String getPathBy(String theme, String statu)
    {
        String ret="";
        switch(theme)
        {
            case "normal":
                switch(statu)
                {
                    case "vide":
                        ret="../img/case_vide.jpg";
                        break;
                    case "sombre":
                        ret="../img/case_noir.jpg";
                        break;
                    case "clair":
                        ret="../img/case_blanc.jpg";
                        break;
                    case "pion_sombre":
                        ret="../img/pion_noir.jpg";
                        break;
                    case "pion_clair":
                        ret="../img/pion_blanc.jpg";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin.jpg";
                        break;
                    default:
                }
                break;
            case "sombre":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_sombre_case_vide.jpg";
                        break;
                    case "sombre":
                        ret="../img/theme_sombre_case_sombre.jpg";
                        break;
                    case "clair":
                        ret="../img/theme_sombre_case_clair.jpg";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_sombre_pion_sombre.jpg";
                        break;
                    case "pion_clair":
                        ret="../img/theme_sombre_pion_clair.jpg";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin.jpg";
                        break;
                    default:
                }
                break;
            case "clair":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_clair_case_vide.jpg";
                        break;
                    case "sombre":
                        ret="../img/theme_clair_case_sombre.jpg";
                        break;
                    case "clair":
                        ret="../img/theme_clair_case_clair.jpg";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_clair_pion_sombre.jpg";
                        break;
                    case "pion_clair":
                        ret="../img/theme_clair_pion_clair.jpg";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin.jpg";
                        break;
                    default:
                }
                break;
            case "halloween":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_halloween_case_vide.png";
                        break;
                    case "sombre":
                        ret="../img/theme_halloween_case_sombre.png";
                        break;
                    case "clair":
                        ret="../img/theme_halloween_case_clair.png";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_halloween_case_sombre.png";
                        break;
                    case "pion_clair":
                        ret="../img/theme_halloween_case_clair.png";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin_citrouille.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin_fantome.jpg";
                        break;
                    default:
                }
                break;
            case "noel":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_noel_case_vide.png";
                        break;
                    case "sombre":
                        ret="../img/theme_noel_case_sombre.png";
                        break;
                    case "clair":
                        ret="../img/theme_noel_case_clair.png";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_noel_case_sombre.png";
                        break;
                    case "pion_clair":
                        ret="../img/theme_noel_case_clair.png";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin_cadeau.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin_pere_noel.jpg";
                        break;
                    default:
                }
                break;
            case "zelda":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_zelda_case_vide.png";
                        break;
                    case "sombre":
                        ret="../img/theme_zelda_case_sombre.png";
                        break;
                    case "clair":
                        ret="../img/theme_zelda_case_clair.png";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_zelda_case_sombre.png";
                        break;
                    case "pion_clair":
                        ret="../img/theme_zelda_case_clair.png";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin_link_dead.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin_link.jpg";
                        break;
                    default:
                }
                break;
            case "harry potter":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_harry_potter_case_vide.png";
                        break;
                    case "sombre":
                        ret="../img/theme_harry_potter_case_sombre.png";
                        break;
                    case "clair":
                        ret="../img/theme_harry_potter_case_clair.png";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_harry_potter_case_sombre.png";
                        break;
                    case "pion_clair":
                        ret="../img/theme_harry_potter_case_clair.png";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin_serpentar.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin_pouffsoufle.jpg";
                        break;
                    default:
                }
                break;
            case "os":
                switch(statu)
                {
                    case "vide":
                        ret="../img/theme_os_case_vide.png";
                        break;
                    case "sombre":
                        ret="../img/theme_os_case_sombre.png";
                        break;
                    case "clair":
                        ret="../img/theme_os_case_clair.png";
                        break;
                    case "pion_sombre":
                        ret="../img/theme_os_case_sombre.png";
                        break;
                    case "pion_clair":
                        ret="../img/theme_os_case_clair.png";
                        break;
                    case "fin_sombre":
                        ret="../img/image_fin_linux.jpg";
                        break;
                    case "fin_clair":
                        ret="../img/image_fin_windows.jpg";
                        break;
                    default:
                }
                break;
            default:
                ret="../img/case_vide.jpg";
                break;
        }
        return ret;
    }

    /**
    * Getter sur le status d'une case en fonction de son chemin
    * @param path qui stoque le chemin vers une image de case
	* @return le status d'une case
    */
    public static String getStatuBy(String path)
    {
        String ret="";
        if(path==getPathBy("normal", "vide") || path==getPathBy("sombre", "vide") || path==getPathBy("clair", "vide") || path==getPathBy("halloween", "vide") || path==getPathBy("noel", "vide") || path==getPathBy("zelda", "vide") || path==getPathBy("harry potter", "vide") || path==getPathBy("os", "vide"))
            ret="vide";
        else if(path==getPathBy("normal", "clair") || path==getPathBy("sombre", "clair") || path==getPathBy("clair", "clair") || path==getPathBy("halloween", "clair") || path==getPathBy("noel", "clair") || path==getPathBy("zelda", "clair") || path==getPathBy("harry potter", "clair") || path==getPathBy("os", "clair"))
            ret="clair";
        else
            ret="sombre";
        return ret;
    }

}
