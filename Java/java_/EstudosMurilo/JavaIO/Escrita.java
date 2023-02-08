package java_.EstudosMurilo.JavaIO;

import java.io.*;

public class Escrita {

    public static void main(String[] args) throws IOException {

        OutputStream fileOutputStream = new FileOutputStream("java_/EstudosMurilo/JavaIO/entrada.txt");
        Writer outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


        bufferedWriter.write("Mano, isso é realmente secreto.");
        bufferedWriter.newLine();
        bufferedWriter.write("<---CUIDADO--->");


        bufferedWriter.close();





    }
}

