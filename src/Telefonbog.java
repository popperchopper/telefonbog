public class Telefonbog {

    private String telefonnummer;
    private String navn;


    public Telefonbog(){}



    public Telefonbog(String telefonnummer, String navn) {
        this.telefonnummer = telefonnummer;
        this.navn = navn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

public String toString(){
        return "Navn: " + navn + ", Telefonnummer: " + telefonnummer;
}
}
