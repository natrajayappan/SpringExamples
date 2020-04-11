/**
 * 
 */
package com.demo.application;

/**
 * @author PooraniArya
 *
 */
public class LocationDetails {
	
	private String state;
	private String country;
	private int latestCount;
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the latestCount
	 */
	public int getLatestCount() {
		return latestCount;
	}
	/**
	 * @param latestCount the latestCount to set
	 */
	public void setLatestCount(int latestCount) {
		this.latestCount = latestCount;
	}
	
	
	@Override
	public String toString() {
		return "LocationDetails [state=" + state + ", country=" + country + ", latestCount=" + latestCount + "]";
	}

}
