package cl_videostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
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
		// read movies from file
		final InputStream movieStream = Main.class.getResourceAsStream("/movies.cvs");
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(movieStream));
		final Map<Integer, Movie> movies = new HashMap<>();
		while (bufferedReader.ready()) {
			final String line = bufferedReader.readLine();
			final String[] movieData = line.split(";");
			Movie movie = new Movie(Integer.parseInt(movieData[0]), movieData[1], movieData[2]);
			movies.put(movie.getKey(), movie);
			out.print(movie.getKey() + ": " + movie.getName() + "\n");
		}

		final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(in));
		out.print("Enter customer name: ");
		String customerName = inputStreamReader.readLine();

		out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + customerName + "\n";
		while (true) {
			String input = inputStreamReader.readLine();
			if (input.isEmpty()) {
				break;
			}
			final String[] rentalParts = input.split(" ");
			Movie movieId = movies.get(Integer.parseInt(rentalParts[0]));
			int daysRented = Integer.parseInt(rentalParts[1]);
			final Rental rental = new Rental(movieId, daysRented);

			//determine amounts for rentalParts
			double thisAmount = +rental.getThisAmount();
			frequentRenterPoints += rental.getFrequentRenterPoints();

			// show figures for this rentalParts
			result += "\t" + rental.getMovieName() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// add footer lines
		result += "You owed " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points\n";

		out.print(result);
	}

}
