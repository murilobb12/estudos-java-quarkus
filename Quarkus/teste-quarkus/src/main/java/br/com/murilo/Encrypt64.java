package br.com.murilo;

import java.util.Arrays;
import java.util.Base64;

public class Encrypt64 {

    public static void main(String[] args) {

        byte[] encodedBytes = Base64.getEncoder().encode("qeUWrq0fk5emSaqjH0I9TUqsCwsa:OxigFV21FioVo0Be0CgmpUSMgcUa".getBytes());
        System.out.println("encodedBytes: " + new String(encodedBytes));
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        System.out.println("decodedBytes: " + new String(decodedBytes));
        byte[] encodedBytes2 = Base64.getEncoder().encode("Teste".getBytes());
        System.out.println("encodedBytes2 :" + new String(encodedBytes2));


        //Base 64 traz um Array contendo cada letra que está representada a tabela ASCII
//        System.out.println(Arrays.toString(Base64.getEncoder().encode("qeUWrq0fk5emSaqjH0I9TUqsCwsa:OxigFV21FioVo0Be0CgmpUSMgcUa".getBytes())));
    }


}
