package fr.zelytra.daedalus.managers.languages;

import java.util.HashMap;

public enum Lang {

    EN("en.conf"),
    FR("fr.conf"),
    ES("es.conf"),
    DE("de.conf");

    private final String fileName;
    private final HashMap<String, String> text;

    Lang(String fileName) {
        this.fileName = fileName;
        this.text = new LangFile(this).getText();
    }

    public String getFileName() {
        return fileName;
    }

    public String textOf(String tag) {
        if (text.containsKey(tag)) {
            return text.get(tag);
        } else
            return "§c404 text not found";
    }
}
