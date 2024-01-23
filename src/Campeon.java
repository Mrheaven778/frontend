public class Campeon {
    private String id;
    private String name;
    private String role;
    private String lane;
    private String attackType;
    private int difficulty;
    private int releaseYear;
    private String lore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public Campeon(String id, String name, String role, String lane, String attackType, int difficulty, int releaseYear, String lore) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.lane = lane;
        this.attackType = attackType;
        this.difficulty = difficulty;
        this.releaseYear = releaseYear;
        this.lore = lore;
    }
}
