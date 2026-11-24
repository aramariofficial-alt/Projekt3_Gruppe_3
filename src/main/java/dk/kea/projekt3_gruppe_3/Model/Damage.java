package dk.kea.projekt3_gruppe_3.Model;

public class Damage {
    private int damageId;
    private String description;
    private double price;

    public Damage(int damageId, String description, double price) {
        this.damageId = damageId;
        this.description = description;
        this.price = price;
    }

    public int getDamageId() {
        return damageId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
