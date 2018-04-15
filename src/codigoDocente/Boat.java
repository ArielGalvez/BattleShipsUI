package codigoDocente;



/**
 * Write a description of class Boat here.
 * 
 * @author VCJ 
 * @version 20120618
 */
public class Boat extends Ship
{
    

    /**
     * Constructor for objects of class Boat
     */

    public Boat(String name, Position pos)
    {
        super(name,pos);

        ShipPart part;
        ShipPart reference;
        
        reference = getReferencePart(0);
        part      = new ShipPart(pos);
        builder (part,reference,ShipPart.LEFT_PART);        
    }

}
