package org.example.models;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
    private Long id;
    private String contenido;
    private Integer valoracion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();
    public void addComentario(Comentario c) {
        comentarios.add(c);
        c.getComentarios().add(this);
    }
    public Comentario(String contenido, Integer valoracion) {
        this.contenido = contenido;
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", valoracion=" + valoracion +
                ", comentarios=" + comentarios +
                '}';
    }
}






