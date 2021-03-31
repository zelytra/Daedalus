package fr.zelytra.daedalus.structure;

import com.sk89q.worldedit.*;
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
import com.sk89q.worldedit.world.block.BlockType;
import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldEditHandler {
    private String structureName;
    private Player player;
    private Location location;
    private Clipboard clipboard;
    private EditSession editSession;

    public WorldEditHandler(String structureName, Player player) {
        this.structureName = structureName;
        this.player = player;
    }

    public WorldEditHandler(Location location, Clipboard clipboard) {
        this.location = location;
        this.clipboard = clipboard;
    }

    public WorldEditHandler(org.bukkit.World w) {
        World world = BukkitAdapter.adapt(w);
        this.editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);
    }

    public boolean saveStructure() {

        Region region;
        World world = BukkitAdapter.adapt(this.player.getLocation().getWorld());
        //Getting player selection
        region = getWorldEditRegion(this.player);
        if (region == null) {
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
        if (!folder.exists()) folder.mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try (ClipboardWriter writer = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard);
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
                    .build();
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }


    }

    private Region getWorldEditRegion(Player p) {
        World world = BukkitAdapter.adapt(p.getLocation().getWorld());
        com.sk89q.worldedit.entity.Player WEp = BukkitAdapter.adapt(p);
        Region region = null;
        try {
            region = WorldEdit.getInstance().getSessionManager().findByName(WEp.getSessionKey().getName()).getSelection(world);
        } catch (IncompleteRegionException e) {

        }
        return region;
    }

    public void setBlock(Location location, BlockType material) {
        try {
            this.editSession.setBlock(BlockVector3.at(location.getX(), location.getY(), location.getZ()), material.getDefaultState());
        } catch (MaxChangedBlocksException e) {
            e.printStackTrace();
        }


    }

    public EditSession getEditSession(){
        return this.editSession;
    }

    public String getStructureName() {
        return this.structureName;
    }

    public Region getSelection() {
        return getWorldEditRegion(this.player);
    }
}

