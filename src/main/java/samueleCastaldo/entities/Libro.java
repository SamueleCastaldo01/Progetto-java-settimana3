package samueleCastaldo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro extends  Elemento{
    private String autore;
    private String genere;  //lo ho messo di tipo string e non enum, perché non è specificato

    //costruttore vuoto, già conosciamo il motivo
    public Libro() {}

    public Libro(String titolo, int anno_pubblicazione, int numero_pagine, String autore, String genere) {
        super(titolo, anno_pubblicazione, numero_pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return this.autore;
    }

    public String getGenere() {
        return this.genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Autore='" + this.autore + '\'' +
                ", Genere='" + this.genere + '\'' +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_pagine=" + numero_pagine +
                '}';
    }
}
