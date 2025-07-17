package pojo;

public class BookingDates {
    private String checkin;
    private String checkout;
    
    /**
	 * Default constructor for BookingDates.
	 */
    public BookingDates() {
		// Default constructor
	}
    
    public BookingDates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}
    
    /**
     * 
     * @return the check-in date
     * @return the check-out date
     */

    // Getters and Setters
    public String getCheckin() { return checkin; }
    public void setCheckin(String checkin) { this.checkin = checkin; }
    public String getCheckout() { return checkout; }
    public void setCheckout(String checkout) { this.checkout = checkout; }


}
