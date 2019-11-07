package it.lparolari.unipd.ml.slp;

public class Pair<T, U> {
    private T t;
    private U u;

    private Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static <T,U> Pair<T,U> of(T t, U u) {
        return new Pair<>(t, u);
    }

    public T fst() {
        return t;
    }

    public U snd() {
        return u;
    }
}
