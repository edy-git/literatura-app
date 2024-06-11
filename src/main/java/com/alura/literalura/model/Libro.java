package com.alura.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private CodigoIdioma idioma;
    private Double numeroDeDescargas;
    @ManyToOne
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = CodigoIdioma.getStringCode(datosLibro.idiomas().toString().split(",")[0].trim());
        this.numeroDeDescargas = datosLibro.numeroDeDescargas() != null ? datosLibro.numeroDeDescargas() : 0.0;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CodigoIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(CodigoIdioma idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString()
    {
        return
                "********** LIBRO ***********" + '\n' +
                "Título = " + titulo + '\n' +
                "autor = " + autor.getNombre() + '\n' +
                "Idioma = " + idioma + '\n' +
                "Número de descargas = " + numeroDeDescargas + '\n' +
                "****************************" + '\n';
    }
}
