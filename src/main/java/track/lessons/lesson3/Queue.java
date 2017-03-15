package track.lessons.lesson3;

import java.util.NoSuchElementException;

// Очередь - структура данных, удовлетворяющая правилу First IN First OUT
public interface Queue {

    void enqueue(int value); // поместить элемент в очередь

    int dequeu() throws NoSuchElementException; // вытащить первый элемент из очереди
}