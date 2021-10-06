package products;

public enum Products {

    SEACH_HAIER ("холодильник Haier"),
    CHOOSE_HAIER ("Haier HB18FGSAAARU"),
    SEACH_SAMSUNG ("телевизор lg"),
    CHOOSE_SAMSUNG ("LG 49UK6200PLA");

    private String title;

    Products(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
