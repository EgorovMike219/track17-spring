package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

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

    private Node begin;
    private Node end;

    public MyLinkedList() {
        size = 0;
        begin = end = new Node(null, null, 0);
    }

    @Override
    public void add(int item) {
        Node elem = new Node(end, null, item);
        end.next = elem;
        end = elem;
        size += 1;
    }

    @Override
    public int remove(int idx) throws NoSuchElementException {
        if (idx < size) {
            if (idx == 0) {
                size -= 1;
                int result = begin.val;
                begin.next.prev = null;
                begin = begin.next;
                return result;
            } else {
                if (idx == size - 1) {
                    size -= 1;
                    int result = end.val;
                    end.prev.next = null;
                    end = end.prev;
                    return result;
                } else {
                    Node elem = begin;
                    for (int i = 0; i < idx; i += 1) {
                        elem = elem.next;
                    }
                    size -= 1;
                    int result = elem.val;
                    elem.prev.next = elem.next;
                    elem.next.prev = elem.prev;
                    return result;
                }
            }
        } else {
            System.out.println(new NoSuchElementException().toString());
            throw new NoSuchElementException();
        }
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        if (idx < size) {
            Node elem = begin;
            for (int i = 0; i < idx; i += 1) {
                elem = elem.next;
            }
            return elem.val;
        } else {
            throw new NoSuchElementException();
        }
    }

    //@Override
    //public int size() {
     //   return 0;
    //}

    @Override
    public void push(int value) {  // положить значение наверх стека
        Node elem = new Node(end, null, value);
        end.next = elem;
        end = elem;
        size += 1;
    }

    @Override
    public int pop() throws NoSuchElementException {  // вытащить верхнее значение со стека
        if (size > 0) {
            size -= 1;
            int result = end.val;
            end.prev.next = null;
            end = end.prev;
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void enqueue(int value) {  // поместить элемент в очередь
        Node elem = new Node(null, begin, value);
        end.next = elem;
        end = elem;
        size += 1;
    }

    @Override
    public int dequeu() throws NoSuchElementException {
        if (size > 0) {
            size -= 1;
            int result = end.val;
            end.prev.next = null;
            end = end.prev;
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }
}
