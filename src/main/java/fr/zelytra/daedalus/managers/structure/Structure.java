package fr.zelytra.daedalus.managers.structure;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.regions.Region;
import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import java.io.IOException;
import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.BlockVector;

public class Structure {

	private final StructureEnum structure;

	@Getter
	private Clipboard clipboard;

	@Getter
	private Region region;

	@Setter
	private boolean firstEntrance = false;

	@Getter
	@Setter
	private Location bossSpawnLocation;

	public Structure(StructureEnum structure) {
		this.structure = structure;

		if (Bukkit.getServer().getPluginManager().getPlugin("FastAsyncWorldEdit") == null)
			return;

		InputStream is = Daedalus.getInstance().getResource("structures/" + this.structure.getName() + ".struct");
		BuiltInClipboardFormat format = BuiltInClipboardFormat.SPONGE_SCHEMATIC;
		try (ClipboardReader reader = format.getReader(is)) {
			this.clipboard = reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.region = this.clipboard.getRegion();
	}

	public boolean hasFirstEntrance() {
		return firstEntrance;
	}

	public String getName() {
		return this.structure.getName();
	}

	public BlockVector getOrigin() {
		return this.structure.getOrigin();
	}

	public StructureType getType() {
		return this.structure.getType();
	}

	public GodsEnum getGod() {
		return this.structure.getGod();
	}

	public BlockVector getOffset() {
		return this.structure.getOffset();
	}

	public boolean canBlock() {
		return this.structure.canBlock();
	}

	public int getID() {
		return this.structure.getId();
	}
}
