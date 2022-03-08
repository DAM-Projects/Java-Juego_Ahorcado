package damProjects.juegoAhorcado.interfaz;

import java.util.Scanner;

import damProjects.juegoAhorcado.juego.JuegoAhorcado;

/**
 * Clase con la lógica para representar las características del juego del ahorcado.
 * 
 * <pre> {@code}
 * ┌──────────────────────────┐
 * │ ▄▄▄▄▄   ┌───────────────┐│
 * │ ▐   O   │ A B C D E F G ││
 * │ ▐  ╚╬╝  │ H I J K L M N ││
 * │ ▐  ╔╩╗  │ Ñ O P Q R S T ││
 * │ ▐       │ U V W X Y Z   ││
 * │ ▀▀▀▀▀▀  └───────────────┘│
 * │ ...                      │
 * └──────────────────────────┘
 * </pre>
 * 
 * @author "Jkutkut -- Jorge Re"
 *
 */
public class JuegoAhorcadoInterfaz {
	
	/**
	 * <p>Longitud máxima de chars que caben en una sóla línea.</p>
	 * <p>Usado para colocar la frase.</p>
	 */
	private static final int WIDTH = 24;
	
	/**
	 * <p>El juego a representar</p>
	 */
	private JuegoAhorcado game;
	
	/**
	 * Scanner usado para preguntar al usuario.
	 */
	Scanner sc;
	
	
	/* *********** CONSTRUCTORES ************* */
	
	/**
	 * Constructor que toma el juego ya creado para comenzar.
	 * 
	 * @param game - Juego ya creado.
	 */
	public JuegoAhorcadoInterfaz(JuegoAhorcado game, Scanner sc) {
		this.game = game;
		this.sc = sc;
	}
	
	/**
	 * Constructor que toma la frase deseada, crea el juego y lo usa para representarlo.
	 * @param frase - String con la frase deseada.
	 */
	public JuegoAhorcadoInterfaz(String frase, Scanner sc) {
		this.game = new JuegoAhorcado(frase);
		this.sc = sc;
	}
	
	public void show() {
		System.out.println(this.toString());
	}
	
	/* *********** GETTERS ************* */
	/**
	 * @return String con el estado del juego.
	 */
	public String toString() {
		String[] body = this.getBody();
		String[] discovered = this.getDiscovered();
		String[] msg = game.toString().split(" ");
		
		String str = 
			"┌──────────────────────────┐\n" +
			"│ ▄▄▄▄▄   ┌───────────────┐│\n" +
			"│ ▐  " + body[0] + "  │ " + discovered[0] + " ││\n" +
			"│ ▐  " + body[1] + "  │ " + discovered[1] + " ││\n" +
			"│ ▐  " + body[2] + "  │ " + discovered[2] + " ││\n" +
			"│ ▐       │ "             + discovered[3] + " ││\n" +
			"│ ▀▀▀▀▀▀  └───────────────┘│\n";
		
		// Procesar la frase para mostrarlo debajo
		String m;
		for (int i = 0; i < msg.length; i++) {
			if (msg[i].length() > WIDTH) { // Si una palabra es muy larga
				str += "│ " + msg[i].substring(0, WIDTH) + " │\n";
				msg[i] = msg[i].substring(WIDTH);
				i--; // Para que la vuelva a usar en la siguiente iteración del bucle
			}
			else {
				m = msg[i++];
				for (; m.length() < WIDTH && i < msg.length; i++) {
					if (m.length() + msg[i].length() + 1 > WIDTH) { // Si la siguiente palabra no cabe
						i--; // Para que la vuelva a usar en la siguiente iteración del bucle
						break;
					}
					else {
						m += " " + msg[i];
					}
				}
				while (m.length() < WIDTH) { // Hasta que no tenga la longitud exacta
					m += " "; // Rellena con espacios
				}
				str += "│ " + m + " │\n";
			}
		}
		
		str += "└──────────────────────────┘"; // Termina cerrando la interfaz
		
		return str;
	}
	
	private String[] getDiscovered() {
		char[] chars = game.getCaracteres();
		
		String[] discovered = new String[4];
		
		for (int i = 0, j; i < 4; i++) {
			discovered[i] = "" + chars[i * 7];
			for (j = 1; j < 7; j++) {
				discovered[i] += " " + chars[i * 7 + j];
			}
			
		}
		
		return discovered; 
	}

	public void pideLetra() {
		int status = JuegoAhorcado.CHAR_NO_VALID;
		String str;
		char c;
				
		while (status < 0) {
			System.out.print("-> ");
			str = sc.nextLine();
			if (str.length() != 1) {
				System.out.println("Tienes que introducir una sola letra.");
				continue;
			}

			c = str.charAt(0);
			
			status = game.intentaLetra(c);
			if (status == JuegoAhorcado.CHAR_NO_VALID)
				System.out.println("El caracter introducido no es válido");
			else if (status == JuegoAhorcado.CHAR_YA_USADO)
				System.out.println("Ya has usado esta letra :S");
		}
		System.out.println("Esta letra se usa un total de " + status + " veces.");
	}
	
	/**
	 * Retorna un array con cada línea del cuerpo como un String.
	 * @return La forma del personaje basada en las vidas restantes del juego.
	 */
	private String[] getBody() {
		char[][] body = {
			{' ', 'O', ' '}, //  0
			{'╚', '╬', '╝'}, // ╚╬╝
			{'╔', '╩', '╗'}, // ╔╩╗
		};
		
		switch (game.getVidasRestantes()) {
		case 6:
			body[0][1] = ' '; // 0
		case 5:
			body[1][1] = ' '; // ╬
			body[2][1] = ' '; // ╩
		case 4:
			body[1][0] = ' '; // ╚
		case 3:
			body[1][2] = ' '; // ╝
		case 2:
			body[2][0] = ' '; // ╔
		case 1:
			body[2][2] = ' '; // ╗
			break;
		}
		
		String[] str = new String[body.length];
		for (int i = 0; i < body.length; i++) {
			str[i] = new String(body[i]);
		}
		return str;
	}
}
