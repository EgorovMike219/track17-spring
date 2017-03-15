package track.lessons.lesson3;

import java.util.NoSuchElementException;

// Стек - структура данных, удовлетворяющая правилу Last IN First OUT
public interface Stack {
    void push(int value); // положить значение наверх стека
    int pop() throws NoSuchElementException; // вытащить верхнее значение со стека
}