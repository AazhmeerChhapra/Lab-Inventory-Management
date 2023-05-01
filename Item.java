import java.util.Date;


public class Item {
    private String name;
    private String model;
    private int quantity;
    private double estimatedValue;

    private boolean consumable;
    private boolean selected;

    // Constructor
    public Item(String name, String model, int quantity, double estimatedValue, boolean consumable) {
        this.name = name;
        this.model = model;
        this.quantity = quantity;
        this.estimatedValue = estimatedValue;
        this.consumable = consumable;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getEstimatedValue() {
        return estimatedValue;
    }
    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }
    public boolean isConsumable() {
        return consumable;
    }
    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
