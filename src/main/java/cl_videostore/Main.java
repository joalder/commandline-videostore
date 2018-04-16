package cl_videostore;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;

public class Main {

	private final InputStream in;
	private final PrintStream out;
	private final MovieRepository movieRepository;
	private final RentalFactory rentalFactory;
	private final Console console;

	public Main(InputStream in, PrintStream out) throws IOException {
		this.in = in;
		this.out = out;
		movieRepository = new MovieRepository();
		rentalFactory = new RentalFactory(movieRepository);
		console = new Console(in, out, rentalFactory);
	}

	public static void main(String[] args) throws IOException {
		new Main(System.in, System.out).run();
	}

	void run() throws IOException {
		console.printMovies(movieRepository.getAllMovies());

		String customerName = console.inputCustomerName();

		Collection<Rental> rentals = console.readRentals();
		RentalRecord rentalRecord = new RentalRecord(rentals, customerName);

		console.printRentalRecord(rentalRecord);

		console.printFooter(rentalRecord);
	}
}
