package cl_videostore;

public class RegularRental extends Rental {
	public RegularRental(Movie movie, int daysRented) {
		super(movie, daysRented);
	}

	public double getAmount() {
		double thisAmount = 2;

		if (daysRented > 2) {
			thisAmount += (daysRented - 2) * 1.5;
		}

		return thisAmount;
	}

	public int getFrequentRenterPoints() {
		return 1;
	}
}
