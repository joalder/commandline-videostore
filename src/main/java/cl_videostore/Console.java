package cl_videostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

public class Console {
	private final InputStream in;
	private final PrintStream out;
	private final BufferedReader inputStreamReader;
	private RentalFactory rentalFactory;

	public Console(InputStream in, PrintStream out, RentalFactory rentalFactory) {
		this.in = in;
		this.out = out;
		this.rentalFactory = rentalFactory;
		this.inputStreamReader = new BufferedReader(new InputStreamReader(in));
	}

	public String inputCustomerName() throws IOException {
		out.print("Enter customer name: ");
		return inputStreamReader.readLine();
	}

	public Collection<Rental> readRentals() throws IOException {
		out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");
		Collection<Rental> rentals = new ArrayList<>();

		while (true) {
			String input = inputStreamReader.readLine();

			if (input.isEmpty()) {
				break;
			}

			rentals.add(rentalFactory.createFrom(input));
		}

		return rentals;
	}

	public void printRentalRecord(RentalRecord rentalRecord) {
		out.print("cl_videostore.Rental Record for " + rentalRecord.getCustomerName() + "\n");

		rentalRecord.getRentals()
				.forEach(rental -> out.print("\t" + rental.getMovieName() + "\t" + rental.getAmount() + "\n"));
	}

	public void printFooter(RentalRecord rentalRecord) {
		out.print("You owed " + rentalRecord.getTotalAmount() + "\n");
		out.print("You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n");
	}

	public void printMovies(Collection<Movie> movies) {
		for (Movie movie : movies) {
			out.print(movie.getKey() + ": " + movie.getName() + "\n");
		}
	}
}
