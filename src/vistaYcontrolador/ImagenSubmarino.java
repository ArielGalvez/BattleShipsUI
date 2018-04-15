package vistaYcontrolador;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


public class ImagenSubmarino extends JPanel{

	Image imagenSubmarino;
	private int posX,posY;

	public ImagenSubmarino(int pX, int pY){
		posX = pX;
		posY = pY;
		crearSubmarino();
	}

	public void crearSubmarino(){
		imagenSubmarino = Toolkit.getDefaultToolkit().getImage("accesorios/submarino.gif");
	}
	
	public void paint(Graphics g, JPanel j){
		g.drawImage(imagenSubmarino,posX,posY,150,130,j);
	}
}
