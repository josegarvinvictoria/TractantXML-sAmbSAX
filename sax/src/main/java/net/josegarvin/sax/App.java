package net.josegarvin.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Programa que genera la clasificació de la lliga a partir d'un fitxer XML.
 * 
 * @author Jose Garvin Victoria.
 *
 */
public class App {
  /**
   * Variable de referencia per comprovar si un fitxer té l'extensió "xml".
   */
  private static final int REF_EXTENSIO = 3;

  /**
   * Mètode principal del programa.
   * 
   * @param args -->.
   */
  public static void main(final String[] args) {
    // Instanciem la clase App
    App program = new App();

    // Demanar a l'usuari el fitxer XML.
    File fitxerXML = program.obtenirFitxer();

    // Obtenir fabrica.
    SAXParserFactory fabrica = SAXParserFactory.newInstance();

    fabrica.setNamespaceAware(true);

    try {
      // Crear el parser.
      SAXParser parser = fabrica.newSAXParser();

      // Al parser li passem el document XML i la classe que rebrà els events
      // SAX.
      Procesar procesar = new Procesar();
      parser.parse(fitxerXML, procesar);

      // Obtenim la clasificacio generada a partir del fitxer .xml.
      ArrayList<DetallEquip> clasificacio = procesar.getClasificacio();
      if (clasificacio.size() == 0) {
        System.out.println("Sembla que no has introduït un XML vàlid!");
      } else {
        program.mostrarClasificacio(clasificacio);
      }

    } catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
      System.out.println("No s'ha pogut llegir el document...");
    }

  }

  /**
   * Mètode que s'encarrega de mostrar la clasificació generada a partir del
   * fitxer .xml.
   * 
   * @param clasificacio
   *          --> ArrayList d'objectes "DetallEquip" on hi ha el resultats
   *          totals de tots els equips.
   */
  public final void mostrarClasificacio(
      final ArrayList<DetallEquip> clasificacio) {
    System.out.println();
    System.out.println("----------->Clasificació<-----------");

    Collections.sort(clasificacio, new Comparator<DetallEquip>() {

      public int compare(final DetallEquip o1, final DetallEquip o2) {
        // TODO Auto-generated method stub
        return o2.getPunts().compareTo(new Integer(o1.getPunts()));
      }

    });

    for (int i = 0; i < clasificacio.size(); i++) {
      System.out.println(clasificacio.get(i).getNom() + " | Victories: "
          + clasificacio.get(i).getVictories() + " | Empats: "
          + clasificacio.get(i).getEmpats() + " | Derrotes: "
          + clasificacio.get(i).getDerrotes() + " | Punts: "
          + clasificacio.get(i).getPunts());
    }

    System.out.println();
  }

  /**
   * Mètode per demanar el fitxer a l'usuari.
   * 
   * @return --> Retorna un fitxer xml.
   */
  public final File obtenirFitxer() {
    String nomfitxer = "6nacions.xml";
    boolean esXML = false;
    File ruta = new File(nomfitxer);
    System.out.println("Introdueix la ruta al fitxer XML:");
    Scanner lector = new Scanner(System.in);
    nomfitxer = lector.nextLine();

    esXML = comprovarEsXML(nomfitxer);
    ruta = new File(nomfitxer);
    while (!ruta.exists() || ruta.isDirectory() || !esXML) {
      if (ruta.isDirectory()) {
        System.out
            .println("La ruta apunta a un directori! Cal introduir una ruta"
                + " que apunti a un fitxer XML:");
      } else if (!ruta.exists()) {
        System.out.println("Aquest fitxer no existeix. Torna-hi:");

      } else if (ruta.exists() && !comprovarEsXML(nomfitxer)) {
        System.out.println("El programa nomès treballa amb XML's! Torna-hi:");
      }
      nomfitxer = lector.nextLine();
      esXML = comprovarEsXML(nomfitxer);
      ruta = new File(nomfitxer);
    }
    lector.close();
    return ruta;
  }

  /**
   * Mètode per comprovar si un fitxer té extensió "xml".
   * 
   * @param nomFitxer
   *          --> Nom del fitxer a examinar.
   * @return --> Retorna true o false depenent de si l'arxiu té l'extensió xml o
   *         no.
   */
  public final boolean comprovarEsXML(final String nomFitxer) {
    if (nomFitxer.substring(nomFitxer.length() - REF_EXTENSIO,
        nomFitxer.length()).equalsIgnoreCase("xml")) {
      return true;
    }
    return false;
  }

}
