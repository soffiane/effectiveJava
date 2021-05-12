package com.joshuablock.createdestroyobjects;

/*
Un singleton est une classe qui ne peut avoir qu'une seule instance (ou objet Stateless)
Impossible de substituer un mock pour un singleton sauf si il implemente une interface
qui sert comme son type
 */
public class Singleton {

	//deux maniere de créer un singleton
	//1) constructeur privé + membre static final pour acceder à l'instance
	public static final Singleton INSTANCE = new Singleton();

	private Singleton() {
	}

	public void leaveTheBuilding() {
	}
	//plus simple et claire (on sait que c'est un singleton)
	//mais encore possible d'appeler le constructeur privé par reflexion : Java Reflection API
}

//approche avec static factory
//avantage : on peut en faire un prototype sans casser l'API
class Elvis {
	private static final Elvis INSTANCE = new Elvis();

	private Elvis() {
	}

	public static Elvis getInstance() {
		return INSTANCE;
	}

	public void leaveTheBuilding() {
	}

	//garder le caractere Singleton d'une classe serialisable
	//variable transient + readResolve method
	private Object readResolve() {
		// Return the one true Elvis and let the garbage collector
		// take care of the Elvis impersonator.
		return INSTANCE;
	}
}
//autre approche possible : enum singleton
enum ElvisEnum {
	INSTANCE;
	public void leaveTheBuilding() {}
}
