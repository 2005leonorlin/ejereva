package org.example.services;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private static EntityManagerFactory emf;
    public DataService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * quiero registrar un nuevo usuario en la plataforma

     * @param correo
     */
    public void registrarUsuario(String correo, String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuarioExistente = em.find(Usuario.class, correo);
            if (usuarioExistente != null) {
                throw new RuntimeException("Ya existe un usuario registrado con el correo: " + correo);
            }
            Usuario nuevoUsuario = new Usuario(correo, nombre);
            em.persist(nuevoUsuario);

            em.getTransaction().commit();
            System.out.println("Usuario registrado exitosamente: " + correo);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /**
     * quiero saber el numero de comentarios de un usuario concreto
     */
    public List<Comentario> listarMejoresComentarios(int valorMinimo) {
        EntityManager em = emf.createEntityManager();
        List<Comentario> mejoresComentarios = new ArrayList<>();

        try {
            em.getTransaction().begin();

            // Consulta ajustada para obtener comentarios con una valoración igual o superior al valor mínimo
            TypedQuery<Comentario> query = em.createQuery(
                    "SELECT c FROM Comentario c WHERE c.valoracion >= :valorMinimo ORDER BY c.valoracion DESC",
                    Comentario.class
            );
            query.setParameter("valorMinimo", valorMinimo);

            mejoresComentarios = query.getResultList();
            em.getTransaction().commit();

        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al listar los mejores comentarios: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return mejoresComentarios;
    }
    //añadir un comentario a una pelicula, quiero añadir comentario escrito por mi

    /**
     * quiero saber el numero de comentarios de un usuario concreto
     */
    public void AñadirComentario(Comentario c){
        try{
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();

        }

    }
    /**
     * quiero eliminar un usuario de la plataforma
     */
    public void eliminarUsuario(Usuario u){
        try{
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            e.printStackTrace();

        }

    }



}
