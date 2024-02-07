package tests;

import model.Account;
import utility.TestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTest {

	public static void main(String[] args) {
		testConstructor();
		testSetters();
	}

	public static void testConstructor() {

		// 1-Setup
		String test_accountNumber = "234512";
		String test_username = "dave";
		String test_accountType = "Saving";
		Date test_openingDate = new Date();

		// 2-Exercise, run the object under test (constructor)
		Account testAccount = new Account(test_accountNumber, test_username, test_accountType, test_openingDate);

		// 3- Verify (Assert)
		assert testAccount.getAccount_number() == (test_accountNumber)
				: "TC1-getAccountNumber-Failed: account number did not match";
		assert testAccount.getUsername_of_account_holder() == (test_username)
				: "TC2-getUsername_of_account_holder-Failed: username did not match";
		assert testAccount.getAccount_type() == (test_accountType)
				: "TC3-getAccountType-Failed: account type did not match";
		assert testAccount.getAccount_opening_date() == (test_openingDate)
				: "TC4-getAccountOpeningDate-Failed: opening date did not match";

		System.out.println("All Java assertions in the Account constructor test passed (none failed)");

	}

	public static void testSetters() {
		// 1-Setup (Initial "dummy" test account created which contents will be
		// overwritten by the setter methods
		Account testAccount = new Account("123456", "alex", "Saving", new Date());

		// 2-Exercise, run the setter methods
		testAccount.setAccount_number("347832");
		testAccount.setUsername_of_account_holder("ethansmith");
		testAccount.setAccount_type("Checking");

		// 3- Verify (Assert)
		assert testAccount.getAccount_number() == ("347832")
				: "TC5-setAccountNumber-Failed: account number did not match";
		assert testAccount.getUsername_of_account_holder() == ("ethansmith")
				: "TC6-setUsername_of_account_holder-Failed: username did not match";
		assert testAccount.getAccount_type() == ("Checking") : "TC7-setAccountType-Failed: account type did not match";

		System.out.println("All Java assertions in the Account setters test passed (none failed)");
	}

}
