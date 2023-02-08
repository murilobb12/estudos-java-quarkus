package aulas.Tecnicas.Stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stream {



    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        List<String> namesFiltered = new ArrayList<>();

        LocalDate.now();

        names.add("Thiag");
        names.add("Gustavo");
        names.add("Murilo");
        names.add("Ana");

        for (String name:names
             ) {
            if (name.length() <= 5){
                namesFiltered.add(name);
            }

        }

        for (String name:namesFiltered
             ) {
            System.out.println(" Nome " + name);

        }





    }

}
