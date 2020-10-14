import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Plateau
{
    int nbCaseX, nbCaseY;
    private Vector<Case> cases;

    Plateau(int _nbCaseX, int _nbCaseY)
	{
        nbCaseX = _nbCaseX;
        nbCaseY = _nbCaseY;
        cases = new Vector<Case>();
        for(int x =0;x<nbCaseX;x++)
        {
            for(int y =0;y<nbCaseY;y++)
            {
                cases.addElement(new Case(x,y));
            }
        }
    }

    public int getNbCaseX()
    {
        return nbCaseX;
    }

    public int getNbCaseY()
    {
        return nbCaseY;
    }

}