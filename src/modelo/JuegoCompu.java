package modelo;

import java.util.ArrayList;
import java.util.Random;

import codigoDocente.Board;
import codigoDocente.Boat;
import codigoDocente.Position;
import codigoDocente.Ship;
import codigoDocente.Submarine;

/**
 * @author Ariel Galvez Ponce
 * 
 * @version 29/06/2013
 * 
 * ESTA CLASE NADA Q VER AUN LO ESTABA TRABAJANDO
 * 
 * */

public class JuegoCompu {
	
	private boolean twoPlayers;
	private int[][] tabDisparosCompu = new int[10][10];
	private Position ultimoDisparo = new Position(2,2);
	private int contador = 0;
	
	private Board boardJug1, boardJug2;
	
	private static final int LIMITE_SUBMARINOS = 3;
	private static final int LIMITE_BOTES = 2;
	private int submarinosJug1;
	private int botesJug1;
	
	private int submarinosJug2;
	private int botesJug2;
	
	private int disparosJug1;
	private int disparosJug2;
	
	private int disparosAcertadosJug1;
	private int disparosAcertadosJug2;
	
	private boolean turnoJug1;
	private boolean turnoJug2;
	
	private String estado ="";
	
	private int tablaDatosJug1[][] = new int[10][10];
	private int tablaDatosJug2[][] = new int[10][10];
	
	public boolean getTurnoJug1(){
		return turnoJug1;
	}
	public boolean getTurnoJug2(){
		return turnoJug2;
	}
	public void setTurno(){
		if(twoPlayers){
		if(turnoJug1){
			turnoJug1 = false;
			turnoJug2 = true;
		}
		else if (turnoJug2){
			turnoJug1 = true;
			turnoJug2 = false;
		}
		}
		else{
			if(turnoJug1){
				turnoJug1 = false;
				turnoJug2 = true;
			}
			else if (turnoJug2){
				dispararAhora();
				turnoJug1 = true;
				turnoJug2 = false;
			}
		}
	}
	public String getEstado(){
		return estado;
	}
	public int getDisparosJug1(){
		return disparosJug1;
	}
	public int getDisparosJug2(){
		return disparosJug2;
	}
	public int[][] getTablaDatosJug1(){
		return tablaDatosJug1;
	}
	public int[][] getTablaDatosJug2(){
		return tablaDatosJug2;
	}
	public int getDisparosAcertadosJug1(){
		return disparosAcertadosJug1;
	}
	public int getDisparosAcertadosJug2(){
		return disparosAcertadosJug2;
	}
	public int getcantidadBuquesJug1(){
		return cantidadBuquesFaltantes(boardJug1.getShips());
	}
	public int getcantidadBuquesJug2(){
		return cantidadBuquesFaltantes(boardJug2.getShips());
	}
	public JuegoCompu(boolean twoPlayers){
		/** 0 agua (no existe nada)
		* 	1 parte barco sanito
		* 	2 parte barco dañado
		* 	3 barco destruido
		*   4 al agua
		* con matrices primero llena en horizontal(y) luego en vertical(x)
		*/
		this.twoPlayers = twoPlayers;
		boardJug1 = new Board(10,10);
		boardJug2 = new Board(10,10);
		for(int n=0; n<10; n++){
			for(int m=0; m<10; m++){
				tablaDatosJug1[n][m] = 0;
				tablaDatosJug2[n][m] = 0;
				tabDisparosCompu[n][m] = 0;
			}
		}
		submarinosJug1 = 0;
		botesJug1 = 0;
		
		submarinosJug2 = 0;
		botesJug2 = 0;
		
		disparosJug1 = 0;
		disparosJug2 = 0;

		turnoJug1 = true;
		turnoJug2 = false;
	}
	
