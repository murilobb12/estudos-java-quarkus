package java_.EstudosMurilo.Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections implements Runnable{

        private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {

        Thread t = new Thread(new Synchronized_1.MeuRunnable());

        list = Collections.synchronizedList(list);


        System.out.println(list);


    }


    @Override
    public void run() {
        list.add("Palavra");
    }
}
