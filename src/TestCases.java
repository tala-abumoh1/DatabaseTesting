import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

	Connection con;

	Statement stmt;

	ResultSet rs;

	String url = "https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver = new ChromeDriver();
	
	@BeforeTest
	public void Setup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "abcd");
	}
	
	
	@Test(priority = 1)

	public void addData() throws SQLException {

		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
		+ "VALUES (77, 'ABC', 'abedalraheem', 'saqqa', '123-456-7890', 'Amman', 'Amman' ,'jordan')";;
		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Query);

	}
	
	
	
	@Test(priority = 2)
	public void updateData() throws SQLException {

		String Query = "update customers set customerName = 'tala' where customerNumber = 77 ";

		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Query);

		Assert.assertEquals(insertedRow > 0, true);

	}
	
	@Test(priority = 3)
	public void getData() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customerNumber =77");

		int customerNumber;
		String customerName = null;
		while (rs.next()) {
			customerNumber = rs.getInt("customerNumber");

			customerName = rs.getNString("customerName");

		}

		driver.get(url);

		driver.findElement(By.id("firstname")).sendKeys(customerName);

	}

	@Test(priority = 4)
	public void DeleteData() throws SQLException {

		String Query = "delete from customers where customerNumber = 77";

		stmt = con.createStatement();

		int insertedRow = stmt.executeUpdate(Query);

		Assert.assertEquals(insertedRow > 0, true);

	}
	
}
