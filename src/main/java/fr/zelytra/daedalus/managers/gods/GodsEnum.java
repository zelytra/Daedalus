package fr.zelytra.daedalus.managers.gods;

public enum GodsEnum {

    ZEUS("Zeus", false),
    POSEIDON("Poseidon", false),
    HADES("Hades", false),
    ARES("Ares", false),
    APHRODITE("Aphrodite", false),
    DEMETER("Demeter", false),
    HERMES("Hermes", false),
    ARTEMIS("Artemis", false),
    ATHENA("Athena", false),
    DIONYSUS("Dionysus", false);

    private String name;
    private boolean selected;

    GodsEnum(String name, boolean selected){
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
