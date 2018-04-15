package vistaYcontrolador;


import javax.swing.*; 

import modelo.Juego;
import modelo.JuegoCompu;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Ariel Galvez Ponce
 * Ahora si ya estan separados por paquetes
 * @version 7/07/2013
 * 
 * */

public class VentanaInicio extends JFrame{
	JLabel fondo;
	JButton bt1Jugador;
	JButton bt2Jugadores;
	JButton btSalir;
	JLabel autor;
	JLabel carrera;
	JLabel nombreJug1, nombreJug2;
	JButton btComenzar;
	JTextField cajaJug1, cajaJug2;
	boolean twoPlayers;
	Sonido sonido;
	
	public VentanaInicio(){
		
		fondo=new JLabel();
		fondo.setIcon(new ImageIcon(new ImageIcon("accesorios/fondo2.jpg").getImage().getScaledInstance(800,700,5)));
	    fondo.setSize(800,700);
	    this.setContentPane(fondo);
		setTitle("Batalla Naval");
		setSize(800,700);
		sonido = new Sonido("accesorios/musica.wav");
		
		nombreJug1 = new JLabel("JUGADOR 1:");
		nombreJug1.setBounds(205,300,164,30);
		nombreJug1.setForeground (Color.white);
		nombreJug1.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));
		getContentPane().add(nombreJug1);
		nombreJug1.setVisible(false);
		
		nombreJug2 = new JLabel("JUGADOR 2:");
		nombreJug2.setBounds(205,335,164,30);
		nombreJug2.setForeground (Color.white);
		nombreJug2.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));
		getContentPane().add(nombreJug2);
		nombreJug2.setVisible(false);
		
		autor = new JLabel("autor: Ariel Galvez Ponce");
		autor.setBounds(620, 540, 180, 30);
		autor.setForeground (Color.white);
		getContentPane().add(autor);
		
		carrera = new JLabel("carrera: Ing. de Sistemas");
		carrera.setBounds(620, 570, 180, 30);
		carrera.setForeground (Color.white);
		getContentPane().add(carrera);
		
		cajaJug1 = new JTextField(" nombre 1_");
		cajaJug1.setBounds(318,300,164,30);
		cajaJug1.setForeground (Color.white);
		cajaJug1.setBackground (Color.black);
		cajaJug1.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));	//tipo de letra y taman
		getContentPane().add(cajaJug1);
		cajaJug1.setVisible(false);
		
		cajaJug2 = new JTextField(" nombre 2_");
		cajaJug2.setBounds(318,335,164,30);
		cajaJug2.setForeground (Color.white);
		cajaJug2.setBackground (Color.black);
		cajaJug2.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));	
		getContentPane().add(cajaJug2);
		cajaJug2.setVisible(false);
		
		bt1Jugador = new JButton("1 JUGADOR");
		bt1Jugador.setBounds(318,300,164,30);
		bt1Jugador.setForeground (Color.white);
		bt1Jugador.setBackground (Color.black);
		bt1Jugador.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));	
		getContentPane().add(bt1Jugador);
		bt1Jugador.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bt1Jugador.setBounds(318,265,164,30);
				bt2Jugadores.setVisible(false);
				nombreJug1.setVisible(true);
				nombreJug2.setVisible(true);
				cajaJug1.setVisible(true);
				cajaJug2.setVisible(true);
				cajaJug2.setText(" Ordenador");
				cajaJug2.setEditable(false);
				btComenzar.setVisible(true);
				twoPlayers = false;
			}
		});
		
		bt2Jugadores = new JButton("2 JUGADORES");
		bt2Jugadores.setBounds(318,335,164,30);
		bt2Jugadores.setForeground (Color.white);
		bt2Jugadores.setBackground (Color.black);
		bt2Jugadores.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));	
		getContentPane().add(bt2Jugadores);
		bt2Jugadores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bt1Jugador.setVisible(false);
				bt2Jugadores.setBounds(318,265,164,30);
				nombreJug1.setVisible(true);
				nombreJug2.setVisible(true);
				cajaJug1.setVisible(true);
				cajaJug2.setVisible(true);
				btComenzar.setVisible(true);
				twoPlayers = true;
			}
		});
		//s.play("accesorios/guerra");
		sonido.tocarContinuo();
		btSalir = new JButton("SALIR");
		btSalir.setBounds(318,405,164,30);
		btSalir.setForeground (Color.white);
		btSalir.setBackground (Color.black);
		btSalir.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));
		getContentPane().add(btSalir);
		btSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				/*try {s.stop();}
				catch(Exception e1){  
					
				}
				s = null;*/
				sonido.parar();
				System.exit(0);
			}
		});
		
		/*aqui donde se inicia el MVC*/
		btComenzar = new JButton("COMENZAR");
		btComenzar.setBounds(318,370,164,30);
		btComenzar.setForeground (Color.white);
		btComenzar.setBackground (Color.black);
		btComenzar.setFont(new Font("Calibri (Cuerpo)",Font.BOLD,18));	
		getContentPane().add(btComenzar);
		btComenzar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Juego modelo = new Juego(twoPlayers);
				BatallaNaval vistaYcontrolador = new BatallaNaval(modelo, cajaJug1.getText(), cajaJug2.getText());
				VentanaInicio.this.hide();
				/*try {s.stop();}
				catch(Exception e1){   
				}*/
			}
		});
		btComenzar.setVisible(false);
	}
	
	public static void main(String[] args) {
        VentanaInicio  vista      = new VentanaInicio();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);//centra la ventana
        vista.setResizable(false);	//no deja maximizar
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
