package Order;

public class Orders {
    private Order order;

    public Orders(Order order, int total, int totalToday) {
        this.order = order;
        this.total = total;
        this.totalToday = totalToday;
    }

    private int total;
    private int totalToday;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }
}
