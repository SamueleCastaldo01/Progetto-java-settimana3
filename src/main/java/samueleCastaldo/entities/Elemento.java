package samueleCastaldo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "elemento")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Elemento {
    @Id
    @GeneratedValue
    protected long isbn;
    protected String titolo;
    protected int anno_pubblicazione;
    protected int numero_pagine;

    //obbligatorio per JPA, oppure esplode (non è vero)
    public Elemento() {}

    //id lo posso anche non metterlo tanto lo mette da solo (generatedValue)
    public Elemento(String titolo, int anno_pubblicazione, int numero_pagine) {
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.numero_pagine = numero_pagine;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public int getNumero_pagine() {
        return numero_pagine;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnno_pubblicazione(int anno_pubblicazione) {
        this.anno_pubblicazione = anno_pubblicazione;
    }

    public void setNumero_pagine(int numero_pagine) {
        this.numero_pagine = numero_pagine;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione='" + anno_pubblicazione + '\'' +
                ", numero_pagine=" + numero_pagine +
                '}';
    }
}
