package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */
public class MyArrayList extends List {

    private int capacity;
    private int[] array;
    final float factor = (float) (1.5);

    public MyArrayList() {
        size = 0;
        capacity = (int) (factor * size + 1);
        array = new int[capacity];
    }

    public MyArrayList(int capacity) {
        size = 0;
        this.capacity = capacity;
        array = new int[this.capacity];
    }

    @Override
    public void add(int item) {
        if (size < capacity) {
            array[size] = item;
            size += 1;
        } else {
            capacity *= factor;
            int [] array1 = new int[capacity];
            System.arraycopy(array,0, array1, 0, size);
            array = array1;
            array[size] = item;
            size += 1;
        }
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        if (idx < size) {
            int result = array[idx];
            size -= 1;
            System.arraycopy(array, idx + 1, array, idx, size - idx);
            return result;
        } else {
            System.out.println(new NoSuchElementException().toString());
            throw new NoSuchElementException();
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        if (idx < size) {
            return array[idx];
        } else {
            throw new NoSuchElementException();
        }
    }

    //@Override
    /*int size() {
        return 0;
    }
    */
}
