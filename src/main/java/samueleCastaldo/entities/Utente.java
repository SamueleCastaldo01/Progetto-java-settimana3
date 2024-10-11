package samueleCastaldo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue
    private long numero_tessera; //qui non sta specificato, ma credo che sia il suo id, anche perché in un esercizio dopo si fa la ricerca tramite numero di tessera
    private String nome;
    private String cognome;
    private LocalDate data_nascita;

    public Utente() {}

    public Utente(String nome, String cognome, LocalDate data_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
    }

    public long getNumero_tessera() {
        return numero_tessera;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setNumero_tessera(long numero_tessera) {
        this.numero_tessera = numero_tessera;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "numero_tessera=" + numero_tessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_nascita=" + data_nascita +
                '}';
    }
}
