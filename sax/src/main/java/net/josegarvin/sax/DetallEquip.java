package net.josegarvin.sax;

/**
 * Classe per crear objectes de tipus "DetallEquip".
 * 
 * @author Jose Garvin Victoria.
 *
 */
public class DetallEquip {

  /**
   * Nom de l'equip.
   */
  private String nom;

  /**
   * Numero de victories.
   */
  private Integer victories;

  /**
   * Numero d'empats.
   */
  private Integer empats;

  /**
   * Numero de derrotes.
   */
  private Integer derrotes;

  /**
   * Numero de punts.
   */
  private Integer punts;

  /**
   * Constructor per crear objectes de tipus "DetallEquip".
   * 
   * @param nomEquip
   *          --> Nom de l'equip.
   */
  DetallEquip(final String nomEquip) {
    this.nom = nomEquip;
    this.victories = 0;
    this.empats = 0;
    this.derrotes = 0;
    this.punts = 0;
  }

  /**
   * Mètode per obtenir les victories.
   * 
   * @return --> Retorna el numero de victories de l'objecte.
   */
  public final Integer getVictories() {
    return victories;
  }

  /**
   * Mètode per assignar victories.
   * 
   * @param victoriesE
   *          --> Numero de victories a assignar.
   */
  public final void setVictories(final Integer victoriesE) {
    this.victories = victoriesE;
  }

  /**
   * Mètode per obtenir els empats.
   * 
   * @return --> Retorna el numero d'empats de l'objecte.
   */
  public final Integer getEmpats() {
    return empats;
  }

  /**
   * Mètode per assignar els empats.
   * 
   * @param empatsE
   *          --> Numero d'empats a assignar.
   */
  public final void setEmpats(final Integer empatsE) {
    this.empats = empatsE;
  }

  /**
   * Mètode per obtenir les derrotes.
   * 
   * @return --> Retorna el numero de de derrotes.
   */
  public final Integer getDerrotes() {
    return derrotes;
  }

  /**
   * Mètode per assignar derrotes.
   * 
   * @param derrotesE
   *          --> Mètode de victories a assignar.
   */
  public final void setDerrotes(final Integer derrotesE) {
    this.derrotes = derrotesE;
  }

  /**
   * Mètode per obtenir els punts.
   * 
   * @return --> Retorna el numero de punts de l'objecte.
   */
  public final Integer getPunts() {
    return punts;
  }

  /**
   * Metode per assignar punts.
   * 
   * @param puntsE
   *          --> Numero de punts a assignar.
   */
  public final void setPunts(final Integer puntsE) {
    this.punts = puntsE;
  }

  /**
   * Mètode per obtenir el nom.
   * 
   * @return --> Retorna el valor de la propietat nom de l'objecte.
   */
  public final String getNom() {
    return nom;
  }

  /**
   * Mètode per assignar un nom.
   * 
   * @param nomE
   *          --> Nom a assignar a l'objecte.
   */
  public final void setNom(final String nomE) {
    this.nom = nomE;
  }

}
