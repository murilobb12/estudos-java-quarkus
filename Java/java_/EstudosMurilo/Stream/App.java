package java_.EstudosMurilo.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {


        List<String> nomeList = Arrays.asList();
        List<Integer> numerosList = Arrays.asList(1, 2, 3, 4, 5);
        //Caso queremos utilizar o stream em um Map, devemos usar o entrySet
        Map<String, String> values = new HashMap<>();
        values.put("Teste", "valor123");
        values.entrySet().stream();

        numerosList.stream()
                .filter(integer -> integer > 3)
                .map(integer -> integer * 2)
                .forEach(System.out::println);

        List<Integer> listStream = Stream.of(1, 2, 3, 4, 5)
                .filter(integer -> integer % 2 == 0)
                .map(integer -> integer * 2)
                .collect(Collectors.toList());

        Stream<Object> build = Stream.builder().add("Teste").add("Murilo").build();

        List<Integer> results = new ArrayList<>();

        ArrayList<Integer> arrayListCollect = listStream.stream()
                .filter(integer -> integer > 3)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Long> listCollect = listStream.stream()
                .map(Integer::longValue)
                .filter(integer -> integer > 2)
                .collect(Collectors.toList());

        //Set não permite lista duplicada

        Stream<Integer> a = Stream.of(1,2,3);
        Stream<Integer> b = Stream.of(4,5,6 );


        //Utiliza para juntar duas Streams
        List<Integer> flatMapList = Stream.of(a,b)
                .flatMap(dado -> dado)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(flatMapList);

        Stream<Integer> maxValue = Stream.of(50, 45, 46, 70);
        maxValue.mapToInt(Integer::intValue).forEach(System.out::print);

        List<Integer> lista = Arrays.asList(1,2,3,4,5,6);

        Optional<Integer> min = lista.stream().min(Comparator.naturalOrder());

        System.out.println(min.get());

        //Passar o identador 0, o que vai acontecer com cada elemento, a forma que ira ser tratado
        System.out.println("1 " +  Stream.of(1,2,3,4,5)
                .reduce(0, (elemento, elemento2) ->  elemento + elemento2));

        System.out.println("1 " +  Stream.of(1,2,3,4,5,100)
                .reduce(0, Integer::sum));

        System.out.println(lista.stream().sorted());






    }
}
