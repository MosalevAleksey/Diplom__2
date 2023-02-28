package Order;

/*
        {"_id":"61c0c5a71d1f82001bdaaa6d","name":"Флюоресцентная булка R2-D3",
        {"_id":"61c0c5a71d1f82001bdaaa6f","name":"Мясо бессмертных моллюсков Protostomia",
        {"_id":"61c0c5a71d1f82001bdaaa70","name":"Говяжий метеорит (отбивная)",
        {"_id":"61c0c5a71d1f82001bdaaa71","name":"Биокотлета из марсианской Магнолии",
        {"_id":"61c0c5a71d1f82001bdaaa72","name":"Соус Spicy-X",uce-02-mobile.png",
        {"_id":"61c0c5a71d1f82001bdaaa6e","name":"Филе Люминесцентного тетраодонтимформа",
        {"_id":"61c0c5a71d1f82001bdaaa73","name":"Соус фирменный Space Sauce",
        {"_id":"61c0c5a71d1f82001bdaaa74","name":"Соус традиционный галактический",
        {"_id":"61c0c5a71d1f82001bdaaa6c","name":"Краторная булка N-200i",
        {"_id":"61c0c5a71d1f82001bdaaa75","name":"Соус с шипами Антарианского плоскоходца",
        {"_id":"61c0c5a71d1f82001bdaaa76","name":"Хрустящие минеральные кольца",
        {"_id":"61c0c5a71d1f82001bdaaa78","name":"Кристаллы марсианских альфа-сахаридов",
        {"_id":"61c0c5a71d1f82001bdaaa79","name":"Мини-салат Экзо-Плантаго",
        {"_id":"61c0c5a71d1f82001bdaaa7a","name":"Сыр с астероидной плесенью",
*/


import java.util.ArrayList;
import java.util.Arrays;


public class OrderGenerater {

    public Ingredients orderwithIngrediens() {
        ArrayList<String> ingredients1 = new ArrayList<>();
        ingredients1.add("60d3b41abdacab0026a733c6"); //"Краторная булка N-200i",
        ingredients1.add("609646e4dc916e00276b2870");//"Говяжий метеорит (отбивная)",
        ingredients1.add("61c0c5a71d1f82001bdaaa7a"); //"Сыр с астероидной плесенью",
        Ingredients ingredients = new Ingredients(ingredients1);

        return ingredients;
    }

    public Ingredients orderwithOutIngrediens() {
        Ingredients ingredients = new Ingredients();

        return ingredients;
    }

    public Ingredients orderRongIngrediens() {
        ArrayList<String> ingredients1 = new ArrayList<>();//"\"ingredients\": [\"60d3b41abdacab0026a733c6\",\"609646e4dc916e00276b2870\",\"61c0c5a71d1f82001bdaaa7a\"]";
        ingredients1.add("3c6");
        ingredients1.add("606b2870");
        ingredients1.add("6bdaaa7a");
        Ingredients ingredients = new Ingredients(ingredients1);

        return ingredients;
    }
}
