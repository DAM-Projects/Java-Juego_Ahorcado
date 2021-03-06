package damProjects.juegoAhorcado.diccionario;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Diccionario {
	private List<String> words;
	private Random r;
	
	public Diccionario(String filename) throws IOException {
		words = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		r = new Random();
	}
	
	public String getWord() {
		return words.get(r.nextInt(words.size()));
	}
}
