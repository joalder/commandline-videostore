package cl_videostore;

import java.util.Collection;

public class RentalRecord {
	private final Collection<Rental> rentals;
	private final String customerName;

	public RentalRecord(Collection<Rental> rentals, String customerName) {
		this.rentals = rentals;
		this.customerName = customerName;
	}

	public int getFrequentRenterPoints() {
		return rentals.stream()
				.map(Rental::getFrequentRenterPoints)
				.mapToInt(Integer::intValue)
				.sum();
	}

	public double getTotalAmount() {
		return rentals.stream()
				.map(Rental::getAmount)
				.mapToDouble(Double::doubleValue)
				.sum();
	}

	public String getCustomerName() {
		return customerName;
	}
}
