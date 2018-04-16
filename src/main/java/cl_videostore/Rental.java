package cl_videostore;

public class Rental {
	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	double getThisAmount() {
		double total = 0;
		switch (movie.getCategory()) {
			case "REGULAR":
				total += 2;
				if (this.daysRented > 2) {
					total += (this.daysRented - 2) * 1.5;
				}
				break;
			case "NEW_RELEASE":
				total += this.daysRented * 3;
				break;
			case "CHILDRENS":
				total += 1.5;
				if (this.daysRented > 3) {
					total += (this.daysRented - 3) * 1.5;
				}
				break;
		}
		return total;
	}

	int getFrequentRenterPoints() {
		int frequentRenterPoints = 1;

		// add bonus for a two day new release rentalParts
		if (this.movie.getCategory().equals("NEW_RELEASE") && this.daysRented > 1) {
			frequentRenterPoints++;
		}

		return frequentRenterPoints;
	}

	public String getMovieName() {
		return this.movie.getName();
	}
}
