package test;

import base.TestBase;
import configuration.handler.CategoriesHandler;
import configuration.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.HomePage;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

public class Categories extends TestBase {



    @Test
    void nameOfCategoryShouldMatch()  {
        HeaderPage headerPage = new HeaderPage(driver);
        List <CategoryPage> mainCategories = headerPage.getMainCategories();
        System.out.println(mainCategories.size());

        for (CategoryPage mainCategory : mainCategories) {
            System.out.println(mainCategory.toString());
        }

    }

    @Test
    void filtersSideMenuShouldBeDisplayed() {


    }

    void openCategory(){

    }

}