package damProjects.juegoAhorcado.main;

import java.util.Scanner;

import damProjects.juegoAhorcado.diccionario.Diccionario;
import damProjects.juegoAhorcado.interfaz.JuegoAhorcadoInterfaz;
import damProjects.juegoAhorcado.juego.JuegoAhorcado;

public class Jugar {

	private static Scanner sc;
	
	private static String[] DICCIONARIOS_DISPONIBLES = {
		"diccionario_es.txt",
		"frases_cine.txt"
	};
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		Diccionario dict = getDiccionario();
		
		JuegoAhorcado juego = new JuegoAhorcado(dict.getWord());
		JuegoAhorcadoInterfaz interfaz = new JuegoAhorcadoInterfaz(juego, sc);
		
		while (!juego.terminado()) {
			interfaz.show();
			interfaz.pideLetra();
		}
		interfaz.show();
		if (juego.getVidasRestantes() > 0) {
			System.out.println("Enhorabuena, lo has conseguido.");
		}
		else {
			System.out.println("Ups, no lo has conseguido.");
			System.out.println("La frase era\n" + juego.getRealFrase());
		}
	}
	
	private static Diccionario getDiccionario() {
		System.out.println("Selecciona el diccionario que quieres utilizar.\nEste será la fuente de frases para el juego.\n");
		String d;
		while (true) {
			try {
				d = (String) getObjectIn("Qué diccionario quieres usar?\n", DICCIONARIOS_DISPONIBLES);
				return new Diccionario("src/damProjects/juegoAhorcado/diccionario/" + d);
			}
			catch (Exception e) {
				System.out.println("Ha habido un error :S");
				System.out.println(e.getMessage());
			}
		}
	}

	// GETTERS
	public static Object getObjectIn(String question, Object[] arr) {
		String q = question;
		for (int i = 0; i < arr.length; i++) {
			q += String.format("%d - %s\n", i + 1, arr[i]);
		}
		q += "-> ";
		return arr[getIntInRange(q, 1, arr.length) - 1];
	}
	
	/**
	 * @param question - Question to show using System.out.print
	 * @return boolean given by Scanner
	 */
	public static boolean getBoolean(String question) {
		String r;
		while (true) {
			System.out.print(question);
			r = sc.nextLine();
			
			if (r.matches("[Yy][Ee][Ss]|[Ss][Iií]"))
				return true;
			else if (r.matches("[Nn][Oo]"))
				return false;
			else
				System.out.println("No entinedo. Sí o no?");
		}
	}

	/**
	 * @param question - Question to show using System.out.print
	 * @return Double given by Scanner
	 */
	public static double getDouble(String question) {
		while (true) {
			try {
				System.out.print(question);
				return Double.parseDouble(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("El valor no es un número decimal válido.\n");
			}
		}
	}
	
	/**
	 * @param question - Question to show using System.out
	 * @return Integer greater or equal to 0
	 */
	public static double getNaturalDouble(String question) {
		double n = 0d;
		boolean isNotNatural = true;
		while (isNotNatural) {
			n = getDouble(question);
			
			if (n >= 0d) {
				isNotNatural = false;
			}
			else {
				System.out.println("El número tiene que ser un natural -> [0, inf)\n");
			}
		}
		return n;
	}

	/**
	 * @param question - Question to show using System.out
	 * @param minLen - min length of String
	 * @param maxLen - max length of String
	 * @return Response given by the scanner meeting the criteria.
	 */
	public static String getString(String question, int minLen, int maxLen) {
		String str;
		while (true) {
			System.out.print(question);
			str = sc.nextLine();
			
			if (str.length() < minLen) {
				System.out.println("La longitud mínima es de " + minLen + " caracteres\n");
			}
			else if (str.length() > maxLen) {
				System.out.println("La longitud máxima es de " + maxLen + " caracteres.\n");
			}
			else {
				return str;
			}
		}
	}
	
	public static String getString(String question) {
		System.out.print(question);
		return sc.nextLine();
	}
	
	/**
	 * @param question - Question to show using System.out
	 * @return Integer greater or equal to 0
	 */
	public static int getNatural(String question) {
		int n = 0;
		boolean isNotNatural = true;
		while (isNotNatural) {
			n = getInt(question);
			
			if (n >= 0) {
				isNotNatural = false;
			}
			else {
				System.out.println("El número tiene que ser un natural -> [0, inf)\n");
			}
		}
		return n;
	}
	
	/**
	 * @param question - Question to show using System.out.print
	 * @return Integer given by Scanner
	 */
	public static int getInt(String question) {
		while (true) {
			try {
				System.out.print(question);
				return Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("El valor no es un número entero válido.\n");
			}
		}
	}

	/**
	 * @param question - Question to show using System.out
	 * @param min - min value of the desired int
	 * @param max - max value of the desired int
	 * @return Integer inside the interval [min, max]
	 */
	public static int getIntInRange(String question, int min, int max) {
		if (min > max) {
			int swap = min;
			min = max;
			max = swap;
		}
		
		int n = 0;
		boolean isNotValid = true;
		while (isNotValid) {
			n = getInt(question);
			
			if (n >= min && n <= max) {
				isNotValid = false;
			}
			else {
				System.out.printf(
					"El número tiene que ser un natural en el rango [%d, %d]\n\n",
					min, max
				);
			}
		}
		return n;
	}
	
//	public static void main(String[] args) {
//		String frase = "En un lugar de la Mancha de cuyo nombre no quiero acordarme no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero adarga antigua rocín flaco y galgo corredor Una olla de algo más vaca que carnero salpicón las más noches duelos y quebrantos los sábados lantejas los viernes algún palomino de añadidura los domingos consumían las tres partes de su hacienda";
////		String frase = "Estapalabraesdemasiadolargaparacaberaqui";
////		String frase = "ese commit fue algo largo";
//		
//		JuegoAhorcado juego = new JuegoAhorcado(frase);
//		JuegoAhorcadoInterfaz interfaz = new JuegoAhorcadoInterfaz(juego);
//		
//		System.out.println("La frase es '" + juego + "'");
//		
////		System.out.println("Introducinedo la letra O");
////		int n = juego.intentaLetra('O');
////		
////		System.out.println("Ha sido usada " + n + " veces!");
////		
////		System.out.println("La frase es '" + juego + "'");
//		
//
//		System.out.println("Weeeeee");
//		
////		Debug palabra larga
//		int n = juego.intentaLetra('A');
//		n = juego.intentaLetra('E');
//		n = juego.intentaLetra('S');
////		n = juego.intentaLetra('T');
////		n = juego.intentaLetra('P');
////		n = juego.intentaLetra('D');
////		n = juego.intentaLetra('L');
////		n = juego.intentaLetra('U');
////		n = juego.intentaLetra('R');
////		n = juego.intentaLetra('G');
////		n = juego.intentaLetra('B');
////		n = juego.intentaLetra('C');
////		n = juego.intentaLetra('Q');
////		n = juego.intentaLetra('M');
////		n = juego.intentaLetra('I');
////		n = juego.intentaLetra('O');
//		
//		System.out.println(interfaz.show());
//		
//		juego.intentaLetra('E');
//		
//		System.out.println(interfaz.show());
//		
////		juego.intentaLetra('W');
////		juego.intentaLetra('W');
////		juego.intentaLetra('W');
////		juego.intentaLetra('W');
////		juego.intentaLetra('W');
////		juego.intentaLetra('W');
//		
//		System.out.println(interfaz.show());
//		
//		
//	}

}
