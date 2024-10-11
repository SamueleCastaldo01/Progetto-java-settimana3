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

    //lo ho messa privata perchè non è richiesta nella traccia
    //ma io la vado ad utilizzare per la funzione di sotto
    private Elemento getByISBN(long ISBN) {
        Elemento found = entityManager.find(Elemento.class, ISBN);
        if(found == null) throw new IllegalArgumentException("NOn è stato travato");
        return found;
    }

    public void rimozioneElementoByISBN(long ISBN) {
        Elemento found = this.getByISBN(ISBN);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();
        System.out.println("Elemento eliminato correttamente: " +found.getTitolo());
    }

}
