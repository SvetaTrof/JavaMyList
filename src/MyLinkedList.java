/**
 * Связанный динамический массив LinkedList
 */
public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {
    /**
     * Внутренний параметризированный класс из элементов, которого состоит список
     */
    private static class Node<T> {
        private T element;
        private Node<T> nextNode;
        private Node<T> prevNode;

        //Конструктор создания элемента с сылками на следующий и предыдущий элемент
        public Node(T element) {
            this.element = element;
            this.nextNode = null;
            this.prevNode = null;
        }
    }

    //Первый элемент
    private Node<T> firstNode;
    //Последний элемент
    private Node<T> lastNode;
    private int size;

    //Конструктор для пустого списка
    public MyLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        size = 0;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param element - добавляемый элемент
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if(lastNode == null) { // если последний узел равен null, то есть равен нулю
            firstNode = newNode; //новый узел становиться первым
            lastNode = newNode; //новый узел также становиться и последним
        } else {
            lastNode.nextNode = newNode; //если список не пуст, то текущий узел ссылается на новый
            lastNode = newNode; // новый узел становиться последним
        }
        size++;
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
        //Если индекс совпадает с количеством элементов в списке, то вызываем метод вставки в конец
        if(index == size) {
            add(element);
            return;
        }

        Node<T> newNode = new Node<>(element);
        if(index == 0) {
            newNode.nextNode = firstNode;
            if(firstNode != null) {
                firstNode.prevNode = newNode;
            }
            firstNode = newNode;
        } else {
            Node<T> current = getNode(index);
            newNode.nextNode = current;
            newNode.prevNode = current.prevNode;
            if(current.prevNode != null) {
                current.prevNode.nextNode = newNode;
            }
            current.prevNode = newNode;
        }
        size++;
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
        return getNode(index).element;
    }

    /**
     * Удаление элемента по индексу
     *
     * @param index - индекс удаляемого элемента
     */
    @Override
    public void delete(int index) {
        checkIndex(index); //проверка индекса
        Node<T> removeNode;
        if (index == 0) {
            removeNode = firstNode;
            firstNode = firstNode.nextNode;
            if(firstNode == null) {
                lastNode = null;
            }
        } else {
            Node<T> prev = getNode(index - 1);
            removeNode = prev.nextNode;
            prev.nextNode = removeNode.nextNode;
            if( removeNode == lastNode) {
                lastNode = prev;
            }
        }
        size--;
    }

    /**
     * Очистить весь лист
     */
    @Override
    public void clear() {
        Node<T> current = firstNode;
        while (current != null) {
            Node<T> next = current.nextNode;
            current.prevNode = null;
            current.nextNode = null;
            current.element = null;
            current = next;
        }
        lastNode = null;
        firstNode = null;
        size = 0;
    }

    /**
     * Сортировка листа пузырьком
     */
    @Override
    public void sort() {
        if(size <= 1) {
            return;
        }
        boolean flag;
        do {
            flag = false;
            Node<T> current = firstNode;
            while (current.nextNode != null) {
                if(((Comparable<T>) current.element).compareTo(current.nextNode.element) > 0) {
                    T y = current.element; //временная переменная, чтобы сохранить текущий элемент
                    current.element = current.nextNode.element;
                    current.nextNode.element = y;
                    flag =true;
                }
                current = current.nextNode;
            }
        } while (flag);
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

    /**
     * Возвращение узла по указанному индексу
     */
    private Node<T> getNode(int index) {
        checkIndex(index); //проверка индекса
        Node<T> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.nextNode;
        }
        return current;
    }

    //Вывод списка
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = firstNode;
        while (current != null) {
            sb.append(current.element);
            if(current.nextNode != null) {
                sb.append(", ");
            }
            current = current.nextNode;
        }
        sb.append("]");
        return sb.toString();
    }
}
