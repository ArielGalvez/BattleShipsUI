package codigoDocente;




/**
 * Write a description of class Submarine here.
 * 
 * @author VCJ 
 * @version 20120618
 */
public class Submarine extends Ship
{


    /**
     * Constructor for objects of class Submarine
     */
    public Submarine(String name, Position pos)
    {
        super(name,pos);

        ShipPart part;
        ShipPart reference;
        
        reference = getReferencePart(0);
        part      = new ShipPart(pos);
        builder (part,reference,ShipPart.LEFT_PART);  
        part      = new ShipPart(pos);
        builder (part,reference,ShipPart.RIGHT_PART);  
        part      = new ShipPart(pos);
        builder (part,reference,ShipPart.UP_PART);  //part,reference,ShipPart.UP_PART
    }

}
