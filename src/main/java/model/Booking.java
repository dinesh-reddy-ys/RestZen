package model;

/**
 * Represents a booking with details such as customer name, price, deposit status, 
 * booking dates, and additional needs.
 */
public class Booking {
    // Customer's first name
    private String firstname;

    // Customer's last name
    private String lastname;

    // Total price of the booking
    private int totalprice;

    // Indicates whether the deposit has been paid
    private boolean depositpaid;

    // Booking dates (start and end dates)
    private BookingDates bookingdates;

    // Additional needs or requests for the booking
    private String additionalneeds;

    
    
    
    
    /**
	 * Default constructor for Booking.
	 */
    public Booking() {
		// Default constructor
	}
    /**
	 * Constructs a Booking with specified details.
	 * @param firstname the customer's first name
	 * @param lastname the customer's last name
	 * @param totalprice the total price of the booking
	 * @param depositpaid whether the deposit has been paid
	 * @param bookingdates the booking dates
	 * @param additionalneeds additional needs or requests for the booking
	 */
    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}
    // Getters and Setters

    /**
     * Gets the customer's first name.
     * @return the first name
     */
    public String getFirstname() { return firstname; }

    /**
     * Sets the customer's first name.
     * @param firstname the first name to set
     */
    public void setFirstname(String firstname) { this.firstname = firstname; }

    /**
     * Gets the customer's last name.
     * @return the last name
     */
    public String getLastname() { return lastname; }

    /**
     * Sets the customer's last name.
     * @param lastname the last name to set
     */
    public void setLastname(String lastname) { this.lastname = lastname; }

    /**
     * Gets the total price of the booking.
     * @return the total price
     */
    public int getTotalprice() { return totalprice; }

    /**
     * Sets the total price of the booking.
     * @param totalprice the total price to set
     */
    public void setTotalprice(int totalprice) { this.totalprice = totalprice; }

    /**
     * Checks if the deposit has been paid.
     * @return true if the deposit is paid, false otherwise
     */
    public boolean isDepositpaid() { return depositpaid; }

    /**
     * Sets the deposit paid status.
     * @param depositpaid true if the deposit is paid, false otherwise
     */
    public void setDepositpaid(boolean depositpaid) { this.depositpaid = depositpaid; }

    /**
     * Gets the booking dates.
     * @return the booking dates
     */
    public BookingDates getBookingdates() { return bookingdates; }

    /**
     * Sets the booking dates.
     * @param bookingdates the booking dates to set
     */
    public void setBookingdates(BookingDates bookingdates) { this.bookingdates = bookingdates; }

    /**
     * Gets additional needs or requests for the booking.
     * @return the additional needs
     */
    public String getAdditionalneeds() { return additionalneeds; }

    /**
     * Sets additional needs or requests for the booking.
     * @param additionalneeds the additional needs to set
     */
    public void setAdditionalneeds(String additionalneeds) { this.additionalneeds = additionalneeds; }
}
