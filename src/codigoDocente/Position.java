package codigoDocente;

/**
 * 2D Position
 * 
 * @author VCJ 
 * @version 20120618
 */
public class Position
{
    private int x;
    private int y;

    /**
     * Constructor for objects of class Postion
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
        
    }
    

    public int getY()
    {
        return y;
        
    }

    /**
     * equals
     * 
     */
    public boolean equals(Position pos)
    {
        return (pos.getX()==x && pos.getY()==y);
    }
    /*extra*/
    public void changeValues(int x, int y){
    	this.x = x;
    	this.y = y;
    }
    
    public String toString()
    {
        return "( " + x + ", " + y + " )";
    }
}
