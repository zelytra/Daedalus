package fr.zelytra.daedalus.structure;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.regions.Region;
import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.structure.StructureEnum;
import fr.zelytra.daedalus.managers.structure.StructureType;
import org.bukkit.util.BlockVector;

import java.io.IOException;
import java.io.InputStream;

public class Structure {

    private String name;
    private StructureType type;
    private Clipboard clipboard;
    private Region region;
    private BlockVector origin;
    private BlockVector offset;

    public Structure(StructureEnum structure) {
        this.name = structure.getName();
        this.type = structure.getType();
        this.origin = structure.getOrigin();
        this.offset = structure.getOffset();

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

    public BlockVector getOrigin() {
        return origin;
    }

    public StructureType getType() {
        return type;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    public Region getRegion() {
        return region;
    }

    public BlockVector getOffset() {
        return offset;
    }
}
