package utilities;

import org.testng.annotations.DataProvider;

import model.Booking;
import model.BookingDates;

/**
 * Utility class that provides data for test cases using TestNG's @DataProvider annotation.
 * This class is responsible for supplying test data for booking-related operations.
 */
public class DataProviders {
	
	/**
	 * Data provider method for booking creation tests.
	 * This method reads booking data from an Excel file and returns it as a two-dimensional array.
	 * Each row in the array represents a Booking object with its associated data.
	 * 
	 * @return a two-dimensional array of Booking objects read from the Excel file.
	 *         The data is fetched using the `ExcelUtils.readExcelData` method.
	 */
	@DataProvider(name = "bookingData")
	public static Object[][] bookingData() {
		// Read data from the Excel file named "BookingData" and return it as an array of Booking objects
		return ExcelUtils.readExcelData("POST");
	}

	@DataProvider(name =  "updateBookingData")
	public static Object[][] updateBookingData(){

		return ExcelUtils.readExcelData("PUT");
	}

}