	public boolean addBote(int x, int y){
		boolean agregado = false;
		Position pos = new Position(x,y);//aqui x,y
		Boat bote = new Boat("Apofis", pos);
		if(turnoJug1){
			if(botesJug1 < LIMITE_BOTES){
				if(boardJug1.add(bote)){
					addTablaDatos(bote.getPositions(), tablaDatosJug1);
					botesJug1++;
					agregado = true;
				}
				else
					estado ="posicion incorrecta";
			}
			else
				estado ="botes al limite";
		}
		else if(turnoJug2){
			if(botesJug2 < LIMITE_BOTES){
				if(boardJug2.add(bote)){
					addTablaDatos(bote.getPositions(), tablaDatosJug2);
					botesJug2++;
					agregado = true;
				}
				else
					estado ="posicion incorrecta";
			}
			else
				estado ="botes al limite";
		}
		return agregado;
	}
	public boolean addSubmarino(int x, int y){
		boolean agregado = false;
		Position pos = new Position(x,y);//aqui x,y
		Submarine submarino = new Submarine("Nautillius", pos);
		if(turnoJug1){
			if(submarinosJug1 < LIMITE_SUBMARINOS){
				if(boardJug1.add(submarino)){
					addTablaDatos(submarino.getPositions(), tablaDatosJug1);
					submarinosJug1++;
					agregado = true;
				}
				else
					estado ="posicion incorrecta";
			}
			else
				estado ="submarinos al limite";
		}
		else if(turnoJug2){
			if(submarinosJug2 < LIMITE_SUBMARINOS){
				if(boardJug2.add(submarino)){
					addTablaDatos(submarino.getPositions(), tablaDatosJug2);
					submarinosJug2++;
					agregado = true;
				}
				else
					estado ="posicion incorrecta";
			}
			else
				estado ="submarinos al limite";
		}
		return agregado;
	}
	/**
	 * metodo para disparar*/
	public void disparar(int x, int y){
		Position pos = new Position(x,y);
		if(twoPlayers){
			if(turnoJug1){
				if(boardJug2.shoot(pos)){
					tablaDatosJug2[x][y] = 2;
					disparosAcertadosJug1++;
					estado="disparo acertado!";
					disparosJug1++;
					AgregarDanados(boardJug2.getShips(), tablaDatosJug2);//aqui
				}
				else{
					tablaDatosJug2[x][y] = 4;
					estado="fallaste!";
					disparosJug1++;
				}
			}
			else if(turnoJug2){
				if(boardJug1.shoot(pos)){
					tablaDatosJug1[x][y] = 2;
					disparosAcertadosJug2++;
					estado="disparo acertado!";
					disparosJug2++;
					AgregarDanados(boardJug1.getShips(), tablaDatosJug1);//aqui
				}
				else{
					tablaDatosJug1[x][y] = 4;
					estado="fallaste!";
					disparosJug2++;
				}
			}
		}
		else{
			if(turnoJug1){
				if(boardJug2.shoot(pos)){
					tablaDatosJug2[x][y] = 2;
					disparosAcertadosJug1++;
					estado="disparo acertado!";
					disparosJug1++;
					AgregarDanados(boardJug2.getShips(), tablaDatosJug2);//aqui
				}
				else{
					tablaDatosJug2[x][y] = 4;
					estado="fallaste!";
					disparosJug1++;
				}
			}
		}
	}

	
	/**
	 * metodo para ingresar las posiciones del board al tablero*/
	public void addTablaDatos(ArrayList<Position> a, int[][] tab){
		for(int i = 0;i < a.size();i++){
			Position p = a.get(i);
			tab[p.getX()][p.getY()] = 1;
		}
	}

	public void AgregarDanados(ArrayList<Ship> b, int[][] tab){
		for(int i = 0;i < b.size();i++){
			Ship buque = b.get(i);
			if(buque.isSunk()){
				agregarPartesRojas(buque.getPositions(), tab);
			}
		}
	}
	public void agregarPartesRojas(ArrayList<Position> a, int[][] tab){
		for(int i = 0;i < a.size();i++){
			Position p = a.get(i);
			int x = p.getX();
			int y = p.getY();
			tab[x][y] = 3;
			
		}
	}
	public int cantidadBuquesFaltantes(ArrayList<Ship> b){
		int res=5;
		for(int i = 0;i < b.size();i++){
			Ship buque = b.get(i);
			if(buque.isSunk())
				res = res-1;
		}
		return res;
	}

