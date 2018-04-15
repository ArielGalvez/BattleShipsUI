package vistaYcontrolador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modelo.Juego;



public class BatallaNaval extends JFrame implements ActionListener{
	//private boolean onePlayer;
    private Juego modelo;
    private Tablero tableroJug1;
    private Tablero tableroJug2;
    private TableroVision tabDisparosJug1;
    private TableroVision tabDisparosJug2;
	JLabel labelPosX, labelPosY, creacionBarcos, tableroDisparos;
	JLabel x1, x2, x3, x4, x5, x6, x7, x8, x9, x10;
	JLabel y1, y2, y3, y4, y5, y6, y7, y8, y9, y10;
	JLabel disparos, acertados, faltanBuques, jugador,contornoJugador;
	JLabel fondo;
	Sonido sonido;
	private JTextField cajaPosX, cajaPosY, estado;
	
	private JTextField cajaDisparosJug1, cajaAcertadosJug1, cajaFaltanBuquesJug1;
	private JTextField cajaDisparosJug2, cajaAcertadosJug2, cajaFaltanBuquesJug2;
	
	private JButton botonDisparo, botonTurno, botonCrearBote, botonCrearSubmarino;
	JToggleButton botonAccion;

    private int cantidadBuquesJug1 = 0;
    private int cantidadBuquesJug2 = 0;

	private String nombre1 ="";
	private String nombre2 ="";
	
	public BatallaNaval(Juego modelo, String n1, String n2){
		this.modelo = modelo;
		nombre1 = n1;
		nombre2 = n2;
		fondo=new JLabel();
		fondo.setIcon(new ImageIcon(new ImageIcon("accesorios/fondo1.jpg").getImage().getScaledInstance(800,700,5)));
	    fondo.setSize(800,700);
	    this.setContentPane(fondo);
		setTitle("Batalla Naval");
		setSize(800,700);
		sonido = new Sonido("accesorios/disparo2.wav");	
		
		tableroJug1 = new Tablero(BatallaNaval.this, modelo.getTablaDatosJug1());
		tableroJug1.setBounds(50,50,502,502); 
		getContentPane().add(tableroJug1);
		tableroJug1.setVisible(true);
		
		tableroJug2 = new Tablero(BatallaNaval.this, modelo.getTablaDatosJug2());
		tableroJug2.setBounds(50,50,502,502); 
		getContentPane().add(tableroJug2);
		tableroJug2.setVisible(false);
		
		tabDisparosJug1 = new TableroVision(BatallaNaval.this, modelo.getTablaDatosJug2());
		tabDisparosJug1.setBounds(570,350,202,202);
		getContentPane().add(tabDisparosJug1);
		
		tabDisparosJug2 = new TableroVision(BatallaNaval.this, modelo.getTablaDatosJug1());
		tabDisparosJug2.setBounds(570,350,202,202);
		getContentPane().add(tabDisparosJug2);
		
		crearComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
    }
	
    public JTextField getCajaY(){
        return cajaPosY;
    }
    
    public JTextField getCajaX(){
        return cajaPosX;
    }
    public void terminoDeCrearJug1(){
    	if(cantidadBuquesJug1==5){
    		botonTurno.setVisible(true);
    	}
    }
    public void terminoDeCrearJug2(){
    	if(cantidadBuquesJug2<=5){
    		botonTurno.setVisible(true);
    		botonDisparo.setVisible(false);
    	}
    }
    
