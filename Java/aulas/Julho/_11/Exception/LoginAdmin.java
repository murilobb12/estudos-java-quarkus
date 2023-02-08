package aulas.Julho._11.Exception;

import java.util.Scanner;

public class LoginAdmin {

    public static void main(String[] args) throws Exception  {

        Scanner sc = new Scanner(System.in);

        System.out.println("Login: ");
        String name = sc.nextLine();
        System.out.println("Senha: ");
        String password = sc.nextLine();

        try {
            autentica(name, password);

        } catch (UserNotFound e) {

            e.printStackTrace();
        } catch (InvalidUserException e) {

            e.printStackTrace();
        } finally {
            System.out.println("Até logo");
            sc.close();

        }

    }

    private static void autentica(String name, String password) throws UserNotFound, InvalidUserException {
        if ((name.toUpperCase().equals("ADMIN")) && (password.equals("123456789") )) {
            System.out.println("Usuário logado com sucesso");

        } else if ((password.length() < 4)) {
            throw new InvalidUserException("Campo senha inválido.");
        } else if ((name.length() < 4)) {
            throw new InvalidUserException("Campo usuário inválido.");
        } else {
            System.out.println(name.toUpperCase()=="ADMIN");
            throw new UserNotFound("Usuário " + name + " não encontrado");

        }
    }

}
