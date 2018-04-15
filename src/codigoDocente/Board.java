package codigoDocente;
import java.util.ArrayList;

/**
 *where the ships live
 * 
 * @author VCJ 
 * @version 20120618
 */
public class Board
{
    private static final int LOW_LIMIT = 0; //limite inferior
    
    private int xTop;
    private int yTop;

    private ArrayList<Ship> ships;
    
    private int cantidadShips = 0;
    
    /**
     * Constructor for objects of class Board
     */
    public Board(int x, int y)	// dimensiones del tablero
    {
        ships = new ArrayList<Ship>();
        
        if (x > LOW_LIMIT && y > LOW_LIMIT)
        {
            xTop = x;
            yTop = y;
        } else {
            xTop = 10;
            yTop = 10;
        }
    }

    
    /*comprobar colisiones*/
    public boolean checkCollisions(Ship ship)
    {
        boolean collision = false;
                
        for (Ship s: ships)
                collision = collision 
                          || (s.collisionWith(ship));
        
               
        return collision;
    }
    /*comprobar si esta fuera de los limites*/
    public boolean checkOutOfBounds(Ship ship)
    {
        boolean out = false;
        ArrayList<Position> poss = ship.getPositions();
        
        for (Position p: poss)
                out = out 
                          || ( (p.getX()<LOW_LIMIT || p.getX()>=xTop)   //esto modifique con un = 
                          ||   (p.getY()<LOW_LIMIT || p.getY()>=yTop)); //lo mismo
        
               
        return out;
    }
    
    /**
     * Add ship
     * 
     * 
     * agregar barco
     * recibe un barco, verifica q no choque con otro barco y
     * q no este fuera de los limites*/
    public boolean add(Ship ship)
    {
        boolean ok = false;
        
        if (!checkCollisions(ship) && !checkOutOfBounds(ship))
        {
            ships.add(ship);
            ok =true;
            cantidadShips = cantidadShips+1;
        } else {}
        
        return ok;
    }
    /**disparar
     * recibe una pocicion y luego dispara*/
    public boolean shoot(Position pos)
    {
    boolean targeted = false;
        
        for (Ship ship: ships)
        {
           
            targeted = targeted || ship.shoot(pos); 
        }
        
        return targeted;
    }
    
    public String toString()
    {
        String strShips = "";
        
        for (Ship s: ships)
            strShips = strShips + s;
            
        return "Board \n ( " + xTop + ", " + yTop + " )" + "\n" + strShips;
    }
    /*extra*/
    public ArrayList<Ship> getShips(){
    	return ships;
    }
    public int getCantidadShips(){
    	return cantidadShips;
    }
    public void setCantidadShips(int cantidadActual){
    	cantidadShips = cantidadActual;
    }

}
