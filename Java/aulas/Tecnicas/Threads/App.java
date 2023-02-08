package aulas.Tecnicas.Threads;

import java.util.ArrayList;
import java.util.List;

public class App implements Runnable{

    private static int test = 0;

    public static void main(String[] args) throws InterruptedException {



/*        Runnable run = () -> System.out.println("Ola Mundo");

        Thread t = new Thread(run);
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());



        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            threadList.add(new Thread("Thread" + i){
                @Override
                        public void run(){
                    System.out.println(this.getName() + " está a correr");
                }
            });
        }

        threadList.forEach(Thread::start);





        Thread t = new Thread(){
          @Override
          public void run(){
              System.out.println("Thread a correr!");
          }
        };
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());*/


        App app = new App();
        Thread t = new Thread(app);

        System.out.println(test);
        t.start();
        //Foça que a Thread seja executada = JOIN
        t.join();
        System.out.println(test);


    }


    @Override
    public void run() {
        System.out.println("Teste");

    }
}
