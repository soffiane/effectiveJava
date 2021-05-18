package com.joshuablock.createdestroyobjects;

import java.util.regex.Pattern;

/**
 * Item 6: Avoid creating unnecessary objects
 *
 * On peut eviter de créer des objets en utilisant les methodes static de factory de certaines classes (Item 1)
 */
public class AvoidCreatingUnnecessaryIbjects {

    public static void main(String... args){
        String s = new String("bikini"); // DON'T DO THIS! new String is redundant
        String s2 = "bikini";

    }

    // Performance can be greatly improved! String.matches n'est pas performante sur des appels recurrents
    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // Hideously slow! Can you spot the object creation?
    /**cette methode somme tous les int mais n'est pas performante car on
    *a declaré le Wrapper Type Long et pas le primitif long donc a chaque fois on crée un Long
    ==> prefer primitives to boxed primitives, and watch out for unintentional autoboxing   **/
    private static long sum() {
        //warning : type may be primitive
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
}

// Reusing expensive object for improved performance
class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"); //Pattern est immutable
    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
