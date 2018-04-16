package cl_videostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Main {

	private final InputStream in;
	private final PrintStream out;
	private final MovieRepository movieRepository;
	private final RentalFactory rentalFactory;

	public static void main(String[] args) throws IOException {
		new Main(System.in, System.out).run();
	}

	public Main(InputStream in, PrintStream out) throws IOException {
		this.in = in;
		this.out = out;
		movieRepository = new MovieRepository();
		rentalFactory = new RentalFactory(movieRepository);
	}

	void run() throws IOException {

		for (Movie movie : movieRepository.getAllMovies()) {
			out.print(movie.getKey() + ": " + movie.getName() + "\n");
		}

		final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(in));
		out.print("Enter customer name: ");
		String customerName = inputStreamReader.readLine();

		out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "cl_videostore.Rental Record for " + customerName + "\n";

		Collection<Rental> rentals = new ArrayList<>();

		while (true) {
			String input = inputStreamReader.readLine();

			if (input.isEmpty()) {
				break;
			}

			rentals.add(rentalFactory.createFrom(input));
		}

		frequentRenterPoints = rentals.stream()
				.map(Rental::getFrequentRenterPoints)
				.mapToInt(Integer::intValue)
				.sum();

		totalAmount = rentals.stream()
				.map(Rental::getAmount)
				.mapToDouble(Double::doubleValue)
				.sum();

		result += rentals.stream()
				.map(rental -> "\t" + rental.getMovieName() + "\t" + rental.getAmount() + "\n")
				.collect(Collectors.joining(""));

		// add footer lines
		result += "You owed " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points\n";

		out.print(result);
	}

}
