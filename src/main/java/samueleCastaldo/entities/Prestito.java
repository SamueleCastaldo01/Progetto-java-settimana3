package samueleCastaldo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestito")
public class Prestito {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)  //che poi sarebbe la tessera
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "elemento_id", nullable = false)
    private Elemento elementoPrestato;
    private LocalDate data_inizio_prestito;
    private LocalDate data_restituzione_prevista;
    @Column(nullable = true)    //può essere nulla, in questo modo posso fare dei controlli
    private LocalDate data_restituzione_effettiva;

    public Prestito() {}

    public Prestito(Utente utente, Elemento elementoPrestato, LocalDate data_inizio_prestito, LocalDate data_restituzione_effettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.data_inizio_prestito = data_inizio_prestito;
        this.data_restituzione_prevista = data_inizio_prestito.plusDays(30); //aggiunge 30 giorni rispetto a quella prevista
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    public long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public Elemento getElementoPrestato() {
        return elementoPrestato;
    }

    public LocalDate getData_inizio_prestito() {
        return data_inizio_prestito;
    }

    public LocalDate getData_restituzione_prevista() {
        return data_restituzione_prevista;
    }

    public LocalDate getData_restituzione_effettiva() {
        return data_restituzione_effettiva;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setElementoPrestato(Elemento elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public void setData_inizio_prestito(LocalDate data_inizio_prestito) {
        this.data_inizio_prestito = data_inizio_prestito;
    }

    public void setData_restituzione_prevista(LocalDate data_restituzione_prevista) {
        this.data_restituzione_prevista = data_restituzione_prevista;
    }

    public void setData_restituzione_effettiva(LocalDate data_restituzione_effettiva) {
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", data_inizio_prestito=" + data_inizio_prestito +
                ", data_restituzione_prevista=" + data_restituzione_prevista +
                ", data_restituzione_effettiva=" + data_restituzione_effettiva +
                '}';
    }
}
