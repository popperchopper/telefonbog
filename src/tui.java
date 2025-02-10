import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class tui {

    private Connection con;
    private SQLConnector connector;

    public tui(Connection con){
        this.con = con;
        this.connector = new SQLConnector();
    }


    public void startTui() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n                        ___  ___  ___ _____ _   _ _____  ___   _____ _   _____ _____ _      ___________ _____ _   _ ______  _____ _____ \n" +
                "                        |  \\/  | / _ \\_   _| | | |_   _|/ _ \\ /  ___( ) |_   _|  ___| |    |  ___|  ___|  _  | \\ | || ___ \\|  _  |  __ \\\n" +
                "                        | .  . |/ /_\\ \\| | | |_| | | | / /_\\ \\\\ `--.|/    | | | |__ | |    | |__ | |_  | | | |  \\| || |_/ /| | | | |  \\/\n" +
                "                        | |\\/| ||  _  || | |  _  | | | |  _  | `--. \\     | | |  __|| |    |  __||  _| | | | | . ` || ___ \\| | | | | __ \n" +
                "                        | |  | || | | || | | | | |_| |_| | | |/\\__/ /     | | | |___| |____| |___| |   \\ \\_/ / |\\  || |_/ /\\ \\_/ / |_\\ \\\n" +
                "                        \\_|  |_/\\_| |_/\\_/ \\_| |_/\\___/\\_| |_/\\____/      \\_/ \\____/\\_____/\\____/\\_|    \\___/\\_| \\_/\\____/  \\___/ \\____/\n" +
                "                                                                                                                                        \n" +
                "                                                                                                                                        ");



        System.out.println("        1. Opret ny bruger i telefonbogen");
        System.out.println("        2. Slet en bruger i telefonbogen");
        System.out.println("        3. Vis alle i telefonbogen");
        System.out.println("        4. Søg på en bruger i telefonbogen");
        int valg = sc.nextInt();

        switch(valg){
            case 1:
                System.out.println("Indtast telefonnummer på bruger: ");
                String nummer = sc.next();
                System.out.println("Indtast navn på bruger: ");
                String navn = sc.next();
                try{
                    connector.TilfoejTilTelefonbog(nummer, navn);
                    System.out.println("Bruger tilfoejet :)");
                    startTui();
                }catch (Exception e){
                    System.out.println("Fejl");
                    throw new RuntimeException(e);
                }
                break;


            case 2:
                System.out.println("1. Slet en bruger udfra ID");
                System.out.println("2. Slet en bruger udfra Telefonnummer");
                System.out.println("3. Slet en bruger udfra Navn");
                int valg2 = sc.nextInt();
                if(valg2 == 1){
                    System.out.println("Indtast ID på bruger der skal fjernes");
                    int idsomSkalFjernes = sc.nextInt();
                    connector.SletFraTelefonbogId(idsomSkalFjernes);
                    startTui();
                } else if(valg2 == 2){
                    System.out.println("Indtast telefonnummer på bruger der skal fjernes");
                    String NummersomSkalFjernes = sc.next();
                    connector.SletFraTelefonbogNr(NummersomSkalFjernes);
                    startTui();
                } else if (valg2 == 3){
                    System.out.println("Indtast navn på bruger der skal fjernes");
                    String NavnsomSkalFjernes = sc.next();
                    connector.SletFraTelefonbogNavn(NavnsomSkalFjernes);
                    startTui();

                }
                break;
            case 3:
                System.out.println("Telefonlisten:");
                connector.visTelefonBog();
                System.out.println("\n 1. for at komme tilbage");
                int valg3 = sc.nextInt();
                if(valg3 == 1){
                    startTui();
                }
                break;

            case 4:
                System.out.println("1. Søg efter bruger med ID");
                System.out.println("2. Søg efter bruger med Telefonnummer");
                System.out.println("3. Søg efter bruger med Navn");
                int valg4 = sc.nextInt();
                if(valg4 == 1){
                    System.out.println("Indtast id på person der skal søges efter");
                    int person = sc.nextInt();
                    connector.SoegEfterPersonId(person);
                    startTui();
                } else if(valg4 == 2){
                    System.out.println("Indtast telefonnummer på person der skal søges efter");
                    String tlfnummer = sc.next();
                    connector.SoegEfterPersonNummer(tlfnummer);
                    startTui();
                } else if(valg4 == 3){
                    System.out.println("Indtast navn på person der skal søges efter");
                    String name = sc.next();
                    connector.SoegEfterPersonNavn(name);
                    startTui();
                }
        }




    }


}
