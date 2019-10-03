package com.twu.biblioteca.models;
import java.util.List;

public class VideoRental extends Store {
    public VideoRental(List<Movie> movies) {
        super(movies, "movie");
    }

    public String getMovieListTablePrintable () {
        StringBuilder message = new StringBuilder();
        String divider = new String(new char[135]).replace("\0", "-") + "\n";
        String tableFormater = "%40s %40s %30s %8s %12s\n";

        message.append("\nList of Movies:\n");
        message.append(divider);
        message.append(String.format(tableFormater, "ID", "TITLE", "DIRECTOR", "YEAR", "RATING"));
        message.append(divider);
        for(Movie movie : (List<Movie>) this.getProductsAvailable()) {
            message.append(String.format(tableFormater, movie.getId(), movie.getTitle(), movie.getDirector(), movie.getYear(), movie.getRatingPrintable()));
        }
        message.append(divider);

        return message.toString();
    }

    public void showMovieListTablePrintable() {
        System.out.println(this.getMovieListTablePrintable());
        this.getMenu().run();
    }
}
