package vistaYcontrolador;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;




public class TableroVision extends JPanel implements MouseListener{
	int tamX,tamY;
	int posX, posY;
	BatallaNaval btNaval;
	int[][] tableroModelo;
	int cordX=1, cordY=1;
	public TableroVision(BatallaNaval vistaYControlador, int[][] tableroModelo){
		this.tableroModelo = tableroModelo;
		btNaval = vistaYControlador;
		addMouseListener(this);
	}
	
	public void paint(Graphics g){
		g.setColor(new Color(0,185,250));
	    g.fill3DRect(0,0,200,200,true);
	    
		pintarTablero(g, tableroModelo);
		
		g.setColor(Color.RED);
		g.drawOval(cordX*20,cordY*20,20,20);
		g.drawLine(cordX*20+10,cordY*20,cordX*20+10,cordY*20+20);
		g.drawLine(cordX*20,cordY*20+10,cordX*20+20,cordY*20+10);
		
		for(int n=0; n<10; n++){
			for(int m=0; m<10; m++){
					g.setColor(Color.black);
					g.drawRect(n*20, m*20, 20, 20);
			}
		}
	}
	
	public void pintarTablero(Graphics g, int tab[][]){

		for(int n=0; n<10; n++){
			for(int m=0; m<10; m++){
				if(tab[n][m]==4){	 //hay barco 1, al agua 4
					g.setColor(new Color(0,0,255));//Color.BLUE
					g.fillRoundRect(n*20,m*20,20,20,0,0);//(n*20, m*20, 20, 20);
				}
				else if(tab[n][m]==2){	//barco daniado
					g.setColor(Color.YELLOW);
					g.fillRoundRect(n*20,m*20,20,20,0,0);
				}
				else if(tab[n][m]==3){  //barco destruido
					g.setColor(Color.RED);
					g.fillRoundRect(n*20,m*20,20,20,0,0);
				}
			}
			g.setColor(Color.black);
		}

	}
	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseEntered(MouseEvent e){
	}

	public void mouseExited(MouseEvent e){
	}

	public void mousePressed(MouseEvent e){
		this.btNaval.getCajaY().setText(""+(e.getY()/20));
		this.btNaval.getCajaX().setText(""+(e.getX()/20));
		cordX=e.getX()/20;
		cordY=e.getY()/20;
		repaint();
	}

	public void mouseReleased(MouseEvent e){
	}
}
