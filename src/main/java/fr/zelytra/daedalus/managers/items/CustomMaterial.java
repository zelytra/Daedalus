package fr.zelytra.daedalus.managers.items;

import org.bukkit.Material;

public enum CustomMaterial {
    ZEUS_LIGHTNING("§e§lZeus's Lightning","zeus_lightning", 1, Material.STICK);

    private String displayName;
    private String name;
    private int customModelData;
    private Material vanillaMaterial;

    CustomMaterial(String displayName,String name, int CMD, Material material) {
        this.displayName = displayName;
        this.name = name;
        this.customModelData = CMD;
        this.vanillaMaterial = material;
    }
    CustomMaterial(String displayName,String name,String description, int CMD, Material material) {
        this.displayName = displayName;
        this.name = name;
        this.customModelData = CMD;
        this.vanillaMaterial = material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public Material getVanillaMaterial() {
        return vanillaMaterial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public static CustomMaterial getByName(String name){
        for(CustomMaterial material : CustomMaterial.values()){
            if(material.getName().equalsIgnoreCase(name)){
                return material;
            }
        }
        return null;
    }

}
