import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Plateau
{
    int nbCaseX, nbCaseY;
    private Vector<Case> cases;
    int size;
    String themePlate;

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
                cases.addElement(new Case((size/nbCaseX)*x,(size/nbCaseY)*y,getPathBy(themePlate, "vide")));
            }
        }
    }

    public void setThemePlate(String _themePlate)
    {
        themePlate=_themePlate;
    }

    public int getNbCaseX()
    {
        return nbCaseX;
    }

    public int getNbCaseY()
    {
        return nbCaseY;
    }

    public Vector<Case> getCases()
    {
        return cases;
    }
    
    public Case getCaseAt(int x, int y)
    {
		return cases.elementAt(((x*getNbCaseX())/size)+(((y-25)*getNbCaseY())/size)*getNbCaseY());
    }
    
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
                    default:
                }
                break;
            default:
                ret="../img/case_vide.jpg";
                break;
        }
        return ret;
    }

    public static String getStatuBy(String path)
    {
        String ret="";
        if(path=="../img/theme_clair_case_vide.jpg" || path=="../img/theme_sombre_case_vide.jpg" || path=="../img/case_vide.jpg")
            ret="vide";
        else if(path=="../img/theme_clair_case_clair.jpg" || path=="../img/theme_sombre_case_clair.jpg" || path=="../img/case_blanc.jpg")
            ret="clair";
        else
            ret="sombre";
        return ret;
    }

}
