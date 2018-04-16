package cl_videostore;

public class Rental {
	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public double getAmount() {
		double thisAmount = 0;

		switch (movie.getCategory()) {
			case "REGULAR":
				thisAmount += 2;
				if (daysRented > 2) {
					thisAmount += (daysRented - 2) * 1.5;
				}
				break;
			case "NEW_RELEASE":
				thisAmount += daysRented * 3;
				break;
			case "CHILDRENS":
				thisAmount += 1.5;
				if (daysRented > 3) {
					thisAmount += (daysRented - 3) * 1.5;
				}
				break;
		}
		return thisAmount;
	}

	public int getFrequentRenterPoints() {
		if (movie.getCategory().equals("NEW_RELEASE") && daysRented > 1) {
			return 2;
		}

		return 1;
	}

	public String getMovieName() {
		return movie.getName();
	}
}
