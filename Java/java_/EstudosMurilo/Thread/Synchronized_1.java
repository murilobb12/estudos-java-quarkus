package java_.EstudosMurilo.Thread;

public class Synchronized_1 {

    static int i = -1;

    public static void main(String[] args) throws InterruptedException {

        MeuRunnable runnable =  new MeuRunnable();

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        System.out.println("Inicial: " + i);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(1000);
        System.out.println("Final: " + i);
    }

    public static class MeuRunnable implements Runnable{
//        static Object lock1 = new Object();
//        static Object lock2 = new Object();

        //Métodos statics não podem receber this como synchronized, devem passar a claase que está sendo chamado
        public static void imprime(){
            synchronized (Synchronized_1.class){

            }
            i++;
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + i);
        }

        //public synchronized void run() { //Ao colocar a palavra Synchronized apenas uma thread pode acessar o método por vez
        @Override
        public void run(){

            synchronized (this){
                imprime();
            }

//            synchronized(lock1) {
//                i++;
//                String name = Thread.currentThread().getName();
//                System.out.println(name + " : " + i);
//            }
//            synchronized(lock2) {
//                i++;
//                String name = Thread.currentThread().getName();
//                System.out.println(name + " : " + i);
//            }
        }
    }
}
