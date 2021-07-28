package fr.zelytra.daedalus.managers.languages;

import java.util.HashMap;

public enum Lang {
    FR("fr.conf"),
    EN("en.conf");

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
            return "§cERROR no text found for this lang";
    }
}
