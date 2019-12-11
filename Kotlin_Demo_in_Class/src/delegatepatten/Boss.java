package delegatepatten;

public class Boss {
    private String name;
    private long revenue;
    private AccountantDelegate goodAccountant;

    public String getName() {
        return name;
    }

    public long getRevenue() {
        return revenue;
    }

    public Boss(String name, long revenue, AccountantDelegate goodAccountant) {
        this.name = name;
        this.revenue = revenue;
        this.goodAccountant = goodAccountant;
    }

    public long payTax() {
        return goodAccountant.calculateTax(revenue);
    }
}
