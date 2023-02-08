package datas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author TASSIS
 *
 *         Criar uma feature que permita o cliente selecionar um filtro e
 *         aplic�-lo nos lan�amentos de seu extrato.
 *         O cliente espera no m�nimo seja entregue tr�s filtro conforme abaixo:
 *         Lan�amentos at� 45 dias.
 *         Lan�amentos at� 90 dias.
 *         Lan�amentos entre <per�odo inicial> e <per�do final>. Se o per�odo
 *         for maior que 90 dias deve lan�ar uma exception com a seguinte
 *         mensagem �Per�odo deve ser menor que 90 dias.�.
 *         Lan�amentos do m�s.
 *         Lan�amentos do ano.
 *
 *
 */

public class Exercicio {

    public static void main(String[] args) {

        Statement statement1 = new Statement("Mall � Explosion Ice cream", 25.0, "2022-07-23");
        Statement statement2 = new Statement("Mall � Explosion Ice cream", 10.50, "2022-03-01");
        Statement statement3 = new Statement("Mall � Explosion Ice cream", 15.0, "2022-03-01");
        Statement statement4 = new Statement("Super Market � All things", 15.0, "2021-10-30");
        Statement statement5 = new Statement("Amazon on-line", 50.0, "2022-05-01");
        Statement statement6 = new Statement("Free fire gift card", 500.0, "2022-06-10");

        List<Statement> statements = new ArrayList<>();
        statements.add(statement1);
        statements.add(statement2);
        statements.add(statement3);
        statements.add(statement4);
        statements.add(statement5);
        statements.add(statement6);

        System.out.println("given_listOfStatements_then_return_statements_with_less_than_ninety_days");
        given_listOfStatements_then_return_statements_with_less_than_ninety_days(statements);

        System.out.println("\ngiven_listOfStatements_then_return_statements_with_less_45_days");
        given_listOfStatements_then_return_statements_with_less_45_days(statements);

        System.out.println("\ngiven_listOfStatements_when_date_are_less_than_ninety_days_then_return_statements");
        given_listOfStatements_when_period_are_less_than_ninety_days_then_return_statements(statements);

        System.out.println("\ngiven_listOfStatements_when_period_are_grether_than_ninety_days_then_throw_exception");
        given_listOfStatements_when_period_are_grether_than_ninety_days_then_throw_exception(statements);

        System.out.println("\ngiven_listOfStatements_when_the_month_is_5_then_return_the_statements");
        given_listOfStatements_when_the_month_is_5_then_return_the_statements(statements);

        System.out.println("\ngiven_listOfStatements_when_the_year_is_2021_then_return_the_statements");
        given_listOfStatements_when_the_year_is_2021_then_return_the_statements(statements);

    }

    /**
     * M�todod respons�vel por filtrar os lan�amentos com menos de 90 dias.
     *
     * @param statements
     * @return List<Statement2>
     */
    public static List<Statement> lessThanNinetyDays(List<Statement> statements) {
        List<Statement> aux = new ArrayList<>();

        for (Statement statement : statements) {
            if (ChronoUnit.DAYS.between(statement.getDate(), LocalDateTime.now()) < 90)
                aux.add(statement);
        }
        System.out.println(aux);
        return aux;
        // return Collections.emptyList();

    }

    /**
     * M�todod respons�vel por filtrar os lan�amentos com menos de 45 dias.
     *
     * @param statements
     * @return List<Statement2>
     */
    public static List<Statement> lessThanFourtyFiveDays(List<Statement> statements) {
        // Implementar o m�todo;
        List<Statement> aux = new ArrayList<>();
        for (Statement statement : statements) {
            if (ChronoUnit.DAYS.between(statement.getDate(), LocalDateTime.now()) < 45)
                aux.add(statement);
        }
        System.out.println(aux);
        return aux;
        // return Collections.emptyList();
    }

    /**
     * M�todod respons�vel por filtrar os lan�amentos entre um per�odo menor que 90
     * dias.
     *
     * @param statements
     * @return List<Statement2>
     */
    public static List<Statement> betweenDays(List<Statement> statements, LocalDateTime from, LocalDateTime to)
            throws Exception {
        if (ChronoUnit.DAYS.between(from, to) > 90)
            throw new Exception("Período deve ser menor que 90 dias.");
        List<Statement> aux = new ArrayList<>();
        for (Statement statement : statements) {
            if (statement.getDate().isAfter(from)&&statement.getDate().isBefore(to))
                aux.add(statement);
        }
        System.out.println(aux);
        return aux;
        // Implementar o m�todo;

        // return Collections.emptyList();
    }

    /**
     * M�todod respons�vel por filtrar os lan�amentos do m�s.
     *
     * @param statements
     * @return List<Statement2>
     */
    public static List<Statement> inTheMonth(List<Statement> statements, Month month) {

        // Implementar o m�todo;
        List<Statement> aux = new ArrayList<>();
        for (Statement statement : statements) {
            if (statement.getDate().getMonth()==month)
                aux.add(statement);
        }
        System.out.println(aux);
        return aux;
        // return Collections.emptyList();

    }

