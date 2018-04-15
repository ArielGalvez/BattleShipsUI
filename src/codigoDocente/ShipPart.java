package codigoDocente;


/**
 * You can build a Ship with this part.
 * 
 * @author VCJ
 * @version 20120618
 */
public class ShipPart
{
    public static final int UP_PART    = 0; //arriba
    public static final int RIGHT_PART = 1;	//derecha
    public static final int DOWN_PART  = 2;	//abajo
    public static final int LEFT_PART  = 3;	//isquierda
    
    private static final boolean DAMAGED     = true; //dañado
    private static final boolean NOT_DAMAGED = false;	//no dañado
    
    /*numero de partes permitidas conectadas*/
    private static final int NUMBER_OF_CONNECTED_PARTS_ALLOWED = 4;

    private ShipPart[]  connectedParts;	//partes conectadas
    private boolean     isDamaged;	//esta dañado
    private Position    pos;

    /**
     * Constructor for objects of class ShipPart
     * contructor de la clase barteBuque
     */
    public ShipPart(Position pos)
    {
        init();
        this.pos = pos;
    }

    /**
     * Initialise part
     * inicilaizar
     */
    public void init()
    {
        isDamaged       = NOT_DAMAGED;
        connectedParts  = new ShipPart[NUMBER_OF_CONNECTED_PARTS_ALLOWED];
        
        // remember at this time there are no connected parts, 
        // all refer to NULL
    }
    
    /**
     * Add part
     * agregar
     */ 
    public boolean add(ShipPart part, int position)
    {
        boolean successfulConnected = false;
        
        if (position >= 0 && position <= LEFT_PART)
            if (connectedParts[position] == null){
                connectedParts[position] = part; // conectar la inversa 
                                                 // para la parte
                setPartCoordinates(part,position);
                successfulConnected      = true;
            } else {}
        else {}
        
        return successfulConnected;
    }
    
    public void setPartCoordinates(ShipPart part, int position)
    {
        int x = pos.getX() + (position == RIGHT_PART?1:position==LEFT_PART?-1:0);
        int y = pos.getY() + (position == DOWN_PART?1:position==UP_PART?-1:0);//esto position == UP_PART?1:position==DOWN_PART?-1:0
        
        part.setPosition(new Position(x,y));
    }
    
    /**
     * isDamaged?
     * esta dañado?
     */ 
    public boolean isDamaged()
    {
        return isDamaged;
    }

    
    /**
     * being damaged
     * disparar
     *     public void shoot(Position pos)
    		{
    			if (this.pos.equals(pos)) 
            		isDamaged = DAMAGED;
    		}
     * aqui mas modifique
     */ 
    public boolean shoot(Position pos)
    {
    	boolean res=false;
        if (this.pos.equals(pos)){
            isDamaged = DAMAGED;
            res=true;
        }
        return res;
    }

    /**
     * set Position
     * ajustar la posicion
     */ 
    public void setPosition(Position pos)
    {
        this.pos = pos;
    }
    
    /**
     * get Position
     * obtener la posicion
     */ 
    public Position getPosition()
    {
        return pos;
    }
    
    public String toString()
    {
        return "Part: " + pos + " ... Damaged: " + (isDamaged?"yes":"no") + "\n";
    }

}
