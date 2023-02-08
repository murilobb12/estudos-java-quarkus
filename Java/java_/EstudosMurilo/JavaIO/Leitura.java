package java_.EstudosMurilo.JavaIO;

import java.io.*;

public class Leitura {

    public static void main(String[] args) throws IOException {


        InputStream fileInputStream = new FileInputStream("java_/EstudosMurilo/JavaIO/entrada.txt");
        Reader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        String linha = bufferedReader.readLine();
        while (linha != null) {
            System.out.println(linha);
            linha = bufferedReader.readLine();;
        }

        bufferedReader.close();



    }

}
