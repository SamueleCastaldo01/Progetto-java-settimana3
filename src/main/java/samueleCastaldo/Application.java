package samueleCastaldo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import samueleCastaldo.dao.ArchivioDAO;
import samueleCastaldo.entities.*;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_settimana3");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ArchivioDAO ad = new ArchivioDAO(em);


        //creazione libro e riviste, per popolare il db
        Libro libro1 = new Libro("Il Signore degli Anelli", 1954, 1216, "J.R.R. Tolkien", "Fantasy");
        Libro libro2 = new Libro("Harry Potter e la Pietra Filosofale", 1997, 223, "J.K. Rowling", "Fantasy");

        Rivista rivista1 = new Rivista("National Geographic", 2023, 100, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("Scientific American", 2023, 120, Periodicita.MENSILE);

        //buona parte dei commenti, stanno anche nei commit, sulla logica che ho utilizzato

        //test numero 1 andato a buon fine :)

        /*
        ad.aggiuntaElemento(libro1);
        ad.aggiuntaElemento(libro2);
        ad.aggiuntaElemento(rivista1);
        ad.aggiuntaElemento(rivista2); */



        //Test 2. funziona, ha eliminato correttamente l'elemento dal db
        //ad.rimozioneElementoByISBN(1);

        //test 3. Ricerca tramite ISBN, Funziona anche questo :)
        Elemento resultSearch = ad.getByISBN(2);
        System.out.println(resultSearch);

        //test ricerca per anno di pubblicazione, perfetto funziona correttamente :)
        List<Elemento> resultAnnoPubblicazione = ad.getByAnnoDiPubblicazione(2023);
        for (Elemento elemento : resultAnnoPubblicazione) {
            System.out.println(elemento);
        }


        //test ricerca per autore, perfetto funziona correttamente
        List<Libro> resultLibroAutore = ad.getLibroByAutore("J.K. Rowling");
        for (Libro libro : resultLibroAutore) {
            System.out.println(libro);
        }

        //test ricerca per titolo, con like. Funziona me lo prende anche se non ho inserito tutto il titolo
        List<Elemento> resultElementoTitolo = ad.getELementoByTitolo("Harry Potter");
        for (Elemento elemento : resultElementoTitolo) {
            System.out.println(elemento);
        }

        //aggiunta di utenti e prestito, per fare i vari test
        Utente utente1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 5, 20));
        Utente utente2 = new Utente("Luigi", "Verdi", LocalDate.of(1985, 3, 15));

        /*
        ad.aggiuntaUtente(utente1);
        ad.aggiuntaUtente(utente2); */

        Prestito prestito1 = new Prestito(utente1, rivista1, LocalDate.now(), null);
        Prestito prestito2 = new Prestito(utente2, libro2, LocalDate.now(), null);
        Prestito prestito3 = new Prestito(utente2, rivista2, LocalDate.now(), null);

        /*
        ad.aggiuntaPrestito(prestito1);
        ad.aggiuntaPrestito(prestito2);
        ad.aggiuntaPrestito(prestito4); */

        //test ricerca elementi attualmente in prestito e dato un numero di tessera, quindi tramite utente
        List<Elemento> resultElementoPrestitoNumeroTessera = ad.getElementoPrestitoByNumeroTessera(52);
        for (Elemento elemento : resultElementoPrestitoNumeroTessera) {
            System.out.println(elemento);
        }

        //Ultimo test tutti i prestiti scaduti, e non restituiti, ok funziona
        List<Prestito> resultPrestitoScadutiNonRestituiti = ad.getPrestitoScadutiNonRestituiti();
        for (Prestito prestito : resultPrestitoScadutiNonRestituiti) {
            System.out.println(prestito);
        }

        em.close();
        emf.close();
    }
}
