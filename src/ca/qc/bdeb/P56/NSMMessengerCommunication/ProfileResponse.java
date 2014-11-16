package ca.qc.bdeb.P56.NSMMessengerCommunication;

/**
 *
 * @author patrick
 */
public class ProfileResponse {
    public String username;
    public String courriel;
    public String nom;
    public String prenom;
    public String sexe;
    public int age;
    public boolean isContact;
    public String image;
    public ProfileResponse(){
        
    }
    public ProfileResponse(String username, String courriel, String nom, String prenom, String sexe, int age, boolean isContact,String image) {
        this.username = username;
        this.courriel = courriel;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.isContact=isContact;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public boolean isContact() {
        return isContact;
    }

    public String getUsername() {
        return username;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }
    
    
}

