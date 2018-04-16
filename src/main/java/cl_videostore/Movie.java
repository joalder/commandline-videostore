package cl_videostore;

public class Movie {
	private final int key;
	private final String name;
	private final MovieCategory category;

	public Movie(int key, String name, MovieCategory category) {

		this.key = key;
		this.name = name;
		this.category = category;
	}

	public int getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public MovieCategory getCategory() {
		return category;
	}
}
