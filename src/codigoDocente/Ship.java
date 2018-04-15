package codigoDocente;


import java.util.ArrayList;

/**
 * it is a sea ship class
 * 
 * @author VCJ 
 * @version 20120618
 */

public class Ship
{
    private ArrayList<ShipPart>  parts;
    private String               shipName;
    private boolean              isSunk;

    /**
     * Constructor for objects of class Ship
     */
	/*constructor de la clase barco*/
    public Ship(String name,Position pos)
    {
        shipName = name;
        isSunk   = false;
        parts    = new ArrayList<ShipPart>();
        parts.add(new ShipPart(pos));        // basic ship probably a canoe
                                            // is the middle part of ship
    }

    /**
     * check if ship is sunk
     * 
     * comprobar si esta hundido
     * 
     * pregunta a todas las piezas y da verdadero o falso
     * si el barco se hunde
     */
    public void checkSunk()
    {
        boolean sunk = true;
        
        for (ShipPart part: parts)
            sunk = sunk && part.isDamaged();
        
        isSunk = sunk;
    }
    
    /**
     * is the ship able to fight? not sunk?
     * 
     * se hundio?
     * 
     * devuelve verdad o falase si el barco se hundio
     */
    public boolean isSunk()
    {
        if (!isSunk) 
            checkSunk();        // checkSunk change isSunk value
        return isSunk;
    }    
    /**
     * es parte de la nave?
     */
    public boolean isPartOfThisShip(ShipPart part)
    {
        int i         = 0;
        boolean found = false;
        
        while (!found && i<parts.size())
        {
            found = part == parts.get(i);
            i++;
        }
        
        return found;
    }
    /***
     * constructor de la clase barco
     */
    public boolean builder(ShipPart part, ShipPart reference, int position)
    {
        boolean isOk = false;
        
        if (isPartOfThisShip(reference)) {
            isOk = reference.add(part,position);
            parts.add(part);
        } else {}
        return isOk;
    }
    /**
     * conseguir pieza de referencia
     */
    public ShipPart getReferencePart(int p)
    {
        ShipPart part = null;
        
        if (p>=0 && p<parts.size())
            part = parts.get(p);
        else {}
        
        return part;
    }
    
    /**
     * obtener parte media
     */
    public ShipPart getMiddlePart()
    {
        return getReferencePart(0);
    }
    /**
     * disparar
     * dispara dado una pocision y devuelve si verdad o falso
     * si le llega a un barco
     */
    public boolean shoot(Position pos)
    {
    boolean targeted = false;
        
        for (ShipPart part: parts)
        {
           if(part.shoot(pos))								//part.shoot(pos);		
        	   targeted = targeted || part.isDamaged();	//targeted = targeted || part.isDamaged();
        }
        
        return targeted;
    }
    /**
     * devuelve las pociciones de un barco en un array
     */
    public ArrayList<Position> getPositions()
    {
        ArrayList<Position> poss = new ArrayList<Position>();
        
        for (ShipPart part: parts)
        {
           poss.add(part.getPosition());
        }
        
        return poss;
    }
    /**
     * colisiona con?
     * agarra las pociciones de un barco y las compara con este barco
     */
    public boolean collisionWith(Ship ship)
    {
        ArrayList<Position> poss = ship.getPositions();
        ArrayList<Position> myPoss = getPositions();
        
        boolean collision = false;
        
        for (Position p: poss)
            for (Position q: myPoss)
                collision = collision || (p.equals(q));
        
        return collision;
    }
    
    public String toString()
    {
        String strParts = "";
        
        for (ShipPart p: parts)
            strParts = strParts + p;
        return "Ship: " + shipName + " " + (isSunk?"Hundido":"A flote") + "\n"
              + strParts + "\n\n" ;
    }

}
