package products;

/**
 * Класс продуктов
 */
public class Product {
    private String searchedName;
    private double price;
    private final boolean guaranty;
    private int yearsGuaranty;
    private int quantity = 1;
    private int indexInArrayProducts;

    public String getSearchedName(){return searchedName;}
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearsGuaranty() {
        return yearsGuaranty;
    }

    public void setYearsGuaranty(int yearsGuaranty) {
        this.yearsGuaranty = yearsGuaranty;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIndexInArrayProducts() { return indexInArrayProducts; }


    public boolean getGuaranty() {
        return guaranty;
    }
    public Product(String searchedName, double price, boolean guaranty, int indexInArray) {
        this.searchedName = searchedName;
        this.price = price;
        this.guaranty = guaranty;
        this.indexInArrayProducts =indexInArray;
    }


}