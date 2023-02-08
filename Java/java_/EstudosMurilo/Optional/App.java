package java_.EstudosMurilo.Optional;

import java.util.Optional;

public class App {


    public static void main(String[] args) {

        String s = "S";
        //Integer numeroAConverter = converteEmNumero(s);

//        Optional<Integer> numeroAConverterOptional = converteEmNumeroOptional(s);
//        numeroAConverterOptional.ifPresent(n -> System.out.println(n));
        //Convertendo para Lambda
        converteEmNumeroOptional(s).ifPresent(n -> System.out.println(n));
        //Convertendo para Method Reference
        //converteEmNumeroOptional(s).ifPresent(System.out::println);

        Integer n = converteEmNumeroOptional(s).orElse(999);
        System.out.println(n);

        //Utilizado para utilizar funções pesadas caso o sistema não ache o valor do Optional
        //Integer n2 = converteEmNumeroOptional(s).orElseGet(() ->  {return operacaoPesada();});

        Integer n2 = converteEmNumeroOptional(s).orElseThrow(() -> new NullPointerException("Teste"));


        //System.out.println("Numero convertido sem optional: " + numeroAConverter + 1);
        //System.out.println("Numero convertido com  optional: " + numeroAConverterOptional + 1);

   }



   static int converteEmNumero(String numero){
        return Integer.valueOf(numero);
    }

    static Optional<Integer> converteEmNumeroOptional(String numero){
        try {
            Integer integer = Integer.valueOf(numero);
            return Optional.of(integer);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}



