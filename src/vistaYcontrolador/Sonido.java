package vistaYcontrolador;
import java.io.*;		//nescesario para hacer sonidos
import java.applet.Applet;	//sonidos de la ubicacion del applet
import java.applet.AudioClip;	//pequeño clip de audio

public class Sonido {
	
	private AudioClip clipSonido;		//el sonido
	private String nombreSonido;		//ubicacion del sonido
	private File file;					//recuperar el archivo q esta en la direccion del sonido
	
	public Sonido(String nombre){
		
		nombreSonido = nombre;
		file = new File(nombre);
		clipSonido = getSonido(nombre);
	}
	
	public void tocarContinuo(){	//funcion para reproducir continuamente
		
		if(file.exists())
			clipSonido.loop();
	}
	
	public void reproducir(){		//
		
		if(file.exists())
			clipSonido.play();
	}
	
	public void parar(){			//si no ponemos esto cuando cerramos el programa se seguira reproduciendose
		
		if(file.exists())
			clipSonido.stop();
	}
	
	public AudioClip getSonido(String sonido){
		
		AudioClip audioClip;
		try{
			audioClip = Applet.newAudioClip(file.toURL());
		}
		catch(Exception e){
			return null;
		}
		
		return audioClip;
	} 
}