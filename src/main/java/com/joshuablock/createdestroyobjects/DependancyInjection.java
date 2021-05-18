package com.joshuablock.createdestroyobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Item 5 : prefer dependancy injection to hardwiring resources
 * Dans le cas ou une classe depends d'une autre classe pour sa construction
 */

class Lexicon {
}

// Inappropriate use of static utility - inflexible & untestable!
class SpellChecker {
	private static final Lexicon dictionary = new Lexicon();
	private SpellChecker() {
	} // Noninstantiable
	public static boolean isValid(String word) {
		return false;
	}
	public static List<String> suggestions(String typo) {
		return new ArrayList<>();
	}
}

// Inappropriate use of singleton - inflexible & untestable!
class SpellChecker2 {
	private final Lexicon dictionary = new Lexicon();
	private SpellChecker2() {}
	public static final SpellChecker2 INSTANCE = new SpellChecker2();
	public boolean isValid(String word) { return false; }
	public List<String> suggestions(String typo) { return new ArrayList<>(); }
}
//Static utility classes and singletons are inappropriate for
//classes whose behavior is parameterized by an underlying resource.
// Dependency injection provides flexibility and testability
class SpellChecker3 {
	private final Lexicon dictionary;
	public SpellChecker3(Lexicon dictionary) {
		this.dictionary = Objects.requireNonNull(dictionary);
	}
	public boolean isValid(String word) { return false; }
	public List<String> suggestions(String typo) { return new ArrayList<>(); }
}
//astuce utile : passer des objets venant d'une Factory au constructeur
/**
 * In summary, do not use a singleton or static utility class to implement a class
 * that depends on one or more underlying resources whose behavior affects that of
 * the class, and do not have the class create these resources directly. Instead, pass
 * the resources, or factories to create them, into the constructor (or static factory or
 * builder). This practice, known as dependency injection, will greatly enhance the
 * flexibility, reusability, and testability of a class
 */
