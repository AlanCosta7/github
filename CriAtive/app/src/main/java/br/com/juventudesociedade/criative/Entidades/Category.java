package br.com.juventudesociedade.criative.Entidades;

/**
 * Created by alanp on 07/02/2018.
 */

public class Category {

    private String autorCurso;
    private String descricaoCurso;
    private String tituloCurso;
    private String textoCurso;
    private String imageCurso;
    private String videoUrl;

    public Category() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAutorCurso() {
        return autorCurso;
    }

    public void setAutorCurso(String autorCurso) {
        this.autorCurso = autorCurso;
    }

    public String getDescricaoCurso() {
        return descricaoCurso;
    }

    public void setDescricaoCurso(String descricaoCurso) {
        this.descricaoCurso = descricaoCurso;
    }

    public String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public String getTextoCurso() {
        return textoCurso;
    }

    public void setTextoCurso(String textoCurso) {
        this.textoCurso = textoCurso;
    }

    public String getImageCurso() {
        return imageCurso;
    }

    public void setImageCurso(String imageCurso) {
        this.imageCurso = imageCurso;
    }
}
