package aulas.Tecnicas.Threads.Parte2;

import java.util.concurrent.*;

public class AppExecutor {

    private static final String USER = "Cerberus";
    private static volatile String usuario;
    private static volatile String profile;


    public static void main(String[] args) {

        /**
         *
         *  Trazer os dados do usuário e os dados do perfil
         *  Dados do usuário - Banco de Dados (5000 ms)
         *  Dados do perfil - WebService (10000 ms)
         *
         */


        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> t1 = executorService.submit(() -> System.out.println(Thread.currentThread().getName()));
        Future<?> t2 = executorService.submit(() -> System.out.println(Thread.currentThread().getName()));

        try {
            t1.get();
            t2.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
        executorService.shutdown();
        }





    }

    private static String getUsuario(String usuario) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    private static String getProfile(String user) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}