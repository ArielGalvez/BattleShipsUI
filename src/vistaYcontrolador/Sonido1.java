package vistaYcontrolador;

import java.applet.AudioClip;
import javax.swing.JApplet;
import java.io.File;
import java.net.URL;

public class Sonido1{
	AudioClip sonid;
	
	public void play(String dir ){
		try {
			//archivo de audio
			File f=new File(dir+".wav");
			//lo convertimos a url
			URL u=f.toURL();
			//Bueno de la AudioClip no se puede instancias por eso esto
			AudioClip sonid=JApplet.newAudioClip(u);
			//para que suene
			sonid.play();
			//como el programa se ejecuta muy rapido el audio no se alcanza a escuchar
			Thread.currentThread().sleep(2000);// antes era 5000
			//si fuese una ventana no fuese necesario esa linea
		}catch (Exception ex) {
			//System.out.println (ex);
		}
	}
	// metodo para cancelar el audio
	public void stop(){    
		try {
			sonid.stop();
		}catch (Exception ex) {
			//System.out.println (ex);
		}
	}
}