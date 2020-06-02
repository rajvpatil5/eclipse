package DataproviderApi;

import org.testng.annotations.DataProvider;

public class AddBookDataprovider {
	@DataProvider(name = "BooksDataprovider")
	public Object addBookDataprovider() {
		return new Object[][] { { "adffdfre", "213212" }, { "wertrr", "13213" }, { "tyytrtyr", "535" } };
	}
}