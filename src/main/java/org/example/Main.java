package org.example;

import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.services.DataService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.Provider;
import java.util.List;


public class Main {
    public static void main(String[] args) {

       DataService servicio = new DataService(ObjectDBUtil.getEntityManagerFactory());

        try {

            servicio.registrarUsuario("juan@example.com", "Juan Pérez");
            servicio.registrarUsuario("ana@example.com", "Ana López");




            int valorMinimo = 8;
            List<Comentario> mejoresComentarios = servicio.listarMejoresComentarios(valorMinimo);

            System.out.println("Comentarios con valoración igual o superior a " + valorMinimo + ":");
            mejoresComentarios.forEach(comentario ->
                    System.out.println("ID: " + comentario.getId() +
                            ", Usuario: " + comentario.getUsuario().getCorreo() +
                            ", Contenido: " + comentario.getContenido() +
                            ", Valoración: " + comentario.getValoracion())
            );

        } finally {

        }
    }
}








