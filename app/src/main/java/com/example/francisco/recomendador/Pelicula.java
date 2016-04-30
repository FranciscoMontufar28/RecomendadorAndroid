package com.example.francisco.recomendador;

/**
 * Created by Francisco on 29/04/2016.
 */
public class Pelicula {

    private String nombre;
    private String tags;
    private String imagen;
    private String recomendacion;




    public Pelicula(String nombre, String tags, String imagen, String recomendacion) {
        this.nombre = nombre;
        this.tags = tags;
        this.imagen = imagen;
        this.recomendacion = recomendacion;

    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
