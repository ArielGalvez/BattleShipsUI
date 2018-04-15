package modelo;

import java.util.Random;

import codigoDocente.Position;

public class Computadora {

	private int contador;
	private int[][] tabDisparosCompu = new int[10][10];;
	private Position ultimoDisparo = new Position(2,2);
	
	public Computadora(){
		for(int n=0; n<10; n++){
			for(int m=0; m<10; m++){
				tabDisparosCompu[n][m] = 0;
			}
		}
	}
	public boolean existe(Position p){
		boolean existe = false;
		int x = p.getX();
		int y = p.getY();
		if(tabDisparosCompu[x][y]==1)
			existe = true;
		return existe;
	}
	public Position nuevoDisparo(){
		Position pos;
		Random rdm = new Random();
		int x = rdm.nextInt(9)+1; //genera de 1 a 9
		int y = rdm.nextInt(10); //genera de 1 a 9
		pos = new Position(x,y);
		if(existe(pos))
			pos = nuevoDisparo();
		ultimoDisparo = pos;
		return pos;
	}
	public Position disparoIzq(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		pos = new Position(x-1, y);//izquieda
		return pos;
	}
	public Position disparoDer(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		pos = new Position(x+1, y);//derecha
		return pos;
	}
	public Position disparoArriba(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		pos = new Position(x, y-1);//arriba
		return pos;
	}
	public Position disparoAbajo(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		pos = new Position(x, y+1);//abajo
		return pos;
	}
	public Position dispararCompu(){
		Position p = ultimoDisparo;
		if(contador == 1)
			p = disparoIzq();
		else if(contador==2)
			p = disparoDer();
		else if(contador==3)
			p = disparoArriba();
		else if(contador==4)
			p = disparoAbajo();
		else{
			p = nuevoDisparo();
			contador = -1;
		}	
		int x = p.getX();
		int y = p.getY();
		tabDisparosCompu[x][y] = 1;
		ultimoDisparo = p;
		contador++;
		return p;
	}
	public void imprimir3(){
		int c=0;
		while(c<tabDisparosCompu.length){
			for(int i = 0;i < tabDisparosCompu.length;i++){
				System.out.print(tabDisparosCompu[i][c]+", ");
			}
			System.out.println(" ");
			c++;
		}
	}
	public void marcarDanado(int x, int y){
		
	}
	/**no se esta usando*/
	public boolean fueraDeLimite(int x){
		boolean res=true;
		if(x>=0&&x<10)
			res = false;
		return res;
	}
	/*
	public static void main (String args[]){
		Computadora cp = new Computadora();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.dispararCompu();
		cp.imprimir3();
	}*/
}