	public void crearComponentes(){
		labelPosX = crearLabel("x:",70,620,30,30);
		labelPosY = crearLabel("y:",170,620,30,30);
		creacionBarcos = crearLabel("Creacion de Barcos",590,20,300,30);
		tableroDisparos = crearLabel("Tablero de Disparos",580,315,300,30);
				
		x1 = crearLabel("0",30,60,30,30);
		x2 = crearLabel("1",30,110,30,30);
		x3 = crearLabel("2",30,160,30,30);
		x4 = crearLabel("3",30,210,30,30);
		x5 = crearLabel("4",30,260,30,30);
		x6 = crearLabel("5",30,310,30,30);
		x7 = crearLabel("6",30,360,30,30);
		x8 = crearLabel("7",30,410,30,30);
		x9 = crearLabel("8",30,460,30,30);
		x10 = crearLabel("9",30,510,30,30);
		
		y1 = crearLabel("0",70,20,30,30);
		y2 = crearLabel("1",120,20,30,30);
		y3 = crearLabel("2",170,20,30,30);
		y4 = crearLabel("3",220,20,30,30);
		y5 = crearLabel("4",270,20,30,30);
		y6 = crearLabel("5",320,20,30,30);
		y7 = crearLabel("6",370,20,30,30);
		y8 = crearLabel("7",420,20,30,30);
		y9 = crearLabel("8",470,20,30,30);
		y10 = crearLabel("9",520,20,30,30);
		
		disparos = crearLabel("Disparos:",570,180,150,30);
		acertados = crearLabel("Acertados:",570,220,150,30);
		faltanBuques = crearLabel("Faltan Buques:",570,260,160,30);
		jugador = crearLabel("Turno de "+nombre1,70,570,290,30);
		jugador.setForeground(new Color(255,105,255));
		jugador.setFont(new Font("Kristen ITC",Font.BOLD,24));
		
		contornoJugador =crearLabel("Turno de "+nombre1,73,572,290,30);
		contornoJugador.setForeground(Color.black);
		contornoJugador.setFont(new Font("Kristen ITC",Font.BOLD,24));
		
		cajaDisparosJug1 = crearCaja(710,180,30,30);
		cajaDisparosJug1.setText(""+modelo.getDisparosJug1());
		
		cajaAcertadosJug1 = crearCaja(710,220,30,30);
		cajaAcertadosJug1.setText(""+modelo.getDisparosAcertadosJug1());
		cajaFaltanBuquesJug1 = crearCaja(710,260,30,30);
		cajaFaltanBuquesJug1.setText(""+modelo.getcantidadBuquesJug2());
	
		cajaPosX = crearCaja1(100,620,30,30);
		cajaPosY = crearCaja1(200,620,30,30);
		cajaPosX.setText(""+1);
		cajaPosY.setText(""+1);
		estado = crearCaja(400,570,350,30);
		estado.setText("ingrese posicion y agregue barcos");
		
		botonDisparo = crearBoton("Disparar",270,620,110,30);
		botonDisparo.setVisible(false);
		botonTurno = crearBoton("Pasar turno",400,620,140,30);
		botonTurno.setVisible(false);
		botonCrearBote = crearBoton("agregar Bote",580,60,155,30);
		botonCrearSubmarino = crearBoton("agregar Submarino",580,95,197,30);
		
		botonAccion = new JToggleButton("Ocultar");
		botonAccion.setBounds(550,620,105,30);
		getContentPane().add(botonAccion);
		botonAccion.addActionListener(this);
		botonAccion.setForeground (new Color(255,0,102));
		botonAccion.setBackground (new Color(105,255,255));
		botonAccion.setFont(new Font("Kristen ITC",Font.BOLD,16));
		
		cajaDisparosJug2 = crearCaja(710,180,30,30);
		cajaDisparosJug2.setVisible(false);
		cajaDisparosJug2.setText(""+modelo.getDisparosJug2());
		cajaAcertadosJug2 = crearCaja(710,220,30,30);
		cajaAcertadosJug2.setText(""+modelo.getDisparosAcertadosJug2());
		cajaAcertadosJug2.setVisible(false);
		cajaFaltanBuquesJug2 = crearCaja(710,260,30,30);
		cajaFaltanBuquesJug2.setText(""+modelo.getcantidadBuquesJug1());	
		cajaFaltanBuquesJug2.setVisible(false);
	}

	public JLabel crearLabel(String texto,int x,int y,int w,int h){
		JLabel label = new JLabel(texto);
		label.setBounds(x,y,w,h);
		label.setForeground (new Color(0,255,0));
		label.setFont(new Font("Kristen ITC",Font.BOLD,16));
		getContentPane().add(label);
		return label;
	}
	
	public JTextField crearCaja(int x,int y,int w,int h){
		JTextField caja = new JTextField();
		caja.setBounds(x,y,w,h);
		getContentPane().add(caja);
		caja.setBackground (new Color(120,180,190));
		caja.setForeground (new Color(0,0,102));
		caja.setFont(new Font("Kristen ITC",Font.BOLD,16));
        //caja.setEnabled(true);	//no se para q es, estaba en su codigo d usted lice.
        caja.setEditable(false);//para q no se escriba dentro de las casillas
		return caja;
	}
	
	public JTextField crearCaja1(int x,int y,int w,int h){
		JTextField caja = new JTextField();
		caja.setBounds(x,y,w,h);
		getContentPane().add(caja);
		caja.setBackground (new Color(120,180,190));
		caja.setForeground (new Color(0,0,102));
		caja.setFont(new Font("Kristen ITC",Font.BOLD,16));
		return caja;
	}
	
