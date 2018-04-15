package vistaYcontrolador;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.ImageIcon;



import java.util.ArrayList;


public class Tablero extends JPanel implements MouseListener  {

	Image imagenMar;
	int tamX,tamY;
	MediaTracker tracker;
	BatallaNaval btNaval;
	int[][] tableroModelo;
	int cordX=1, cordY=1;
	private boolean ocultar=false;

	public Tablero(BatallaNaval vistaYControlador, int[][] tableroModelo){
		this.tableroModelo = tableroModelo;
		btNaval = vistaYControlador;
		addMouseListener(this);
		crearCasillas();
	}
	ArrayList<ImagenBote> imagenBote = new ArrayList<ImagenBote>();
	public void addBotes(int posX, int posY){
		imagenBote.add (new ImagenBote(posX, posY));
	}
	
	ArrayList<ImagenSubmarino> imagenSubmarino = new ArrayList<ImagenSubmarino>();
	public void addSubmarinos(int posX, int posY){
		imagenSubmarino.add(new ImagenSubmarino(posX, posY));
	}
	
	public void crearCasillas(){
		tracker = new MediaTracker(this);
	    imagenMar = Toolkit.getDefaultToolkit().getImage("accesorios/mar2.gif");
	  	tracker.addImage(imagenMar,0);
		
		try{
			tracker.waitForID(0);
		}
		catch(InterruptedException e){}
	}
	public void ocultar(){
		ocultar=true;
	}
	public void mostrar(){
		ocultar=false;
	}
	
	public void paint(Graphics g){
		
		tamX = getSize().width/10;
		tamY = getSize().height/10;
		//g.fillRect(0,0,getSize().width,getSize().height);// era para poner relleno rectangulo
		
		/*para el tablero*/
		g.drawImage(imagenMar,0,0,getSize().width,getSize().height,this);
		
		/*para los botes*/
		for(int i=0;i< imagenBote.size();i++){
	       imagenBote.get(i).paint(g,this);
	       }
		
		/*para los submarinos*/
		for(int i=0;i< imagenSubmarino.size();i++){
	       imagenSubmarino.get(i).paint(g,this);
	       }
		pintarTablero(g, tableroModelo);
		
		/*esto pinta la mira*/
		g.setColor(Color.RED);
		g.drawLine(cordX*50+25,cordY*50,cordX*50+25,cordY*50+50);
		g.drawLine(cordX*50,cordY*50+25,cordX*50+50,cordY*50+25);
		g.drawOval(cordX*50,cordY*50,50,50);
		
		/*para q no se vea el tablero de cada jugador*/
		if(ocultar)
		g.drawImage(imagenMar,0,0,getSize().width,getSize().height,this); 
		
		/*para hacer las lineas si o si tiene q estar al final*/
		for(int i = 0; i<10; i++)
	    	for(int j = 0; j<10; j++){
	    		g.setColor(new Color(212,189,18));//120,100,200  color de las rayitas
	    		g.drawRect(j*tamX,i*tamY,tamX,tamY);	
	    	}
	

	}

	public void pintarTablero(Graphics g, int tab[][]){

		for(int n=0; n<10; n++){
			for(int m=0; m<10; m++){
				if(tab[n][m]==1){
					g.setColor(new Color(0,255,0,60));//color verde
					g.fillRoundRect(n*50,m*50,50,50,0,0);//(n*20, m*20, 20, 20);
				}
				else if(tab[n][m]==2){
					g.setColor(new Color(255,255,0,80));//color amarillo
					g.fillRoundRect(n*50,m*50,50,50,0,0);
				}
				else if(tab[n][m]==3){
					g.setColor(new Color(255,0,0,80));//color rojo
					g.fillRoundRect(n*50,m*50,50,50,0,0);
				}
				else if(tab[n][m]==4){
					g.setColor(new Color(0,0,255,80));//color azul
					g.fillRoundRect(n*50,m*50,50,50,0,0);
				}
			}
			g.setColor(Color.black);
		}

	}

	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("accesorios/mira.png").getImage(),new Point(8,8),"aaa"));
	}

	public void mouseExited(MouseEvent e){
	}

	public void mousePressed(MouseEvent e){
		this.btNaval.getCajaY().setText(""+((e.getY()/50)+0));
		this.btNaval.getCajaX().setText(""+((e.getX()/50)+0));
		cordX=e.getX()/50;
		cordY=e.getY()/50;
		repaint();
	}

	public void mouseReleased(MouseEvent e){
	}
}
