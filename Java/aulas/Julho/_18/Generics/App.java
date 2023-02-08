package aulas.Julho._18.Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        //List<Object> list = new ArrayList();
        List list = new ArrayList();
        List<Integer> intList = List.of(1,2,3,4,5);
        list = intList;

        Integer[] intArray = {1,56,4,6,12,14};
        orderArray(intArray);

        String[] stringArray = {"Ana", "Mario", "Alexandre", "Beatriz"};
        orderArray(stringArray);

        Integer j = null;
        Optional<Integer> integerOptional = Optional.empty();

    }

    //Método para ordenar, porém só números.
//    public static <T extends Number> void orderArray(T[] array){
//        Arrays.sort(array);
//    }

    //Método para ordenar, sem passar um item genérico
    public static <T> void orderArray(T[] array){
        Arrays.sort(array);
    }
}
