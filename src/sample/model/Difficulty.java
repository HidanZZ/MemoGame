package sample.model;

public enum Difficulty{
    EASY(0),
    normal(1),
    hard(2);

    private int diff;
    private Difficulty(int diff){
        this.diff=diff;
    }

    public int getDiff() {
        return diff;
    }
}
