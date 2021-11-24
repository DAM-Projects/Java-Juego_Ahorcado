package damProjects.juegoAhorcado.main;

import damProjects.juegoAhorcado.juego.JuegoAhorcado;

public class Jugar {

	public static void main(String[] args) {
		String frase = "Hola caracola";
		
		JuegoAhorcado juego = new JuegoAhorcado(frase);
		
		System.out.println("La frase es '" + juego + "'");
		
		System.out.println("Introducinedo la letra O");
		int n = juego.intentaLetra('O');
		
		System.out.println("Ha sido usada " + n + " veces!");
		
		System.out.println("La frase es '" + juego + "'");
		
		System.out.println("Weeeeee");
	}

}