	public JButton crearBoton(String texto,int x,int y,int w,int h){
		JButton boton = new JButton(texto);
		boton.setBounds(x,y,w,h);
		boton.addActionListener(this);
		boton.setForeground (new Color(255,0,102));
		boton.setBackground (new Color(105,255,255));
		boton.setFont(new Font("Kristen ITC",Font.BOLD,16));	//tipo de letra y taman
		getContentPane().add(boton);
		return boton;
	}
	
	public void marcarCasilla(int x, int y){
		cajaPosX.setText(""+x+"");	
		cajaPosY.setText(""+y+"");	
	}
	/*metodos para el controlador*/
	int disparosPermitidos=0;
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		int x, y;
		
		x = Integer.parseInt(cajaPosX.getText());
		y = Integer.parseInt(cajaPosY.getText());
		
		if(obj == botonCrearBote){
			if(EstaFueraDeLimite(x, y)){
				if(modelo.getTurnoJug1()){
					if(modelo.addBote(x, y)){
						estado.setText("bote agregado en "+x+", "+y);
						x--;
						tableroJug1.addBotes(x*50,y*50);
						cantidadBuquesJug1++;
						terminoDeCrearJug1();
					}
					else
						estado.setText(modelo.getEstado());
				}
				else if(modelo.getTurnoJug2()){
					if(modelo.addBote(x, y)){
						estado.setText("bote agregado en "+x+", "+y);
						x--;
						tableroJug2.addBotes(x*50,y*50);
						cantidadBuquesJug2++;
						terminoDeCrearJug2();
					}
					else
						estado.setText(modelo.getEstado());
				}
			}
			else
				estado.setText("fuera de limite");
		}
		else if(obj == botonCrearSubmarino){
			if(EstaFueraDeLimite(x, y)){
				if(modelo.getTurnoJug1()){
					if(modelo.addSubmarino(x, y)){
						estado.setText("submarino agregado en "+x+", "+y);
						x--;
						y--;
						tableroJug1.addSubmarinos(x*50,y*50);
						cantidadBuquesJug1++;
						terminoDeCrearJug1();
					}
					else
						estado.setText(modelo.getEstado());
				}
				else if(modelo.getTurnoJug2()){
					if(modelo.addSubmarino(x, y)){
						estado.setText("submarino agregado en "+x+", "+y);
						x--;
						y--;
						tableroJug2.addSubmarinos(x*50,y*50);
						cantidadBuquesJug2++;
						terminoDeCrearJug2();
					}
					else
						estado.setText(modelo.getEstado());
				}
			}
			else
				estado.setText("fuera de limite");
		}
		
		else if (obj == botonDisparo&&disparosPermitidos<1){
			if(EstaFueraDeLimite(x, y)){
				//Sonido1 s = new Sonido1();
				//s.play("accesorios/disparo2");
				sonido.reproducir();
				modelo.disparar(x, y);
				estado.setText(modelo.getEstado());
				if(modelo.getMode()){
					if(modelo.getTurnoJug1()){
					tabDisparosJug1.repaint();
					tableroJug1.repaint();
					disparosPermitidos++;
					cajaDisparosJug1.setText(""+modelo.getDisparosJug1());
					cajaAcertadosJug1.setText(""+modelo.getDisparosAcertadosJug1());
					cajaFaltanBuquesJug1.setText(""+modelo.getcantidadBuquesJug2());
					}
					else if(modelo.getTurnoJug2()){
					tabDisparosJug2.repaint();
					tableroJug2.repaint();
					disparosPermitidos++;
					cajaDisparosJug2.setText(""+modelo.getDisparosJug1());
					cajaAcertadosJug2.setText(""+modelo.getDisparosAcertadosJug2());
					cajaFaltanBuquesJug2.setText(""+modelo.getcantidadBuquesJug1());
					}
				}
				else{
					if(modelo.getTurnoJug1()){
						tabDisparosJug1.repaint();
						tableroJug1.repaint();
						disparosPermitidos++;
						cajaDisparosJug1.setText(""+modelo.getDisparosJug1());
						cajaAcertadosJug1.setText(""+modelo.getDisparosAcertadosJug1());
						cajaFaltanBuquesJug1.setText(""+modelo.getcantidadBuquesJug2());
					}
					else if(modelo.getTurnoJug2()){
						cajaDisparosJug2.setText(""+modelo.getDisparosJug1());
						cajaAcertadosJug2.setText(""+modelo.getDisparosAcertadosJug2());
						cajaFaltanBuquesJug2.setText(""+modelo.getcantidadBuquesJug1());
					}
				}
			}
			else 
				estado.setText("Posicion no valida, fuera de limites");
		}
		
