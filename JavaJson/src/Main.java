import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args){

        JSONObject object = new JSONObject();

        //instancia um novo JSONObject
        JSONObject my_obj = new JSONObject();

        //preenche o objeto com os campos: titulo, ano e genero
        my_obj.put("titulo", "JSON x XML: a Batalha Final");
        my_obj.put("ano", 2012);
        my_obj.put("genero", "Ação");


        System.out.println(my_obj);
        System.out.println(my_obj.get("titulo"));





    }
}