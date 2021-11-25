package damProjects.juegoAhorcado.main;

import damProjects.juegoAhorcado.interfaz.JuegoAhorcadoInterfaz;
import damProjects.juegoAhorcado.juego.JuegoAhorcado;

public class Jugar {

	public static void main(String[] args) {
		String frase = "En un lugar de la Mancha de cuyo nombre no quiero acordarme no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero adarga antigua rocín flaco y galgo corredor Una olla de algo más vaca que carnero salpicón las más noches duelos y quebrantos los sábados lantejas los viernes algún palomino de añadidura los domingos consumían las tres partes de su hacienda";
//		String frase = "Estapalabraesdemasiadolargaparacaberaqui";
//		String frase = "ese commit fue algo largo";
		
		JuegoAhorcado juego = new JuegoAhorcado(frase);
		JuegoAhorcadoInterfaz interfaz = new JuegoAhorcadoInterfaz(juego);
		
		System.out.println("La frase es '" + juego + "'");
		
//		System.out.println("Introducinedo la letra O");
//		int n = juego.intentaLetra('O');
//		
//		System.out.println("Ha sido usada " + n + " veces!");
//		
//		System.out.println("La frase es '" + juego + "'");
		

		System.out.println("Weeeeee");
		
//		Debug palabra larga
//		int n = juego.intentaLetra('A');
//		n = juego.intentaLetra('E');
//		n = juego.intentaLetra('S');
//		n = juego.intentaLetra('T');
//		n = juego.intentaLetra('P');
//		n = juego.intentaLetra('D');
//		n = juego.intentaLetra('L');
//		n = juego.intentaLetra('U');
//		n = juego.intentaLetra('R');
//		n = juego.intentaLetra('G');
//		n = juego.intentaLetra('B');
//		n = juego.intentaLetra('C');
//		n = juego.intentaLetra('Q');
//		n = juego.intentaLetra('M');
//		n = juego.intentaLetra('I');
//		n = juego.intentaLetra('O');
		
		System.out.println(interfaz.show());
		
		juego.intentaLetra('E');
		
		System.out.println(interfaz.show());
		
		juego.intentaLetra('W');
		juego.intentaLetra('W');
//		juego.intentaLetra('W');
//		juego.intentaLetra('W');
//		juego.intentaLetra('W');
//		juego.intentaLetra('W');
		
		System.out.println(interfaz.show());
	}

}
