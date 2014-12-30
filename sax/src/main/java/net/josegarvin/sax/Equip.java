package net.josegarvin.sax;

/**
 * Classe per crear objectes de tipus "Equip".
 * 
 * @author Jose Garvin Victoria.
 *
 */
public class Equip {

  /**
   * Nom de l'equip.
   */
  private String nom;

  /**
   * Resultat de l'equip.
   */
  private String resultat;

  /**
   * Constructor per crear objectes de tipus "Equip".
   * 
   * @param nomEquip
   *          --> Nom de l'equip.
   * @param resultatEquip
   *          --> Resultat de l'equip.
   */
  Equip(final String nomEquip, final String resultatEquip) {
    this.nom = nomEquip;
    this.resultat = resultatEquip;
  }

  /**
   * Mètode per obtenir el valor de la propietat "nom" de l'objecte.
   * 
   * @return --> Retorna el valor de la propietat "nom" de l'objecte.
   */
  public final String getNom() {
    return nom;
  }

  /**
   * Mètode per assignar un valor a la propietat "nom" de l'objecte.
   * @param nomE --> Nom a assignar.
   */
  public final void setNom(final String nomE) {
    this.nom = nomE;
  }

  /**
   * Mètode per obtenir el valor a la propietat "resultat" de l'objecte.
   * @return --> Retorna el valor de la propietat "resultat" de l'objecte.
   */
  public final String getResultat() {
    return resultat;
  }

  /**
   * Mètode per assignar un valor a la propietat "resultat" de l'objecte.
   * @param resultatE --> Resultat a assignar.
   */
  public final void setResultat(final String resultatE) {
    this.resultat = resultatE;
  }

}
