package java_;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

// C = 2 * π * r, onde: C = comprimento da circunferência ou perímetro.
// Por exemplo, se o diâmetro for igual a 4 cm, o raio será igual a 4 cm ÷ 2 = 2 cm.
public class EstudosTecnicas {


    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Olá Mundo");
            }
        }).run();

        new Thread(() -> System.out.println("Ola Mundo")).run();

        List<Integer> lista = new ArrayList<>();

        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);


        lista.stream().forEach(System.out::println);
        lista.stream()
                .filter(integer -> integer % 2 == 0)
                .forEach(integer -> System.out.println(integer));

        new Thread(() -> System.out.println("Lista impressa")).run();

        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.getDayOfWeek());

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        Consumer<String> consumerr=  new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumerr.accept("olá meu amigu.");



        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("Murilo");

        Predicate<Integer> p = (Integer integer) -> integer==1;


        System.out.println("Testando predicate");
        System.out.println(p.test(1));

        Predicate<String> predicate = new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return s == null;
            }
        };

        Predicate<String> predicateLambda = (String s) -> s == null;

        Function<Integer, Integer> function2 = (Integer integer) -> integer*30;



        System.out.println(function2.apply(5));
        Function<String, Boolean> function = new Function<String, Boolean>() {

            @Override
            public Boolean apply(String s) {
                return s.equals("Thiago");
            }
        };

        function.apply("Murilo");

        Function<String, Boolean> functionLambda = (String s) -> s.equals("Thiago");

        functionLambda.apply("Murilo");

        BiFunction<Integer, Integer, Boolean> checkParentalValues = (Integer integer, Integer integer2) -> integer == integer2;

        System.out.println("Testando BiFunction: " +  checkParentalValues.apply(1,2));


        BiFunction<Integer, Integer, Integer> biFunction = new BiFunction<Integer, Integer, Integer> (){

            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1+t2;
            }
        };

        System.out.println(biFunction.apply(1,2));

        BiConsumer<String, Integer> biConsumer2 =  new BiConsumer<String, Integer>() {
            @Override
            public void accept(String nome, Integer idade) {
                System.out.println("Nome: " + nome + "\nIdade: " + idade );
            }
        };

        biConsumer2.accept("Murilo", 28);

        BiConsumer<Integer, Integer> biConsumer = (Integer integer, Integer integer2) -> System.out.println(integer + integer2);


        biConsumer.accept(1,2);

        List<String> listaString = new ArrayList<>();

        listaString.add("Murilo");
        listaString.add("Murilo");
        listaString.add("murilo");
        listaString.add("Marcela");
        listaString.add("Anaryel");
        listaString.add("Teste");
        listaString.contains("Teste");


        List<String> listaNomes = new ArrayList<>();


        listaNomes.add("Murilo");
        listaNomes.add("Rosana");

        listaNomes.stream()
                .filter(s -> s == "Murilo")
                .forEach(s -> System.out.println(s + "OAPKSPSOAK"));

        List<String> listaMinuscula = new ArrayList<>();


        listaMinuscula = listaString ;
//        for (String n:listaString
//             ) {
//            listaMinuscula.add(n.toLowerCase());
//        }

        //Map serve para transformar os dados da lista
        //Filter serve para filtrar os itens da lista
        //distinct serve para pegar os itens com distinção
        //limit serve para limitar os registros apresentados
        //

        System.out.println("Imprimindo a lista sem collectors");
        System.out.println(listaNomes);

        Map<Boolean, List<String>> collect1 = listaNomes.stream()
                .collect(Collectors.groupingBy(s ->  s.equals("Murilo")));

        System.out.println(collect1);


        String collect = listaMinuscula.stream()
                .map(e -> e.toLowerCase())
                .collect(Collectors.joining("!", "Juntando palavras em java com Joining: ",". Finalizando junção com Joining!"));

        System.out.println(collect);





    }
}


