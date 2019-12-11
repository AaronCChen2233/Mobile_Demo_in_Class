package delegatepatten;

public class GoodAccountant implements AccountantDelegate {
    private String name;

    public String getName() {
        return name;
    }

    public GoodAccountant(String name) {
        this.name = name;
    }

    public long calculateTax(long revenue){
        return Math.round(revenue*0.1);
    }
}
