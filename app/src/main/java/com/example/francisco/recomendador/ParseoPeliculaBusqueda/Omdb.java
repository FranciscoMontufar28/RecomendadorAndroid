package com.example.francisco.recomendador.ParseoPeliculaBusqueda;

/**
 * Created by Francisco on 01/05/2016.
 */
public class Omdb {

    private String Title;
    private String Year;
    private String Genre;
    private String Plot;
    private String Poster;

    public Omdb(String title, String year, String genre, String plot, String poster) {

        this.Title = title;
        this.Year = year;
        this.Genre = genre;
        this.Plot = plot;
        this.Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
