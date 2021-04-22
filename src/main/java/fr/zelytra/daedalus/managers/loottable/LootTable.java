package fr.zelytra.daedalus.managers.loottable;

import org.bukkit.Material;

import java.util.ArrayList;

public class LootTable {
    private ArrayList<LootsEnum> loots;
    private ArrayList<Material> containerWhiteList;
    private String name;

    public LootTable(String name) {
        this.name = name;
        this.loots = new ArrayList<>();
        this.containerWhiteList = new ArrayList<>();
    }

    public ArrayList<LootsEnum> getLoots() {
        return loots;
    }

    public ArrayList<Material> getContainerWhiteList() {
        return containerWhiteList;
    }

    public String getName() {
        return name;
    }

    public void addContainerWhiteList(Material material) {
        this.containerWhiteList.add(material);
    }

    public void addLoot(LootsEnum loot) {
        this.loots.add(loot);
    }
}
