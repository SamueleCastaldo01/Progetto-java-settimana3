package samueleCastaldo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import samueleCastaldo.entities.Elemento;

import javax.swing.text.html.parser.Entity;

public class ArchivioDAO {
    private final EntityManager entityManager;

    public ArchivioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void aggiuntaElemento(Elemento newElemento) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newElemento);
        transaction.commit();
        System.out.println("Elemento aggiunto con successo: " +newElemento.getTitolo());
    }

}
