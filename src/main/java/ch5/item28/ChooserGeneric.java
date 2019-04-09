package ch5.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChooserGeneric<T> {
    private final T[] choiceArray;

    public ChooserGeneric(Collection<T> choices) {
        choiceArray = (T[]) choices.toArray();
    }

    public T choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}