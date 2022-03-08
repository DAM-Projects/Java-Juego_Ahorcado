package damProjects.juegoAhorcado.juego;

import damProjects.juegoAhorcado.procesarTexto.Desacentuar;

public class JuegoAhorcado {
	
	/**
	 * Vidas que tiene un jugador al inicio.
	 */
	private static final int VIDAS_INICIALES = 6;
	
	/**
	 * Código de error en intentaLetra cuando el caracter introducido no es válido.
	 */
	public static final int CHAR_NO_VALID = -1;
	
	/**
	 * Código de error en intentaLetra cuando el caracter introducido ya se ha usado.
	 */
	public static final int CHAR_YA_USADO = -2;
	
	/**
	 * Vidas restantes del jugador.
	 */
	private int vidasRestantes;
	
	/**
	 * Frase solución.
	 */
	private String frase;
	
	/**
	 * Secuencia de chars de la frase que ya hemos descubierto.
	 */
	private char[] descubierto;
	
	/**
	 * Secuencia de chars que nos indica si hemos (o no) usado una letra.
	 */
	private char[] caracteres;
	
	/**
	 * Inicializa la clase con la frase a usar como parámetro.
	 * @param frase
	 */
	public JuegoAhorcado(String frase) {
		this.vidasRestantes = VIDAS_INICIALES; // inicia vidas
		
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
		this.caracteres = new char[28];
		for (int i = 0; i < this.caracteres.length; i++) {
			this.caracteres[i] = ' ';
		}
	}
	
	// GETTERS
	
	/**
	 * Frase descubierta por el momento.
	 * @return frase descubierta como char[].
	 */
	public char[] getFrase() {
		return descubierto;
	}
	
	/**
	 * @return Frase original.
	 */
	public String getRealFrase() {
		return frase;
	}
	
	/**
	 * @return Char[] con los caracteres usados por el momento.
	 */
	public char[] getCaracteres() {
		return caracteres;
	}
	
	/**
	 * Si no quedan vidas o hemos resuelto la frase, verdadero.
	 * @return Si el juego ha terminado o no.
	 */
	public boolean terminado() {
		if (this.getVidasRestantes() == 0)
			return true;
		for (int i = 0; i < descubierto.length; i++) {
			if (esOcultable(frase.charAt(i)) && descubierto[i] != frase.charAt(i))
				return false;
		}
		return true;
	}

	/**
	 * @return Número de vidas restantes.
	 */
	public int getVidasRestantes() {
		return vidasRestantes;
	}
	
	public String toString() {
		return new String(this.getFrase());
	}
	
	/**
	 * Si el char dado necesita ser ocultado al inicio del juego
	 * @param c char a verificar
	 * @return Si hay que ocultarlo o no.
	 */
	private static boolean esOcultable(char c) {
		return getIndiceLetra(c) != CHAR_NO_VALID;
	}
	
	/**
	 * El índice del array donde se almacena la letra dada. Si no es válido, devuelve CHAR_NO_VALID
	 * @param letra char a analizar.
	 * @return El valor del array o CHAR_NO_VALID.
	 */
	private static int getIndiceLetra(char letra) {
		if (letra >= 'A' && letra <= 'Z')
			return (int) (letra - 'A');
		if (letra == 'Ñ')
			return 26;
		return CHAR_NO_VALID;
	}
	
	/**
	 * Intenta usar la letra dada y devolver el número de veces usado.
	 * 
	 * Si ya ha sido usada o no es válida, el valor retornado es un código de error.
	 * 
	 * @param letra char a usar.
	 * @return El número de veces usadas o CHAR_NO_VALID o CHAR_YA_USADO.
	 */
	public int intentaLetra(char letra) {
		letra = Character.toUpperCase(letra);
		int indiceLetra = getIndiceLetra(letra);
		
		if (indiceLetra == CHAR_NO_VALID)
			return CHAR_NO_VALID;
		if (this.caracteres[indiceLetra] != ' ')
			return CHAR_YA_USADO;
		
		int vecesUsadas = 0; // Veces que la letra se ha usado en la frase
		for (int i = 0; i < frase.length(); i++) {
			if (letra == frase.charAt(i)) { // Si la letra se usa en esta posición
				descubierto[i] = letra; // Pon la letra en la frase descubierta
				vecesUsadas++;
			}
		}
		
		this.caracteres[indiceLetra] = letra; // Marca en la lista de caracteres la letra usada
		
		if (vecesUsadas == 0) { // Si no hemos descubierto ninguna letra
			this.vidasRestantes--;
		}
		return vecesUsadas;
	}
}
