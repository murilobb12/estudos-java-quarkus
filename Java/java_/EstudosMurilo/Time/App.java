package java_.EstudosMurilo.Time;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class App {

    public static void main(String[] args) {


        //LocalDate armazena apenas a data: 12/08/2022
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println(localDate.of(1990,2,15));

        //LocalTime armazena apenas o horário: 19:10:00
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(localTime.format(formatter));

        //localDateTime armazena o horário + data: 14/08/2022 19:10:00
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatterHoraData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(formatterHoraData));
        System.out.println("PATERN");
        //Instant representa um instante/momento na linha do tempo (milissegundos a partir de 01/01/1970) o horário vai vir no padrão GMT/UTC
        System.out.println("INstant");
        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());

        //ZonedDateTime - LocalDateTime com Time Zone(Fuso Horário)
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);



    }

}
