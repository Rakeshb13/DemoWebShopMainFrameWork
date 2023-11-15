package pomrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="Email")
	private WebElement emailTextField;

	@FindBy(id="Password")
	private WebElement passwordTextField;

	@FindBy(id="RememberMe")
	private WebElement rememberMeCheckbox;
	
	@FindBy(xpath="//input[@value='Log in']")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public WebElement getEmailTextField() {
		return emailTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getRememberMeCheckbox() {
		return rememberMeCheckbox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	
	
}
