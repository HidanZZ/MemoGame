package sample.model;

public enum Grid {
    small(3),
    average(4),
    big(6);

    private int grid;
    private Grid(int grid){
        this.grid=grid;
    }

    public int getGrid() {
        return grid;
    }
}
