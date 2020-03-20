package sample.model;

public class Player {
    private String name;
    private String diff;
    private String grid;
    private String time;

    public Player(String name, String diff, String grid, String time) {
        this.name = name;
        this.diff = diff;
        this.grid = grid;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", diff='" + diff + '\'' +
                ", grid='" + grid + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
