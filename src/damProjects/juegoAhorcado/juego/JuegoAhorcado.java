package damProjects.juegoAhorcado.juego;

public class JuegoAhorcado {
	
	private static final int VIDAS_INICIALES = 6;
	
	private int vidasRestantes;
	
	private String frase;
	private char[] descubierto;
	
	public JuegoAhorcado(String frase) {
		this.vidasRestantes = VIDAS_INICIALES; // 
		
		this.frase = frase.toUpperCase(); // Guarda la frase en mayúsculas
		
		// Crea una lista con los char ocultos
		this.descubierto = new char[frase.length()];
		for (int i = 0; i < frase.length(); i++) { // Para cada char de la frase
			if (frase.charAt(i) == ' ') { // Si es un espacio
				this.descubierto[i] = ' '; // Añade el espacio normal
			}
			else {
				this.descubierto[i] = '_'; // Oculta la información
			}
		}
	}
	
	public char[] getFrase() {
		return descubierto;
	}
	
	public int getVidasRestantes() {
		return vidasRestantes;
	}
	
	public String toString() {
		return new String(this.getFrase());
	}
	
	public int intentaLetra(char letra) {
		int vecesUsadas = 0; // Veces que la letra se ha usado en el programa
		for (int i = 0; i < frase.length(); i++) {
			if (letra == frase.charAt(i)) { // Si la letra se usa en esta posición
				descubierto[i] = letra; // Pon la letra en la frase descubierta
				vecesUsadas++;
			}
		}
		
		if (vecesUsadas == 0) {
			this.vidasRestantes--;
		}
		return vecesUsadas;
	}
}
