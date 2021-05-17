package com.joshuablock.createdestroyobjects;

/**
 * ITEM 1 : Consider static factory methods instead of constructors
 */
public class CreateDestroyObjects {

	public static void main(String[] args) {


		//une methode static qui retourne une instance de l'objet
		//exemple des classes Wrapper Type
		Boolean aTrue = Boolean.valueOf("true");
		//avantages : les methodes ont des noms contrairement au constructeur -> plus facile a lire
		//avec static method, on est pas obligé créer instance a chaque appel : pattern Singleton
		//par exemple Boolean.valueOf ne crée aucun objet
		//une methode static peut renvoyer un objet de n'importe quel type de l'arbre d'heritage de l'objet
		//contrairement au constructeur
		//inconvenient : une classe sans constructeur public ou protegé ne pourra pas etre herité
		//Date.from() EnumSet.of() Stream.of() getInstance() getType() Collections.list() String.valueOf()

		//il ne s'agit pas du pattern Factory Method -> voir dans FactoryMethodPattern.java
	}
}
