package fr.zelytra.daedalus.managers.languages;

import fr.zelytra.daedalus.Daedalus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;

public class LangFile {
	private final HashMap<String, String> text;

	public LangFile(Lang lang) {
		this.text = new HashMap<>();
		InputStream is = Daedalus.getInstance().getResource("lang/" + lang.getFileName());
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		try {
			String line = reader.readLine();

			while (line != null) {
				text.put(line.split("=")[0], StringUtils.substringBetween(line.split("=")[1], "\"", "\""));
				line = reader.readLine();
			}

		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
	}

	public HashMap<String, String> getText() {
		return text;
	}
}
