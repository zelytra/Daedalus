package fr.zelytra.daedalus.structure;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.regions.Region;
import fr.zelytra.daedalus.Daedalus;

import java.io.*;

public class Structure {

    private String name;
    private String type;
    private Clipboard clipboard;
    private Region region;

    public Structure(StructureEnum structure) {
        this.name = structure.getName();
        this.type = structure.getType();

        InputStream is = Daedalus.getInstance().getResource(this.name + ".struct");
        BuiltInClipboardFormat format = BuiltInClipboardFormat.SPONGE_SCHEMATIC;
        try (ClipboardReader reader = format.getReader(is)) {
            this.clipboard = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.region = this.clipboard.getRegion();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    public Region getRegion() {
        return region;
    }
}
