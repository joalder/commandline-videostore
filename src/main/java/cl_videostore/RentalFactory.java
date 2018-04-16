package cl_videostore;

public class RentalFactory {
	private MovieRepository movieRepository;

	public RentalFactory(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Rental createFrom(String input) {
		final String[] rentalData = input.split(" ");
		int movieKey = Integer.parseInt(rentalData[0]);
		final Movie movie = movieRepository.getByKey(movieKey);
		return createRental(movie, Integer.parseInt(rentalData[1]));
	}

	static Rental createRental(Movie movie, int daysRented) {
		switch (movie.getCategory()) {
			case REGULAR:
				return new RegularRental(movie, daysRented);
			case NEW_RELEASE:
				return new NewReleaseRental(movie, daysRented);
			case CHILDRENS:
				return new ChildrenRental(movie, daysRented);
			default:
				throw new IllegalArgumentException("Unknown Movie type given!");
		}
	}
}
