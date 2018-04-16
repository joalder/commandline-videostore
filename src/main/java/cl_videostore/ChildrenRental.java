package cl_videostore;

public class ChildrenRental extends Rental {
	public ChildrenRental(Movie movie, int daysRented) {
		super(movie, daysRented);
	}

	public double getAmount() {
		double thisAmount = 1.5;

		if (daysRented > 3) {
			thisAmount += (daysRented - 3) * 1.5;
		}

		return thisAmount;
	}

	public int getFrequentRenterPoints() {
		return 1;
	}
}
