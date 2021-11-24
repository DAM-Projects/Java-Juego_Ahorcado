package damProjects.juegoAhorcado.main;

import damProjects.juegoAhorcado.interfaz.JuegoAhorcadoInterfaz;
import damProjects.juegoAhorcado.juego.JuegoAhorcado;

public class Jugar {

	public static void main(String[] args) {
		String frase = "Hola caracola";
		
		JuegoAhorcado juego = new JuegoAhorcado(frase);
		JuegoAhorcadoInterfaz interfaz = new JuegoAhorcadoInterfaz(juego);
		
		System.out.println("La frase es '" + juego + "'");
		
		System.out.println("Introducinedo la letra O");
		int n = juego.intentaLetra('O');
		
		System.out.println("Ha sido usada " + n + " veces!");
		
		System.out.println("La frase es '" + juego + "'");
		
<<<<<<< HEAD
		System.out.println("Weeeeee");
=======
		n = juego.intentaLetra('p');
		n = juego.intentaLetra('q');
		
		System.out.println(interfaz.show());
>>>>>>> master
	}

}
