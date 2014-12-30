package net.josegarvin.sax;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe que s'encarrega de procesar els fitxer XML.
 * 
 * @author Jose Garvin Victoria.
 *
 */
public class Procesar extends DefaultHandler {

  /**
   * ArrayList on s'emmagatzemen el equips de cada partit.
   */
  private ArrayList<Equip> equips = new ArrayList<Equip>();

  /**
   * ArrayList on s'emmagatzemen les estadistiques de cadascun dels equips.
   */
  private ArrayList<DetallEquip> clasificacio = new ArrayList<DetallEquip>();

  /**
   * Mètode que es dispara quan comença el document.
   */
  public final void startDocument() {
  }

  /**
   * Mètode que es dispara quan s'acaba un document.
   */
  public final void endDocument() {
  }

  /**
   * Mètode que es dispara quan comença un element.
   * 
   * @param uri
   *          --> Si hem declarat namespaces, retorna la uri de l'element.
   * @param localName
   *          --> Nom de l'element en el namespace.
   * @param qName
   *          --> Nom del tag.
   * @param attributes
   *          --> Objecte de tipus "Attributes" en forma de HashMap amb els
   *          atributs de l'etiqueta i el seu valor.
   */
  public final void startElement(final String uri, final String localName,
      final String qName, final Attributes attributes) {

    if (localName == "match") {
      equips = new ArrayList<Equip>();

    }
    if (localName == "team") {

      String nom = "";
      String resultat = "";

      for (int i = 0; i < attributes.getLength(); i++) {
        if (attributes.getQName(i) == "name") {
          nom = attributes.getValue(i);

        }
        if (attributes.getQName(i) == "score") {
          resultat = attributes.getValue(i);
        }
      }
      Equip equip = new Equip(nom, resultat);
      equips.add(equip);
    }

  }

  /**
   * Mètode que es dispara quan finalitza un element.
   * 
   * @param uri
   *          --> Si hem declarat namespaces, retorna la uri de l'element.
   * @param localName
   *          --> Nom de l'element en el namespace.
   * @param qName
   *          -->Nom del tag.
   */
  public final void endElement(final String uri, final String localName,
      final String qName) {
    if (localName == "match") {
      comprovaEquips(equips);
      comprovaPuntuacions(equips);
    }
  }

  /**
   * Mètode per comprovar si els equips que disputen un partit han estat afegits
   * a la clasificació general.
   * 
   * @param equipsA
   *          --> ArrayList amb els dos equips que han disputat l'últim partit.
   */
  public final void comprovaEquips(final ArrayList<Equip> equipsA) {
    boolean equipControlat = false;
    for (int i = 0; i < equipsA.size(); i++) {

      for (int j = 0; j < clasificacio.size(); j++) {
        if (equipsA.get(i).getNom().equals(clasificacio.get(j).getNom())) {
          equipControlat = true;
        }

      }

      if (!equipControlat) {
        afegirEquip(equipsA.get(i));
      }
    }

  }

  /**
   * Mètode per comparar les puntuacions dels dos equips que han jugat un
   * partit.
   * 
   * @param equipsA
   *          --> Equips que han jugat el partit.
   */
  public final void comprovaPuntuacions(final ArrayList<Equip> equipsA) {
    String nomEquip1 = equipsA.get(0).getNom();
    int resultatEquip1 = Integer.parseInt(netejaEspais(equipsA.get(0)
        .getResultat()));
    String nomEquip2 = equipsA.get(1).getNom();
    int resultatEquip2 = Integer.parseInt(netejaEspais(equipsA.get(1)
        .getResultat()));

    if (resultatEquip1 > resultatEquip2) {
      afegirVictoria(nomEquip1);
      afegirDerrota(nomEquip2);
    }
    if (resultatEquip1 < resultatEquip2) {
      afegirVictoria(nomEquip2);
      afegirDerrota(nomEquip1);
    }
    if (resultatEquip1 == resultatEquip2) {
      afegirEmpat(nomEquip1, nomEquip2);
    }

  }

  /**
   * Mètode per afegir una victoria a un equip.
   * 
   * @param nomEquip
   *          --> Nom de l'equip guanyador.
   */
  public final void afegirVictoria(final String nomEquip) {
    for (int i = 0; i < clasificacio.size(); i++) {
      if (clasificacio.get(i).getNom().equals(nomEquip)) {
        clasificacio.get(i)
            .setVictories(clasificacio.get(i).getVictories() + 1);
        clasificacio.get(i).setPunts(clasificacio.get(i).getPunts() + 2);
      }
    }
  }

  /**
   * Mètode per afegir empats.
   * 
   * @param nomEquip1
   *          --> Nom de l'equip 1.
   * @param nomEquip2
   *          --> Nom de l'equip 2.
   */
  public final void afegirEmpat(final String nomEquip1,
      final String nomEquip2) {
    for (int i = 0; i < clasificacio.size(); i++) {
      if (clasificacio.get(i).getNom().equals(nomEquip1)
          || clasificacio.get(i).getNom().equals(nomEquip2)) {
        clasificacio.get(i).setEmpats(clasificacio.get(i).getEmpats() + 1);
        clasificacio.get(i).setPunts(clasificacio.get(i).getPunts() + 1);
      }
    }

  }

  /**
   * Mètode per afegir una derrota a un equip.
   * 
   * @param nomEquip
   *          --> Nom de l'equip derrotat.
   */
  public final void afegirDerrota(final String nomEquip) {
    for (int i = 0; i < clasificacio.size(); i++) {
      if (clasificacio.get(i).getNom().equals(nomEquip)) {
        clasificacio.get(i).setDerrotes(clasificacio.get(i).getDerrotes() + 1);

      }
    }
  }

  /**
   * Mètode per afegir un equip a l'arraylist de clasificació.
   * 
   * @param equip
   *          --> Equip a afegir.
   */
  public final void afegirEquip(final Equip equip) {
    clasificacio.add(new DetallEquip(equip.getNom()));
  }

  /**
   * Mètode per obtenir la classificació en un ArrayList d'objectes
   * "DetallEquip".
   * 
   * @return --> Retorna un ArrayList amb les dades corresponents a la
   *         sclasificació.
   */
  public final ArrayList<DetallEquip> getClasificacio() {
    return clasificacio;
  }

  /**
   * Mètode per netejar els espais d'una linia.
   * 
   * @param linia
   *          --> Linia amb espais.
   * @return --> Retorna la linia sense espais.
   */
  public final String netejaEspais(final String linia) {
    char espai = ' ';
    String neta = "";
    for (int i = 0; i < linia.length(); i++) {
      if (linia.charAt(i) != espai) {
        neta += linia.charAt(i);
      }
    }
    return neta;
  }
}
