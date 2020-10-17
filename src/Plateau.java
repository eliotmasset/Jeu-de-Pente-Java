import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Plateau
{
    int nbCaseX, nbCaseY;
    private Vector<Case> cases;
    int size;

    Plateau(int _nbCaseX, int _nbCaseY, int _size)
	{
        size=_size;
        nbCaseX = _nbCaseX;
        nbCaseY = _nbCaseY;
        cases = new Vector<Case>();
        for(int y =0;y<nbCaseY;y++)
        {
            for(int x =0;x<nbCaseX;x++)
            {
                cases.addElement(new Case((size/nbCaseX)*x,(size/nbCaseY)*y));
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

    public Vector<Case> getCases()
    {
        return cases;
    }
    
    public Case getCaseAt(int x, int y)
    {
		return cases.elementAt(((x*getNbCaseX())/size)+(((y-25)*getNbCaseY())/size)*getNbCaseY());
	}

}
