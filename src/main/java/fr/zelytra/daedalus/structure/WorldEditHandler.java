package fr.zelytra.daedalus.structure;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldEditHandler {
    private  String structureName;
    private Player player;
    private  Location location;
    private  Clipboard clipboard;

    public WorldEditHandler(String structureName, Player player) {
        this.structureName = structureName;
        this.player = player;
    }

    public WorldEditHandler(Location location, Clipboard clipboard) {
        this.location = location;
        this.clipboard = clipboard;
    }

    public boolean saveStructure() {

        Region region;
        World world = BukkitAdapter.adapt(this.player.getLocation().getWorld());
        //Getting player selection
        try {
            region = getWorldEditRegion(this.player);
            if (region == null) {
                return false;

            }
        } catch (IncompleteRegionException e) {
            return false;

        }
        //Saving selection into clipboard
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
            ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
            Operations.complete(forwardExtentCopy);
        } catch (WorldEditException e) {
            return false;

        }

        File file = new File(Daedalus.getInstance().getDataFolder() + File.separator + this.structureName + ".struct");
        File folder = new File(Daedalus.getInstance().getDataFolder().toString());
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void pasteStructure() {
        World world = BukkitAdapter.adapt(this.location.getWorld());
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
            Operation operation = new ClipboardHolder(this.clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(this.location.getX(), this.location.getY(), this.location.getZ()))
                    // configure here
                    .build();
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }

    }

    private Region getWorldEditRegion(Player p) throws IncompleteRegionException {
        World world = BukkitAdapter.adapt(p.getLocation().getWorld());
        com.sk89q.worldedit.entity.Player WEp = BukkitAdapter.adapt(p);
        Region region = null;
        try {
            region = WorldEdit.getInstance().getSessionManager().findByName(WEp.getSessionKey().getName()).getSelection(world);
        } catch (IncompleteRegionException e) {

        }
        return region;
    }

    public String getStructureName() {
        return this.structureName;
    }

    public Region getSelection() throws IncompleteRegionException {
        return getWorldEditRegion(this.player);
    }
}

