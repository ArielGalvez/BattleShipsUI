package vistaYcontrolador;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


public class ImagenBote extends JPanel{
	
	Image imagenBarco;
	private int posX,posY;
	
	public ImagenBote(int pX, int pY){
		posX = pX;
		posY = pY;
		crearBote();
	}

	public void crearBote(){
		imagenBarco = Toolkit.getDefaultToolkit().getImage("accesorios/barco.gif");
	}
	
	public void paint(Graphics g,JPanel j){
		g.drawImage(imagenBarco,posX,posY,100,50,j);
							  //posx posy tamx tamy
	}
	
	
}
