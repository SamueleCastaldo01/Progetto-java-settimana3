package samueleCastaldo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import samueleCastaldo.entities.Elemento;
import samueleCastaldo.entities.Libro;
import samueleCastaldo.entities.Prestito;
import samueleCastaldo.entities.Utente;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
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

    public void aggiuntaUtente(Utente newUtente) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUtente);
        transaction.commit();
        System.out.println("Elemento aggiunto con successo: " +newUtente.getNome());
    }

    public void aggiuntaPrestito(Prestito newPrestito) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newPrestito);
        transaction.commit();
        System.out.println("Elemento aggiunto con successo: " +newPrestito.getId());
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

    //anche in questo caso sarà una lista, perché ci possso essere più libri di un solo autore
    public List<Libro> getLibroByAutore(String autore) {
        TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return  query.getResultList();
    }

    //qui in qesto caso devo utilizzare l'operatore like, ho fatto che diventa tutto minuscolo il titolo, cosi togliamo il case sensitive
    //e poi ho messo al set Parameter su titolo %, in questo modo il titolo non deve esser proprio uguale
    public List<Elemento> getELementoByTitolo(String titolo) {
        TypedQuery<Elemento> query = entityManager.createQuery("SELECT e FROM Elemento e WHERE LOWER(e.titolo) LIKE LOWER(:titolo)", Elemento.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

    //la condizione è che la restituzione effettiva deve essere null e allo stesso tempo il numero di tessera deve corrispondere
    public List<Elemento> getElementoPrestitoByNumeroTessera(long numeroTessera) {
        TypedQuery<Elemento> query = entityManager.createQuery(
                "SELECT p.elementoPrestato FROM Prestito p WHERE p.data_restituzione_effettiva IS NULL AND p.utente.numero_tessera = :numeroTessera",
                Elemento.class
        );
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    //in questo caso data effettiva deve essere null e bisogna confrontare la data odierna con quella della scadenza
    public List<Prestito> getPrestitoScadutiNonRestituiti() {
        LocalDate oggi = LocalDate.now();
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.data_restituzione_effettiva IS NULL AND p.data_restituzione_prevista < :oggi", Prestito.class);
        query.setParameter("oggi", oggi);
        return query.getResultList();

    }



}
