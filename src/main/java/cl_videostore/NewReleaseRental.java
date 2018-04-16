package cl_videostore;

public class NewReleaseRental extends Rental {
	NewReleaseRental(Movie movie, int daysRented) {
		super(movie, daysRented);
	}


	public double getAmount() {
		return (double) (daysRented * 3);
	}

	public int getFrequentRenterPoints() {
		if (daysRented > 1) {
			return 2;
		}

		return 1;
	}
}