	public void imprimir(){
		int c=0;
		while(c<10){
			for(int i = 0;i < tabDisparosCompu.length;i++){
				System.out.print(tabDisparosCompu[i][c]+", ");
			}
			System.out.println(" ");
			c++;
		}
	}
	public void imprimir3(){
		int c=0;
		while(c<10){
			for(int i = 0;i < tablaDatosJug1.length;i++){
				System.out.print(tablaDatosJug1[i][c]+", ");
			}
			System.out.println(" ");
			c++;
		}
	}
	
	public void generarTablero(){
		while(submarinosJug2 < LIMITE_SUBMARINOS){
			Random generado = new Random();
			int posX = generado.nextInt(8)+1; //numero entre 1 y 8
			int posY = generado.nextInt(9)+1; //numero entre 1 y 9
			addSubmarino(posX, posY);
		}
		while(botesJug2 < LIMITE_BOTES){
			Random generado = new Random();
			int posX = generado.nextInt(9)+1;  //numero entre 1 y 9
			int posY = generado.nextInt(10);    //numero entre 0 y 9
			addBote(posX, posY);
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
		int y = rdm.nextInt(10); //genera de 0 a 9
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
		if(!fueraDeLimite(x-1))
			pos = new Position(x-1, y);//izquieda
		else
			pos = disparoDer();
		return pos;
	}
	public Position disparoDer(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		if(!fueraDeLimite(x+1))
			pos = new Position(x+1, y);//derecha
		else
			pos = disparoArriba();
		return pos;
	}
	public Position disparoArriba(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		if(!fueraDeLimite(y-1))
			pos = new Position(x, y-1);//arriba
		else
			pos = disparoAbajo();
		return pos;
	}
	public Position disparoAbajo(){
		Position pos;
		int x = ultimoDisparo.getX();
		int y = ultimoDisparo.getY();
		if(!fueraDeLimite(y+1))
			pos = new Position(x, y+1);//abajo
		else
			pos = nuevoDisparo();
		return pos;
	}
	public void dispararAhora(){
		Position pos = dispararCompu();
		int pX= pos.getX();
		int pY= pos.getY();
		if(boardJug1.shoot(pos)){
			contador = contador+1;
			tabDisparosCompu[pX][pY] = 1;
			tablaDatosJug1[pX][pY] = 2;
			disparosAcertadosJug2++;
			disparosJug2++;
			AgregarDanados(boardJug1.getShips(), tablaDatosJug1);//aqui
		}
		else{
			tablaDatosJug1[pX][pY] = 4;
			tabDisparosCompu[pX][pY] = 1;
			disparosJug2++;
			contador = 0;
		}
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
		}	
		int x = p.getX();
		int y = p.getY();
		tabDisparosCompu[x][y] = 1;
		ultimoDisparo = p;
		//contador++;
		return p;
	}
	public boolean fueraDeLimite(int n){
		boolean res=true;
		if(n>=0 && n<10)
			res = false;
		return res;
	}

	
	public static void main(String[] args){
		JuegoCompu juego = new JuegoCompu(false);
		
		juego.addBote(3, 4);
		juego.addBote(1, 1);
		juego.addSubmarino(8, 8);
		juego.addSubmarino(5, 5);
		juego.addSubmarino(8, 2);
		juego.disparar(1, 1);
		juego.setTurno();//turno 2
		juego.generarTablero();
		juego.setTurno();//1
		juego.setTurno();//2
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		juego.setTurno();
		System.out.println("cantidad buques Jug1: "+juego.getcantidadBuquesJug1());
		juego.imprimir3();
		System.out.println("estado: "+juego.estado);
		System.out.println("disparos Jug2: "+juego.disparosJug2);
		juego.imprimir();
	}

}
