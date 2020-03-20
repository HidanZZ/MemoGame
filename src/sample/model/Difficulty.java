package sample.model;

public enum Difficulty{
    EASY(5),
    normal(3),
    hard(1);

    private int diff;
    private Difficulty(int diff){
        this.diff=diff;
    }

    public int getDiff() {
        return diff;
    }
}
