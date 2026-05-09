/**
 * Интерфейс с основными операциями по работе с листом
 *
 * @param <T> тип элементов в листе
 */
public interface MyList<T> {

    //Добавление элемента
    void add(T element);

    //Добавление элемента по индексу
    void add(int index, T element);

    //Получение элемента по индексу, возвращает элемент типа Е
    T get(int index);

    //Удалить элемент по индексу
    void delete(int index);

    //Очистить массив
    void clear();

    //Отсортировать массив
    void sort();
}
