package pl.kamil.wyniki_strzeleckie.model;

public enum StartType {
    PSP("Pistolet sportowy"),
    PCZ("Pistolet centralnego zapłonu"),
    KSP("Karabin"),
    STRZ("Strzelba");

    private String name;
    StartType(String name){
        this.name = name;
    }
}

