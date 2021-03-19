package fr.zelytra.daedalus.managers.gods;

public enum GodsEnum {

    ZEUS("Zeus"),
    POSEIDON("Poseidon"),
    HADES("Hades"),
    ARES("Ares"),
    APHRODITE("Aphrodite"),
    DEMETER("Demeter"),
    HERMES("Hermes"),
    ARTEMIS("Artemis"),
    ATHENA("Athena"),
    DIONYSUS("Dionysus");

    private String name;

    GodsEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
