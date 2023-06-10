public class Contenedor {
    private int valor;
    private boolean disponible;

    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        disponible = false;
        notifyAll();
        return valor;
    }

    public synchronized void put(int value) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        valor = value;
        disponible = true;
        notifyAll();
    }
}