package cl_videostore;

public class Movie {
	private int key;
	private String name;
	private String category;

	public Movie(int key, String name, String category) {
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

	public String getCategory() {
		return category;
	}
}
