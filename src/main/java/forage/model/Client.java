package forage.model;

import javax.persistence.*;

// import baccalaureat.model.Ecole;
// import baccalaureat.model.Serie;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer idClient;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "contact", length = 50)
    private int contact;

    // @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    // private List<Demande> demandes;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "idEcole", nullable = false)
    // private Ecole ecole;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "idSerie", nullable = false)
    // private Serie serie;

    public Client() {
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

   
}
