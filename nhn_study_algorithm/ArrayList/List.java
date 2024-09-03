public interface List<E> extends Iterable<E>{
    
    void add(E item);

    E get(int index);

    int size();

    void remove(int index);

    boolean isEmpty();

    void clear();
}
