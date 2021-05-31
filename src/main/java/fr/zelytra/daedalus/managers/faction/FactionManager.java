package fr.zelytra.daedalus.managers.faction;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FactionManager {

    private final List<Faction> factionList = new ArrayList<>();

    public FactionManager() {
        for (FactionsEnum faction : FactionsEnum.values()) {
            factionList.add(new Faction(faction));
        }
    }

    public List<Faction> getFactionList() {
        return factionList;
    }

    public Faction getFactionOf(Player player){
        for (Faction faction: factionList) {
            if (faction.has(player))
                return faction;
        }
        return null;
    }

    public Faction getFactionOf(FactionsEnum factionsEnum){
        for (Faction faction: factionList) {
            if (faction.is(factionsEnum))
                return faction;
        }
        return null;
    }

}
