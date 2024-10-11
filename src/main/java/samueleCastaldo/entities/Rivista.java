package samueleCastaldo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivista")
public class Rivista extends Elemento{
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    //costruttore vuoto, già conosciamo i motivi
    public Rivista() {}

    public Rivista(String titolo, int anno_pubblicazione, int numero_pagine, Periodicita periodicita) {
        super(titolo, anno_pubblicazione, numero_pagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_pagine=" + numero_pagine +
                '}';
    }
}
