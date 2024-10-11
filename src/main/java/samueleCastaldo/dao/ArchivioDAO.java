package samueleCastaldo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import samueleCastaldo.entities.Elemento;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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
    public Elemento getByISBN(long ISBN) {
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

    //in questo caso sarà una lista, perché più libri posso essere stati fatti nello stesso anno
    public List<Elemento> getByAnnoDiPubblicazione(int annoDiPubblicazione) {
        //qui in questo caso ci conviene usare JPQL
        TypedQuery<Elemento> query = entityManager.createQuery("SELECT e FROM Elemento e WHERE e.anno_pubblicazione = :anno_p", Elemento.class);
        query.setParameter("anno_p", annoDiPubblicazione);
        return query.getResultList();
    }

}
