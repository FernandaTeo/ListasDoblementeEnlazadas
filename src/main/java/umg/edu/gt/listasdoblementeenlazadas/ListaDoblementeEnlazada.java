package umg.edu.gt.listasdoblementeenlazadas;

public class ListaDoblementeEnlazada {
    // Declaración de variables de instancia para el primer y último nodo de la lista
    private NodoDoble primero;
    private NodoDoble ultimo;

    // Constructor de la clase ListaDoblementeEnlazada
    public ListaDoblementeEnlazada() {
        // Inicialización de los punteros primero y ultimo a null ya que la lista está vacía al inicio
        this.primero = null;
        this.ultimo = null;
    }

    // Método que verifica si la lista está vacía
    public boolean estaVacia() {
        return primero == null;
    }

    // Método para agregar un nuevo nodo al inicio de la lista
    public void agregarAlInicio(Object dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            // Si la lista está vacía, el nuevo nodo se convierte en el primero y el último
            primero = ultimo = nuevo;
        } else {
            // Si la lista no está vacía, se enlaza el nuevo nodo al primer nodo actual
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo; // El nuevo nodo ahora es el primero
        }
    }

    // Método para agregar un nuevo nodo al final de la lista
    public void agregarAlFinal(Object dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            // Si la lista está vacía, el nuevo nodo se convierte en el primero y el último
            primero = ultimo = nuevo;
        } else {
            // Si la lista no está vacía, se enlaza el nuevo nodo al último nodo actual
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo; // El nuevo nodo ahora es el último
        }
    }

    // Método para eliminar el primer nodo de la lista
    public void eliminarAlInicio() {
        if (!estaVacia()) {
            if (primero == ultimo) {
                // Si solo hay un nodo en la lista, se establece primero y ultimo a null
                primero = ultimo = null;
            } else {
                // Si hay más de un nodo, se elimina el primer nodo y se actualiza el puntero primero
                primero = primero.getSiguiente();
                primero.setAnterior(null);
            }
        }
    }

    // Método para eliminar el último nodo de la lista
    public void eliminarAlFinal() {
        if (!estaVacia()) {
            if (primero == ultimo) {
                // Si solo hay un nodo en la lista, se establece primero y ultimo a null
                primero = ultimo = null;
            } else {
                // Si hay más de un nodo, se elimina el último nodo y se actualiza el puntero ultimo
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(null);
            }
        }
    }

    // Método para recorrer e imprimir los elementos de la lista
    public void recorrerLista() {
        NodoDoble actual = primero;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    // Método para buscar un nodo con un dato específico en la lista
    public void buscarNodo(Object dato) {
        NodoDoble actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                System.out.println("El dato " + dato + " se encuentra en la lista");
                return;
            }
            actual = actual.getSiguiente();
        }
        System.out.println("El dato " + dato + " no se encuentra en la lista");
    }

    // Método para insertar un nuevo nodo después de un dato específico en la lista
    public void insertarEnMedio(Object dato, Object datoDespues) {
        NodoDoble actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(datoDespues)) {
                NodoDoble nuevo = new NodoDoble(dato);
                nuevo.setSiguiente(actual.getSiguiente());
                if (actual.getSiguiente() != null) {
                    actual.getSiguiente().setAnterior(nuevo);
                }
                actual.setSiguiente(nuevo);
                nuevo.setAnterior(actual);
                if (actual == ultimo) {
                    ultimo = nuevo;
                }
                return;
            }
            actual = actual.getSiguiente();
        }
        System.out.println("El dato " + datoDespues + " no se encuentra en la lista");
    }

    // Método para eliminar un nodo con un dato específico de la lista
    public void eliminarEnMedio(Object dato) {
        if (estaVacia()) {
            return;
        }
        NodoDoble actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                if (actual == primero) {
                    eliminarAlInicio();
                } else if (actual == ultimo) {
                    eliminarAlFinal();
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                }
                return;
            }
            actual = actual.getSiguiente();
        }
        System.out.println("El dato " + dato + " no se encuentra en la lista");
    }

    // Método para revertir la lista
    public void revertirLista() {
        if (estaVacia() || primero == ultimo) {
            return;
        }
        NodoDoble actual = primero;
        NodoDoble temp = null;
        while (actual != null) {
            temp = actual.getAnterior();
            actual.setAnterior(actual.getSiguiente());
            actual.setSiguiente(temp);
            actual = actual.getAnterior();
        }
        if (temp != null) {
            ultimo = primero;
            primero = temp.getAnterior();
        }
    }

    // Método para obtener el tamaño de la lista
    public int tamanoLista() {
        int tamano = 0;
        NodoDoble actual = primero;
        while (actual != null) {
            tamano++;
            actual = actual.getSiguiente();
        }
        return tamano;
    }

    // Método para eliminar nodos duplicados de la lista
    public void eliminarDuplicados() {
        NodoDoble actual = primero;
        while (actual != null) {
            NodoDoble comparar = actual.getSiguiente();
            while (comparar != null) {
                if (actual.getDato().equals(comparar.getDato())) {
                    if (comparar == ultimo) {
                        ultimo = comparar.getAnterior();
                    }
                    comparar.getAnterior().setSiguiente(comparar.getSiguiente());
                    if (comparar.getSiguiente() != null) {
                        comparar.getSiguiente().setAnterior(comparar.getAnterior());
                    }
                    comparar = comparar.getSiguiente(); // Avanzar el puntero después de eliminar un duplicado
                } else {
                    comparar = comparar.getSiguiente();
                }
            }
            actual = actual.getSiguiente();
        }
    }
}
