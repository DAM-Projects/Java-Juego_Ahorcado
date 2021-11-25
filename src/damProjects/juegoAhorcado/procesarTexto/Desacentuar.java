package damProjects.juegoAhorcado.procesarTexto;

import java.text.Normalizer;

/**
 * Code from https://gist.github.com/rponte/893494
 * 
 * @author "rponte"
 *
 */
public class Desacentuar {
	/**
	 * Remove toda a acentuação da string substituindo por caracteres simples sem acento.
	 */
	public static String unaccent(String src) {
		return Normalizer
				.normalize(src, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}
}
