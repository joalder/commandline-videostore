package cl_videostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;

public class Main {

	private final InputStream in;
	private final PrintStream out;

	public static void main(String[] args) throws IOException {
		new Main(System.in, System.out).run();
	}

	public Main(InputStream in, PrintStream out) {
		this.in = in;
		this.out = out;
	}

	void run() throws IOException {
		MovieRepository movieRepository = new MovieRepository();

		movieRepository.getAll()
				.forEach(movie -> out.print(movie.getKey() + ": " + movie.getName() + "\n"));

		final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(in));
		out.print("Enter customer name: ");
		String customerName = inputStreamReader.readLine();

		out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "cl_videostore.Rental Record for " + customerName + "\n";
		while (true) {
			String input = inputStreamReader.readLine();
			if (input.isEmpty()) {
				break;
			}
			final String[] rentalData = input.split(" ");
			final Movie movie = movieRepository.getByKey(Integer.parseInt(rentalData[0]));
			final Rental rental = new Rental(movie, Integer.parseInt(rentalData[1]));

			frequentRenterPoints += rental.getFrequentRenterPoints();
			// show figures for this rental
			result += "\t" + rental.getMovieName() + "\t" + rental.getAmount() + "\n";
			totalAmount += rental.getAmount();
		}

		// add footer lines
		result += "You owed " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points\n";

		out.print(result);
	}

}
