package ca.qc.bdeb.P56.NSMMessenger.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Martin on 2014-11-25.
 */
public class Fichiers {
    public static void sauvegarderFichier(String path,String contenu){
        try {
            Files.write(Paths.get(path), contenu.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> chargerFichier(String path){
        List<String> contenu = null;
        try {
            contenu = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Fichier "+path+" non trouvé");
        }
        return contenu;
    }
    public static void supprimerFichier(String path){
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Fichier "+path+" non trouvé");
        }
    }
}
