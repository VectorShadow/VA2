package io.in;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class InputCommandList implements Iterable<InputCommand>{
    ArrayList<InputCommand> list = new ArrayList<>();

    public int test(KeyEvent ke) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).test(ke))
                return i;
        }
        return -1;
    }

    public void add(InputCommand ic) {
        list.add(ic);
    }

    @Override
    public Iterator<InputCommand> iterator() {
        return list.iterator();
    }
}
