package java_.EstudosMurilo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExecutorsSingleThread {

    public static void main(String[] args) {

        Tarefa tarefa = new Tarefa();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            //Criar a tarefa
            executor.execute(tarefa);
            //Aguardar a thread ser executada
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
        //Finalizar a tarefa
        executor.shutdown();
        }

        Future<?> submit = executor.submit(tarefa);
        submit.isDone();



    }

    //system.currentMiles

    public static class Tarefa implements Runnable{

        @Override
        public void run() {
            System.out.println("Testando executors.");
        }
    }

}
