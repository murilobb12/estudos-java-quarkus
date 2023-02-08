package aulas.Tecnicas.Data;

import javax.sound.midi.Soundbank;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Data {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2022,8,03);
        System.out.println(localDate);


        Date data = new Date();
        System.out.println(data);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println(gregorianCalendar.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        System.out.println(calendar.getTime());


        //API do JAVA 8
        LocalDate dataAtual = LocalDate.now();
        System.out.println("Data: " + dataAtual);

        LocalTime horaAtual = LocalTime.now();
        System.out.println("Hora: " + horaAtual);


        LocalDateTime dataHoraAtual = LocalDateTime.now();
        System.out.println("Data e Hora: " + dataHoraAtual);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: "+ zonedDateTime);

        //Pega o horário padrão do UTC
        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("OffsetDateTime: " + offsetDateTime);

        ZonedDateTime utcZoned = ZonedDateTime.now();
        ZoneId swissZone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime swissLocal = swissZoned.toLocalDateTime();
        System.out.println(swissLocal);

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now().minusHours(1));
        System.out.println("Format: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        LocalDateTime.now().plusDays(1).plusMonths(2);
        LocalDateTime.now().plusDays(5).plusYears(2).plusHours(5).plusMinutes(1000);

        LocalDate.parse("2018-01-02", DateTimeFormatter.ofPattern("yyy-MM-dd"));



    }

}
