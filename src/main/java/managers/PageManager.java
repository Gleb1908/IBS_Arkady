package managers;

import pages.HomePage;
import pages.ShoppingBasketPage;
import pages.ProductPage;
import pages.SearchingResultsPage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка с результатами поиска
     */
    private SearchingResultsPage searchingResultsPage;
    /**
     * Страничка продукта
     */
    private ProductPage productPage;
    /**
     * Страничка корзины
     */
    private ShoppingBasketPage shoppingBasketPage;


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link SearchingResultsPage}
     *
     * @return ResultsPage
     */
    public SearchingResultsPage getSearchingResultsPage() {
        if (searchingResultsPage == null) {
            searchingResultsPage = new SearchingResultsPage();
        }
        return searchingResultsPage;
    }

    /**
     * Ленивая инициализация {@link ProductPage}
     *
     * @return ProductPage
     */
    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    /**
     * Ленивая инициализация {@link ShoppingBasketPage}
     *
     * @return CartPage
     */
    public ShoppingBasketPage getShoppingBasketPage() {
        if (shoppingBasketPage == null) {
            shoppingBasketPage = new ShoppingBasketPage();
        }
        return shoppingBasketPage;
    }

}
