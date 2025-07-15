package model;

public class BookingResponse {
private int bookingid;
private Booking booking;

public BookingResponse() {
	// Default constructor
}

public BookingResponse(int bookingid, Booking booking) {
	this.bookingid = bookingid;
	this.booking = booking;
}

public int getBookingid() {
	return bookingid;
}
public void setBookingid(int bookingid) {
	this.bookingid = bookingid;
}
public Booking getBooking() {
	return booking;
}
public void setBooking(Booking booking) {
	this.booking = booking;
}




}
