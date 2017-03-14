package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack {

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    @Override
    public void add(int item) {
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        return 0;
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void push(int value) {

    } // положить значение наверх стека

    @Override
    public int pop() {
        return 1;
    } // вытащить верхнее значение со стека
}
