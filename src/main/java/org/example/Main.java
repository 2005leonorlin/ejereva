package org.example;

import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.services.DataService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.security.Provider;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");
        Provider.Service servicio = new Provider.Service(emf);

        try {
            // Crear usuarios y comentarios de ejemplo
            servicio.registrarUsuario("juan@example.com", "Juan Pérez");
            servicio.registrarUsuario("ana@example.com", "Ana López");

            servicio.crearComentario("juan@example.com", "¡Excelente experiencia!", 9);
            servicio.crearComentario("juan@example.com", "Podría mejorar, pero está bien", 7);
            servicio.crearComentario("ana@example.com", "Fantástico servicio", 10);

            // Listar los mejores comentarios con valoración igual o superior a 8
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
            // Cerrar el EntityManagerFactory
            emf.close();
        }
    }
}








