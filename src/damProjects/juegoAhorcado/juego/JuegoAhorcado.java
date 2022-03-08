package damProjects.juegoAhorcado.juego;

import damProjects.juegoAhorcado.procesarTexto.Desacentuar;

public class JuegoAhorcado {
	
	private static final int VIDAS_INICIALES = 6;
	public static final int CHAR_NO_VALID = -1;
	public static final int CHAR_YA_USADO = -2;
	
	private int vidasRestantes;
	
	private String frase;
	private char[] descubierto;
	private char[] caracteres;
	
	public JuegoAhorcado(String frase) {
		this.vidasRestantes = VIDAS_INICIALES; // 
		
		this.frase = frase.toUpperCase(); // Guarda la frase en mayúsculas
		this.frase = Desacentuar.unaccent(this.frase); // Desacentua la frase
		
		// Crea una lista con los char ocultos
		this.descubierto = new char[this.frase.length()];
		for (int i = 0; i < this.frase.length(); i++) { // Para cada char de la frase
			if (esOcultable(this.frase.charAt(i)))
				this.descubierto[i] = '_'; // Oculta la información
			else
				this.descubierto[i] = this.frase.charAt(i);
		}
		
		// Inicializa el array de chars descubiertos:
		this.caracteres = new char[27];
		for (int i = 0; i < 27; i++) {
			this.caracteres[i] = ' ';
		}
	}
	
	public char[] getFrase() {
		return descubierto;
	}
	
	public String getRealFrase() {
		return frase;
	}
	
	public char[] getCaracteres() {
		return caracteres;
	}
	
	public boolean terminado() {
		if (this.getVidasRestantes() == 0)
			return true;
		for (int i = 0; i < descubierto.length; i++) {
			if (esOcultable(frase.charAt(i)) && descubierto[i] != frase.charAt(i))
				return false;
		}
		return true;
	}

	public int getVidasRestantes() {
		return vidasRestantes;
	}
	
	public String toString() {
		return new String(this.getFrase());
	}
	
	private boolean esOcultable(char c) {
		return getIndiceLetra(c) != CHAR_NO_VALID;
	}
	
	private int getIndiceLetra(char letra) {
		if (letra >= 'A' && letra <= 'Z')
			return (int) (letra - 'A');
		if (letra == 'Ñ')
			return 26;
		return CHAR_NO_VALID;
	}
	
	public int intentaLetra(char letra) {
		letra = Character.toUpperCase(letra);
		int indiceLetra = getIndiceLetra(letra);
		
		if (indiceLetra == CHAR_NO_VALID)
			return CHAR_NO_VALID;
		if (this.caracteres[indiceLetra] != ' ')
			return CHAR_YA_USADO;
		
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
