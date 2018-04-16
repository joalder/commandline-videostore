package cl_videostore;

public abstract class Rental {
	protected Movie movie;
	protected int daysRented;

	protected Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public abstract double getAmount();

	public abstract int getFrequentRenterPoints();

	public String getMovieName() {
		return movie.getName();
	}
}
