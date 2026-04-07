enum BillStatus {
    PENDING,
    APPROVED,
    REJECTED,
    PROCESSING
}

public class Bill {
    private String type;
    private double amount;
    private String owner;
    public BillStatus status;

    public Bill(String type, double amount, String owner){
        this.type = type;
        this.amount = amount;
        this.owner = owner;
        this.status = BillStatus.PENDING;
    }

    public String getType() {return type ;}
    public double getAmount() {return amount; }
    public String getOwner() {return owner; }
    public BillStatus getStatus() { return status; }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return type + " Bill";
    }
}
