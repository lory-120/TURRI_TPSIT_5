package model;


class Tavolo {
    int numero;
    int posti;
    boolean occupato;
    String nomePrenotazione;

    Tavolo(int numero, int posti) {
        this.numero = numero;
        this.posti = posti;
        this.occupato = false;
        this.nomePrenotazione = "";
    }

    @Override
    public String toString() {
        if (occupato) {
            return "Tavolo " + numero + " (" + posti + " posti) -----> Occupato da: " + nomePrenotazione;
        } else {
            return "Tavolo " + numero + " (" + posti + " posti) -----> Libero";
        }
    }
}