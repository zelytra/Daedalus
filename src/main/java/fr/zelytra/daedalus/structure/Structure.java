package fr.zelytra.daedalus.structure;

import fr.zelytra.daedalus.Daedalus;

import java.io.File;

public class Structure {
    private final File schamtic;

    public Structure(String schamticName) {
        this.schamtic = new File(Daedalus.plugin.getDataFolder() + File.separator + schamticName+".schem");
    }
}
