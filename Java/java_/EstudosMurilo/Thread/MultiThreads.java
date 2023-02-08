package java_.EstudosMurilo.Thread;

import java.util.Arrays;

public class MultiThreads {

    public static void main(String[] args) {

        MeuRunnable meuRunnable = new MeuRunnable();

        Thread t = Thread.currentThread();

        Thread t2 = new Thread(meuRunnable);
        t2.start();
        System.out.println(t2.getName());

        Thread t3 = new Thread(() -> System.out.println("Current Thread: " + Thread.currentThread().getName()));
        t3.start();


        //Pega o nome da Thread
        System.out.println(t.getName());
        //Pega o estado atual da Thread
        System.out.println(t.getState());
        //Retorna o grupo da Thread
        System.out.println(t.getThreadGroup());
        //Retorna o class loader da Thread
        System.out.println(t.getContextClassLoader());
        //Pega o ID da Thread
        System.out.println(t.getId());
        //Pega a prioridade da Thread
        System.out.println(t.getPriority());
        //Rastreamento da pilha
        System.out.println(Arrays.toString(t.getStackTrace()));
        //Se a Thread está ativa
        System.out.println(t.isAlive());
        //Thread em low priorty
        System.out.println(t.isDaemon());
        //Verifica se a Thread foi interrompida
        System.out.println(t.isInterrupted());


    }

}
