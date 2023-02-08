package java_.EstudosMurilo.Datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Datas {


    public static void main(String[] args) throws ParseException {

        //Criando um formatador para converter String para Data
        String dataRequisicao = "12/04/1995";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = formatter.parse(dataRequisicao);
        System.out.println(dataFormatada);


        LocalDate hoje = LocalDate.now();

        //Declarando a data específica
        LocalDate olimpiadas = LocalDate.of(2016, Month.JUNE, 5);

        //Declarando a diferença de anos sem utilizar a classe Period
        int anos = hoje.getYear() - olimpiadas.getYear();
        System.out.println(hoje + "\n" + olimpiadas + "\n" + anos);

        //Usando a diferença de anos usando a classe Period
        Period period = Period.between(hoje, olimpiadas);
        System.out.println(period.toString());

        //Vendo quando vai ser a próxima olimpíada
        LocalDate proximaOlimpiada = olimpiadas.plusYears(4);
        System.out.println("Próxima olimpíadas: " + proximaOlimpiada);

        //Organizando um formatador de data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Imprimindo a datada proxima olimpiadas formatada com o padrao
        System.out.println(proximaOlimpiada.format(formatterData));

        //Criando um formatador de Data e Hora
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("d/MM/yyyy HH-mm-ss");

        //Criando um LocalDateTime e imprimindo com o formatador criado
        LocalDateTime agora = LocalDateTime.now();
        String data = "2000-12-04";
        System.out.println(agora.format(formatterDataHora));

    }

}
