package br.com.juventudesociedade.criative.Entidades;

/**
 * Created by alanp on 16/02/2018.
 */

public class Video {

    private String amb_videoUrl;
    private String amb_autor;
    private String amb_descricao;
    private String amb_titulo;
    private String imageVideo;

    public Video() {
    }

    public String getAmb_videoUrl() {
        return amb_videoUrl;
    }

    public void setAmb_videoUrl(String amb_videoUrl) {
        this.amb_videoUrl = amb_videoUrl;
    }

    public String getAmb_autor() {
        return amb_autor;
    }

    public void setAmb_autor(String amb_autor) {
        this.amb_autor = amb_autor;
    }

    public String getAmb_descricao() {
        return amb_descricao;
    }

    public void setAmb_descricao(String amb_descricao) {
        this.amb_descricao = amb_descricao;
    }

    public String getAmb_titulo() {
        return amb_titulo;
    }

    public void setAmb_titulo(String amb_titulo) {
        this.amb_titulo = amb_titulo;
    }

    public String getImageVideo() {
        return imageVideo;
    }

    public void setImageVideo(String imageVideo) {
        this.imageVideo = imageVideo;
    }
}
