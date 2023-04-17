package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.ProductsPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MouseOperationTest extends BaseTest {

    private MainPage mainPage;

    private ProductsPage productsPage;

    @BeforeEach
    void initialize() {
        mainPage = new MainPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void test() {
        mainPage.hoverOverWomenCategory()
                .selectBlouses();
        productsPage
                .hoverOverProduct(0)
                .clickQuickView()
                .switchToProductFrame();

        int numberOfThumbnails = productsPage.getNumberOfThumbnails();

        for (int i = 0; i < numberOfThumbnails; i++) {
            productsPage.hoverOverThumbnail(i);
            String thumbnailPartLink = productsPage.getThumbnailPartLink(i);
            String mainPicturePartLink = productsPage.getMainPicturePartLink();
            assertThat(thumbnailPartLink).isEqualTo(mainPicturePartLink);
        }
    }
}
