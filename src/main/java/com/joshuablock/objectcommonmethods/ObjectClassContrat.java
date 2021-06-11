package com.joshuablock.objectcommonmethods;

import java.awt.*;
import java.util.Objects;

/**
 * Item 10: Obey the general contract when overriding equals
 *
 * Bien a faire mais peu engendrer de nombreux problemes
 * Regles a respecter pour bien le faire :
 * -  Each instance of the class is inherently unique.
 * -  There is no need for the class to provide a “logical equality” test.
 * -  A superclass has already overridden equals, and the superclass behavior
 * is appropriate for this class. En general, les methodes equals des classes meres sont suffisantes (super.equals() )
 * -  The class is private or package-private, and you are certain that its equals
 * method will never be invoked.
 * Egalité "logique" et egalite de valeur
 */
class ObjectClassContrat {
    /**
     * regles pour la methode Equals
     * reflexive : x.equals(x) doit etre true
     * symetrique : x.equals(y) et y.equals(x) doivent avoir le meme resultat
     * transitive : si x.equals(y) et y.equals(z) alors x.equals(z)
     * consistent (idempotence) : X appels de x.equals(y) doivent renvoyer la meme reponse = ne pas ecrire un equals basé sur des attributs dont la valeurs est non fiable
     * x.equals(null) --> false
     */
    // Broken - violates symmetry!
    static final class CaseInsensitiveString {
        private final String s;
        public CaseInsensitiveString(String s) {
            this.s = Objects.requireNonNull(s);
        }
        // Broken - violates symmetry!
        @Override public boolean equals(Object o) {
            if (o instanceof CaseInsensitiveString)
                return s.equalsIgnoreCase(
                        ((CaseInsensitiveString) o).s);
            if (o instanceof String) // One-way interoperability!
                return s.equalsIgnoreCase((String) o);
            return false;
        }
        //... // Remainder omitted
    }
    public static void main(String[] args){
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(cis.equals(s)); //return true as expected
        System.out.println(s.equals(cis)); //false donc pas symetrique

        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3)); //false au lieu de true - violation transitivité
    }

    static class ColorPoint extends Point {
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        // Broken - violates transitivity!
        @Override public boolean equals(Object o) {
            if (!(o instanceof Point))
                return false;
            // If o is a normal Point, do a color-blind comparison
            if (!(o instanceof ColorPoint))
                return o.equals(this);
            // o is a ColorPoint; do a full comparison
            return super.equals(o) && ((ColorPoint) o).color == color;
        }
    }

    /**
     * ecrire un bon equals
     * 1. Use the == operator to check if the argument is a reference to this object
     * 2. Use the instanceof operator to check if the argument has the correct type.
     * 3. Cast the argument to the correct type.
     * 4. For each “significant” field in the class, check if that field of the argument
     * matches the corresponding field of this object
     * When you are finished writing your equals method, ask yourself three
     * questions: Is it symmetric? Is it transitive? Is it consistent?
     */

    // Class with a typical equals method
    static final class PhoneNumber {
        private final short areaCode, prefix, lineNum;
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
            PhoneNumber pn = (PhoneNumber)o;
            return pn.lineNum == lineNum && pn.prefix == prefix
                    && pn.areaCode == areaCode;
        }
    }
}
