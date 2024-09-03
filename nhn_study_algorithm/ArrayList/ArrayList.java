import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    E[] list;
    int index;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        list = (E[]) new Object[10];
    }

    @Override
    public void add(E item) {
        list[index++] = item;
    }

    @Override
    public E get(int index) {
        return list[index];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public void remove(int index) {
        for (int i = index; i < size(); i++) {
            list[i] = list[i + 1];
        }
        list[this.index--] = null;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(list, null);
    }

    @Override
    public Iterator<E> iterator() {
        return Arrays.stream(this.list).filter(x -> x != null).iterator();
    }

}
