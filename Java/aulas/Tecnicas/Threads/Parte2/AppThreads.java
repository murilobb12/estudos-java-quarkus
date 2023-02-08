package aulas.Tecnicas.Threads.Parte2;

public class AppThreads {

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



        long start =  System.currentTimeMillis();

        Thread threadUsuario =  new Thread(() -> usuario = getUsuario(USER));
        Thread threadProfile = new Thread(() -> profile = getProfile(USER));

        threadUsuario.start();
        threadProfile.start();

        try {
            threadUsuario.join(5001);
            threadProfile.join(10001);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            long end = System.currentTimeMillis();
            //Pegando o tempo que demorou
            System.out.println((end - start) / 1000 + "s Tempo gasto");
        }

        System.out.println(usuario + " pokas" + profile);


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
