package aulas.Tecnicas.Threads.Parte2;

public class App {


    public static void main(String[] args) {

        /**
         *
         *  Trazer os dados do usuário e os dados do perfil
         *  Dados do usuário - Banco de Dados (5000 ms)
         *  Dados do perfil - WebService (10000 ms)
         *
         */


        /*App app = new App();

        app.pegaUsuario();*/

        /*

            Thread main
                vai no BD
                volta
                    Acessa o WebService
                    volta
                        concatena o resultado
                        imprime na tela


         */
        Thread thread = new Thread();

        long start =  System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName());

        try {
        String usuario = getUsuario("Cerberus");
        String profile = getProfile("Cerberus");


        System.out.println(usuario + " OIAPSKPOSA " + profile);


        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }finally {
            long end = System.currentTimeMillis();
            //Pegando o tempo que demorou
            System.out.println((end - start) / 1000 + "s Tempo gasto");
        }


    }

    private static String getUsuario(String usuario) throws InterruptedException {
        Thread.sleep(5000);
        return usuario;
    }

    private static String getProfile(String user) throws InterruptedException {
        Thread.sleep(10000);
        return user;


    }

}
