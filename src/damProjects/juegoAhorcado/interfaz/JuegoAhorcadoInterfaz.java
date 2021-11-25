package damProjects.juegoAhorcado.interfaz;

import damProjects.juegoAhorcado.juego.JuegoAhorcado;

public class JuegoAhorcadoInterfaz {

	/*
	 *  ┌──────────────────────────┐
	 * 	│ ▄▄▄▄▄   ┌───────────────┐│
	 *  │ ▐   O   │	A B C D E F G ││
	 *  │ ▐  ╚╬╝  │ H I J K L M N ││
	 *  │ ▐  ╔╩╗  │ Ñ O P Q R S T ││
	 *  │ ▐       │ U V W X Y Z   ││
	 * 	│ ▀▀▀▀▀▀  └───────────────┘│
	 * 	│ ...                      │
	 * 	│ ...                      │
	 * 	└──────────────────────────┘
	 */
	
	private static final int WIDTH = 24;
	
	private JuegoAhorcado game;
	
	public JuegoAhorcadoInterfaz(JuegoAhorcado game) {
		this.game = game;
	}
	
	public String show() {
		String[] body = this.getBody();
		String[] discovered = {
			"A B C D E F G",
			"A B C D E F G",
			"A B C D E F G",
			"A B C D E F"	
		};
		String[] msg = game.toString().split(" ");
		
		String str = 
			"┌──────────────────────────┐\n" +
			"│ ▄▄▄▄▄   ┌───────────────┐│\n" +
			"│ ▐  " + body[0] + "  │ " + discovered[0] + " ││\n" +
			"│ ▐  " + body[1] + "  │ " + discovered[1] + " ││\n" +
			"│ ▐  " + body[2] + "  │ " + discovered[2] + " ││\n" +
			"│ ▐       │ " + discovered[3] + "   ││\n" +
			"│ ▀▀▀▀▀▀  └───────────────┘│\n";
		
		int current = 0;
		int cWidth, j;
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
