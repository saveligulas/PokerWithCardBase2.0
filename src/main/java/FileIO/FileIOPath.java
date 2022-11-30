package FileIO;

public enum FileIOPath {
    PLAYER("src/main/resources/PlayerSheet.csv"),
    TABLE("src/main/resources/TableSheet.csv"),
    CASINO("src/main/resources/CasinoDataSheet.csv");

    private final String PATH;

    FileIOPath(String PATH){
        this.PATH = PATH;
    }

    public String getPATH() {
        return PATH;
    }
}
