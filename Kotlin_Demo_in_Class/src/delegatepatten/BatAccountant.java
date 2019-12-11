package delegatepatten;

public class BatAccountant implements AccountantDelegate {
    private String name;

    public String getName() {
        return name;
    }

    public BatAccountant(String name) {
        this.name = name;
    }

    public long calculateTax(long revenue){
        return Math.round(revenue*0.3);
    }
}
