package samueleCastaldo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import samueleCastaldo.dao.ArchivioDAO;
import samueleCastaldo.entities.Libro;
import samueleCastaldo.entities.Periodicita;
import samueleCastaldo.entities.Rivista;

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

        ad.aggiuntaElemento(libro1);
        ad.aggiuntaElemento(libro2);
        ad.aggiuntaElemento(rivista1);
        ad.aggiuntaElemento(rivista2);

        System.out.println("Hello World!");
    }
}
