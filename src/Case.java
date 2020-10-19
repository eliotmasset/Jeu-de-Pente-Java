import java.util.*;
import javax.swing.*;
import java.lang.Math;

class Case
{
    int coord_X, coord_Y;
    String path;

    Case(int _coord_X, int _coord_Y)
    {
        coord_X = _coord_X;
        coord_Y = _coord_Y;
        path="../img/case_vide.jpg";
    }

    public int getX()
    {
        return coord_X;
    }

    public int getY()
    {
        return coord_Y;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String _path)
    {
        path=_path;
    }

}
