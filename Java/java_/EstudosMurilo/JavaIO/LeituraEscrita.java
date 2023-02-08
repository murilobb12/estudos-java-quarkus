package java_.EstudosMurilo.JavaIO;

import java.io.*;

public class LeituraEscrita {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = new FileInputStream("java_/EstudosMurilo/JavaIO/entrada.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        OutputStream fileOutputStream = new FileOutputStream("java_/EstudosMurilo/JavaIO/entrada2.txt");
        Writer outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        String linha =  bufferedReader.readLine();

        while (linha!=null){
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
            linha = bufferedReader.readLine();
        }

        bufferedReader.close();
        bufferedWriter.close();

    }


}
