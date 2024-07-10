package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import base.PageContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CategoriesPage {
    private PageContext context;

    // Constructor to initialize PageContext
    public CategoriesPage(PageContext context) {
        this.context = context;
    }

    By select_position = By.id("products-orderby");
    By category = By.xpath("//div[@class='listbox']/ul/li/a");
    By subCategory = By.xpath("//*[@class=\"sublist\"]/li/a");
    @Given("user selects category")
    public void searchByCategory() {
        List<WebElement> categoryElements = context.getDriver().findElements(category);

        for (int i = 0; i < categoryElements.size(); i++) {
            WebElement categoryElement = categoryElements.get(i);
            String categoryName = categoryElement.getText().trim(); // Get category name

            try {
                categoryElement.click();
                verifySearchResults(categoryName);
            } catch (StaleElementReferenceException e) {
                // Retry finding the element and clicking it
                categoryElements = context.getDriver().findElements(category);
                categoryElement = categoryElements.get(i);
                categoryElement.click();
                verifySearchResults(categoryName);
            }

            context.getDriver().navigate().back();
            categoryElements = context.getDriver().findElements(category); 
        }
    }

    private void verifySearchResults(String category) {
        switch (category.toLowerCase()) {
            case "books":
            	 VerifySortProductsByName();
                 break;
            case "computers":
            	chooseSubList();
                break;
            case "electronics":
            	chooseSubList();
            	break;
            case "apparel & shoes":
            case "digital downloads":
            case "jewelry":
            case "gift cards":
                VerifySortProductsByName();
                break;
            default:
                break;
        }
    }

    // Method to verify sorting products by name
    public void VerifySortProductsByName() {
        // Find the dropdown element
        WebElement dropdown = context.getDriver().findElement(select_position);
        
        // Create Select object
        Select select = new Select(dropdown);
        
        // Select by index (assuming the index of "Sort by Name" option is 1)
        select.selectByIndex(1);
        isSortedAtoZ();
       
    }

    
    public void isSortedAtoZ() {
    	 // Wait for the products to be sorted (adjust timeout as needed)
        context.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-grid")));
        List<WebElement> products = context.getDriver().findElements(By.cssSelector(".product-grid"));

        boolean sorted = true;
        String previous = ""; // Empty string

        for (WebElement element : products) {
            String current = element.getText().trim();
            if (current.compareTo(previous) < 0) {
                sorted = false;
                break;
            }
            previous = current;
        }
        if (sorted) {
            System.out.println("it is sorted");
        } else {
            System.out.println("it is not sorted");
        }
    }
    
    public void chooseSubList() {
        List<WebElement> subCategoryElements = context.getDriver().findElements(subCategory);
        
        if (!subCategoryElements.isEmpty()) {
            for (int i = 0; i < subCategoryElements.size(); i++) {
                subCategoryElements = context.getDriver().findElements(subCategory); 
                WebElement subCategoryElement = subCategoryElements.get(i);
                
                try {
                    subCategoryElement.click();
                    VerifySortProductsByName();
                } catch (StaleElementReferenceException e) {
                    // Retry clicking the element on StaleElementReferenceException
                    subCategoryElements = context.getDriver().findElements(subCategory); 
                    subCategoryElement = subCategoryElements.get(i);
                    subCategoryElement.click();
                }
            }
        
            VerifySortProductsByName();
        } else {
            System.out.println("No sub-categories found for the current category.");
        }
    }
}
