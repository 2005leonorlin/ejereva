package org.example.models;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;


@Data
@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private Long id;

    private String contenido;

    @Column(nullable = false)
    private Integer valoracion; // Valoración entre 0 y 10

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Relación con Usuario

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", valoracion=" + valoracion +
                ", usuario=" + (usuario != null ? usuario.getCorreo() : "null") +
                '}';
    }
}


