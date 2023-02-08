package java_.EstudosMurilo.Thread;

public class MeuRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread atual: " + Thread.currentThread().getName());

    }
}
