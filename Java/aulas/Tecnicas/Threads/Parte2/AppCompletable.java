package aulas.Tecnicas.Threads.Parte2;

import java.util.concurrent.*;

public class AppCompletable {

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


        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Hello World");
        });

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        forkJoinPool.;


        try {
            t1.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
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