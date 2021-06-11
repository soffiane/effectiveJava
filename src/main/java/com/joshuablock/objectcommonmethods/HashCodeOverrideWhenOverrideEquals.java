package com.joshuablock.objectcommonmethods;

import java.util.Objects;

/**
 * Item 11: Always override hashCode when you override equals
 *
 * Equals object must have equals hashcode
 */
public class HashCodeOverrideWhenOverrideEquals {

}

final class PhoneNumber {
    final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    //toujours Object en parametre et pas autre chose
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNum == lineNum && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    /**
     * ecrire une bonne methode hashCode qui doit renvoyer des hashCode differents pour des instances différentes
     * 1) declarer un int result et l'initialiser avec la valeur du hashCode du champ le plus significatif de l'objet
     * == un champ qui affecte le equals
     * 2/ pour les autres champ de l'objet, on calcule le hashCode du champ
     * si c'est un primitif, on fait Type.hashCode(f) sinon appel recursif du hashCode ou Arrays.hashCode pour les tableaux
     * puis on somme avec le result de depart
     */

    // Typical hashCode method
    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    /**
     * autre possibilité mais mauvaises performance
     */
    @Override public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
    }
}
