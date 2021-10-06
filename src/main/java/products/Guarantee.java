package products;

public enum Guarantee {

    BASE_12 ("12 мес."),
    BASE_12_PLUS_12 ("+ 12 мес."),
    BASE_12_PLUS_24 ("+ 24 мес.");

    private String title;

    Guarantee(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}
