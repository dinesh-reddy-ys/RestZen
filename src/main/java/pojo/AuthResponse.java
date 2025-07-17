package pojo;

public class AuthResponse {
	
	private String token;
	/**
	 * Default constructor for AuthResponse.
	 */
	public AuthResponse() {
		
	}
	/**
	 * Parameterized constructor for AuthResponse.
	 * @param token The authentication token.
	 */
	public AuthResponse(String token) {
		
	
		this.token = token;
	}
	
	/**
	 * Retrieves the authentication token.
	 * @return The authentication token as a String.
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Sets the authentication token.
	 * @param token The authentication token to set.
	 */
	

}
