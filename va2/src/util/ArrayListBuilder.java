package util;

import java.util.ArrayList;

public class ArrayListBuilder<E> {
    ArrayList<E> list;

    private ArrayListBuilder(){
        list = new ArrayList<>();
    }
    public static ArrayListBuilder initialize() {
        return new ArrayListBuilder();
    }
    public ArrayListBuilder addElement(E element) {
        list.add(element);
        return this;
    }
    public ArrayList build() {
        return list;
    }
}
