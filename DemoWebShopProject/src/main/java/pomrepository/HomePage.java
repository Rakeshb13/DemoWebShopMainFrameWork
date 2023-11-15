package pomrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(linkText = "Log in")
	private WebElement loginLink;

	@FindBy(linkText = "Register")
	private WebElement registerLink;

	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartLink;

	@FindBy(xpath = "//span[text()='Wishlist']")
	private WebElement wishListLink;

	@FindBy(linkText = "Log out")
	private WebElement logoutLink;

	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLoginLink() {
		return loginLink;
	}

	public WebElement getRegisterLink() {
		return registerLink;
	}

	public WebElement getShoppingCartLink() {
		return shoppingCartLink;
	}

	public WebElement getWishListLink() {
		return wishListLink;
	}
	
	public WebElement getLogoutLink() {
		return logoutLink;
	}
}