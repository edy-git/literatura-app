package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutor)
    {
        this.nombre =  (datosAutor.nombre() != null) ? datosAutor.nombre() : "Autor desconocido";
        try
        {
            this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        } catch (java.lang.NumberFormatException e)
        {
            this.fechaDeNacimiento = null;
        }
        try {
            this.fechaDeMuerte = datosAutor.fechaDeMuerte();
        }catch (java.lang.NumberFormatException e)
        {
            this.fechaDeMuerte = null;
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {

        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                "********** AUTOR ***********" + '\n' +
                "Nombre= " + nombre + '\n' +
                "Año de nacimiento= " + fechaDeNacimiento + '\n' +
                "Año de muerte= " + fechaDeMuerte + '\n' +
                "Libros= " + libros.stream().map(Libro::getTitulo).toList() + '\n' +
                "****************************" + '\n';
    }
}