    /**
     * M�todod respons�vel por filtrar os lan�amentos do ano.
     *
     * @param statements
     * @return List<Statement2>
     */
    public static List<Statement> inTheYear(List<Statement> statements, int year) {

        // Implementar o m�todo;
        List<Statement> aux = new ArrayList<>();
        for (Statement statement : statements) {
            if (statement.getDate().getYear()==year)
                aux.add(statement);
        }
        System.out.println(aux);
        return aux;
        // return Collections.emptyList();
    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * inTheYear.
     *
     * @param statements
     */
    private static void given_listOfStatements_when_the_year_is_2021_then_return_the_statements(
            List<Statement> statements) {

        List<Statement> result = inTheYear(statements, 2021);
        List<Statement> expected = new ArrayList<>();
        expected.add(new Statement("Super Market � All things", 15.0, "2021-10-30"));

        msgPrint(result, expected);

    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * inTheMonth.
     *
     * @param statements
     */
    private static void given_listOfStatements_when_the_month_is_5_then_return_the_statements(
            List<Statement> statements) {

        List<Statement> result = inTheMonth(statements, Month.MAY);
        List<Statement> expected = new ArrayList<>();
        expected.add(new Statement("Amazon on-line", 50.0, "2022-05-01"));

        msgPrint(result, expected);

    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * betweenDays com um periodo maior que noventa dias.
     *
     * @param statements
     */
    private static void given_listOfStatements_when_period_are_grether_than_ninety_days_then_throw_exception(
            List<Statement> statements) {
        try {
            LocalDateTime from = LocalDateTime.now().minus(95l, ChronoUnit.DAYS);
            LocalDateTime to = LocalDateTime.now();

            betweenDays(statements, from, to);
            System.out.println("TEST FAILED!");

        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
            System.out.println("TESTE PASSED!");
        }
    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * betweenDays com um periodo menor que noventa dias.
     *
     * @param statements
     */
    private static void given_listOfStatements_when_period_are_less_than_ninety_days_then_return_statements(
            List<Statement> statements) {

        LocalDateTime from = LocalDateTime.now().minus(12l, ChronoUnit.DAYS);
        LocalDateTime to = LocalDateTime.now();

        try {

            List<Statement> result = betweenDays(statements, from, to);
            List<Statement> expected = new ArrayList<>();
            expected.add(new Statement("Mall � Explosion Ice cream", 25.0, "2022-07-23"));

            msgPrint(result, expected);

        } catch (Exception e) {
            System.out.println("An error occured. msg:" + e.getMessage() + "parameters: " + from + "to: " + to);
            System.out.print("TEST FAILED!");
        }
    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * lessThanFourtyFiveDays.
     *
     * @param statements
     */
    private static void given_listOfStatements_then_return_statements_with_less_45_days(List<Statement> statements) {
        List<Statement> result = lessThanFourtyFiveDays(statements);
        List<Statement> expected = new ArrayList<>();
        expected.add(new Statement("Mall � Explosion Ice cream", 25.0, "2022-07-23"));
        // expected.add(new Statement("Free fire gift card", 500.0, "2022-06-10"));

        msgPrint(result, expected);
    }

    /**
     * Este m�todo representa um teste unit�rio que valida o comportamento do m�todo
     * lessThanNinetyDays.
     *
     * @param statements
     */
    private static void given_listOfStatements_then_return_statements_with_less_than_ninety_days(
            List<Statement> statements) {
        List<Statement> result = lessThanNinetyDays(statements);

        List<Statement> expected = new ArrayList<>();
        expected.add(new Statement("Mall � Explosion Ice cream", 25.0, "2022-07-23"));
        // expected.add(new Statement("Amazon on-line", 50.0, "2022-05-01"));
        expected.add(new Statement("Free fire gift card", 500.0, "2022-06-10"));

        msgPrint(result, expected);
    }

    /**
     * Este m�todo � usado dentro dos teste unit�rios para exibir uma mensagem de
     * sucesso ou de fallha, caso o resultado n�o seja igual ao esperado.
     *
     * @param result
     * @param expected
     */
    private static void msgPrint(List<Statement> result, List<Statement> expected) {
        if (result.equals(expected)) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }
    }

}

/**
 *
 * @author Thiago
 *
 *         Esta classe � a representa��o de um lan�amento no extrato do cliente.
 *
 *         � importante que essa classe tenha os m�todos equals e hashcode para
 *         que na compara��o do
 *         resultado com o esperado functione corretamente.
 *
 *
 */
class Statement {

    private String name;
    private double value;
    private LocalDateTime date;

    public Statement(String name, double value, String date) {
        this.name = name;
        this.value = value;
        this.date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
    }

    public Statement(String name, double value, LocalDateTime date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Statement other = (Statement) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Statement [name=" + name + ", value=" + value + ", date=" + date + "]";
    }

}
