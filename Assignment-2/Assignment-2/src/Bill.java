public class Bill {
    private String type;
    private double amount;
    private String owner;

    public Bill(String type, double amount, String owner){
        this.type = type;
        this.amount = amount;
        this.owner = owner;
    }

    public String getType() {return type ;}
    public double getAmount() {return amount; }
    public String getOwner() {return owner; }

    @Override
    public String toString(){
        return type + "Bill";
    }
}
