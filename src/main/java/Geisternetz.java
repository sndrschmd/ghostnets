import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double durchmesser;
    private BigDecimal breitengrad;
    private BigDecimal laengengrad;
    private Status status;

    @OneToOne
    private Person meldendePerson;
    @OneToOne
    private Person bergendePerson;

    public Geisternetz() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(double durchmesser) {
        this.durchmesser = durchmesser;
    }

    public BigDecimal getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(BigDecimal laengengrad) {
        if (laengengrad != null) {
            this.laengengrad = laengengrad.setScale(6, RoundingMode.HALF_UP);
        } else {
            this.laengengrad = null;
        }
    }

    public BigDecimal getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(BigDecimal breitengrad) {
        if (breitengrad != null) {
            this.breitengrad = breitengrad.setScale(6, RoundingMode.HALF_UP);
        } else {
            this.breitengrad = null;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getMeldendePerson() {
        return meldendePerson;
    }

    public void setMeldendePerson(Person meldendePerson) {
        this.meldendePerson = meldendePerson;
    }

    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
}
