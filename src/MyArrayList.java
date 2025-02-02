import java.util.Arrays;

/**
 * Простой динамический массив ArrayList, реализует интерфейс MyList
 *
 * @param <T> тип элемента
 */
public class MyArrayList<T> implements MyList<T>{
    //Принимаемый массив любого ссылочного типа
    private T[] list;
    // Количество элементов
    private int size;
    // Размер массива по умолчанию
    private static final int DEFAULSIZE = 10;

    //Конструктор, если не задается размер листа, а берется дефолтное значение defaultSize
    public MyArrayList() {
        list = (T[]) new Object[DEFAULSIZE];
    }

    /**
     * Добавление элемента в конец списка
     * @param element - добавляемый элемент
     */
    @Override
    public void add(T element) {
        checkSize();
        list[size++] = element;
    }

    /**
     * Добавление элемента по индексу - добавляет элемент на указанную позицию
     *
     * @param index - индекс, куда нужно вставить элемент
     * @param element - элемент, который нужно вставить
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index); //проверка индекса
        checkSize();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1]; //сдвигаем элементы вправо
        }
        list[index] = element; //вставляем элемент
        size++; // увеличиваем размер на 1
    }

    /**
     * Получение элемента по индексу
     *
     * @param index - индекс элемента, который нужно получить
     * @return - возвращается элемент
     */
    @Override
    public T get(int index) {
        checkIndex(index); //проверка индекса
        return list[index];
    }

    /**
     * Удаление элемента по индексу
     *
     * @param index - индекс удаляемого элемента
     */
    @Override
    public void delete(int index) {
        checkIndex(index); //проверка индекса
        for(int i = index; i < size; i++) {
            list[i] = list[i+1];
        };
        list[--size] = null;
    }

    /**
     * Очистить весь лист
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        };
        size = 0;
    }

    /**
     * Сортировка листа
     */
    @Override
    public void sort() {
        Arrays.sort((T[]) list, 0, size);
    }

    /**
     * Проверка размера массива, если массив полон и новое значение не влезает,
     * то массив перезаписывается в новый, где размер увеличен в 2 раза
     */
    private void checkSize() {
        if(size == list.length) {
            list = Arrays.copyOf(list, size*2);
        }
    }

    /**
     * Проверка не является ли индекс отрицательным и не выходит ли за пределы массива
     *
     * @param index - индекс элемента
     */
    private void checkIndex(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //Вывод списка
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

}
