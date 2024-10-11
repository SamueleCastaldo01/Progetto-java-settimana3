package samueleCastaldo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_settimana3");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        //buona parte dei commenti, stanno anche nei commit, sulla logica che ho utilizzato

        System.out.println("Hello World!");
    }
}