		else if(obj == botonTurno){
			int controlador=0;
			if(modelo.getMode()){
				if(modelo.getTurnoJug1()){
					tableroJug1.setVisible(false);
					tabDisparosJug1.setVisible(false);
					cajaDisparosJug1.setVisible(false);
					cajaAcertadosJug1.setVisible(false); 
					cajaFaltanBuquesJug1.setVisible(false);

					tableroJug2.setVisible(true);
					tabDisparosJug2.setVisible(true);
					cajaDisparosJug2.setVisible(true);
					cajaAcertadosJug2.setVisible(true); 
					cajaFaltanBuquesJug2.setVisible(true);

					jugador.setText("Turno de "+nombre2);
					contornoJugador.setText("Turno de "+nombre2);
					botonDisparo.setVisible(true);//aqui
				}
				else if(modelo.getTurnoJug2()){
					tableroJug1.setVisible(true);
					tabDisparosJug1.setVisible(true);
					cajaDisparosJug1.setVisible(true);
					cajaAcertadosJug1.setVisible(true); 
					cajaFaltanBuquesJug1.setVisible(true);
				
					tableroJug2.setVisible(false);
					tabDisparosJug2.setVisible(false);
					cajaDisparosJug2.setVisible(false);
					cajaAcertadosJug2.setVisible(false); 
					cajaFaltanBuquesJug2.setVisible(false);
				
					jugador.setText("Turno de "+nombre1);
					contornoJugador.setText("Turno de "+nombre1);
					botonDisparo.setVisible(true);
					verGanador();//ultimo q agregue
				}
				disparosPermitidos=0;
				modelo.setTurno();
			}
			else{
				if(modelo.getTurnoJug1()){
					botonDisparo.setVisible(true);
					botonTurno.setVisible(true);
					jugador.setText("Turno de "+nombre2);
					contornoJugador.setText("Turno de "+nombre2);
				}
				else if(modelo.getTurnoJug2()){
					jugador.setText("Turno de "+nombre1);
					contornoJugador.setText("Turno de "+nombre1);
					if(controlador == 0){
						modelo.generarTablero();
						//modelo.setTurno();//modif------------
					}
					verGanador();//ultimo q agregue
				}
				disparosPermitidos=0;
				modelo.setTurno();//para dar el turno a la compu
				//modelo.setTurno();//para q pase el turno al jugador2--------
				controlador++;
			}
		}
		else if(obj ==botonAccion){
			if(modelo.getTurnoJug1()){
				if(botonAccion.isSelected())
					tableroJug1.ocultar();
				else
					tableroJug1.mostrar();	
			}
			else if(modelo.getTurnoJug2()){
				if(botonAccion.isSelected())
					tableroJug2.ocultar();
				else
					tableroJug2.mostrar();
			}
		}
	}
	
	private boolean EstaFueraDeLimite(int x, int y){
		boolean fuera = false;
		if((x>=0 && x<10) && (y>=0 && y<10))
			fuera = true;
		return fuera;
	}
	private void verGanador(){
		if(modelo.getcantidadBuquesJug1() == 0 && modelo.getcantidadBuquesJug2() > 0){
			estado.setText("gana "+nombre2);
			estado.setForeground(Color.red);
			botonTurno.setVisible(false);
			botonCrearBote.setVisible(false);
			botonCrearSubmarino.setVisible(false);
			botonDisparo.setVisible(false);
			tableroJug1.mostrar();
			tableroJug2.mostrar();
		}
		else if(modelo.getcantidadBuquesJug1() > 0 && modelo.getcantidadBuquesJug2() == 0){
			estado.setText("gana "+nombre1);
			estado.setForeground(Color.red);
			botonTurno.setVisible(false);
			botonCrearBote.setVisible(false);
			botonCrearSubmarino.setVisible(false);
			botonDisparo.setVisible(false);
			tableroJug1.mostrar();
			tableroJug2.mostrar();
		}
		else if(modelo.getcantidadBuquesJug1() == 0 && modelo.getcantidadBuquesJug2() == 0){
			estado.setText("Empate, No hay ganador");
			estado.setForeground(Color.red);
			botonTurno.setVisible(false);
			botonCrearBote.setVisible(false);
			botonCrearSubmarino.setVisible(false);
			botonDisparo.setVisible(false);
			tableroJug1.mostrar();
			tableroJug2.mostrar();
		}
	}
}
