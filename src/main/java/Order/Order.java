package Order;

import java.util.ArrayList;

public class Order {
    private String id;
    private String status;
    private int number;
    private ArrayList<Ingredients> ingredients;
    private String createdAt;
    private String updatedAt;

    public Order(String id, String status, int number, ArrayList<Ingredients> ingredients, String createdAt, String updatedAt) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.ingredients = ingredients;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Order(String id, String status, int number, String createdAt, String updatedAt) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
