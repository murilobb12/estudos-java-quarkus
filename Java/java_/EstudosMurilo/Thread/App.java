package java_.EstudosMurilo.Thread;

public class App {

    public static void main(String[] args)  {

//      Thread atual
        Thread t = Thread.currentThread();
        System.out.println("Current Thread: " + t.getName());

//      Nova Thread
        Thread t1 = new Thread(new MeuRunnable());
        //t1.run(); //apenas executando na mesma thread
        t1.start(); //executando em uma nova thread

        // Runnable como lambda
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        t2.start();
        //t2.start(); não da para iniciar a mesma thread ao mesmo tempo.


        Thread t3 = new Thread(new MeuRunnable());
        t3.start();
//        Thread.sleep(1000);




    }


}
