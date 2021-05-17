package com.joshuablock.createdestroyobjects;

/**
 * Item 2 : Consider a builder when faced with many constructor parameters
 */
//pattern permettant de construire un objet qui a beaucoup d'attributs
//evite l'antipattern "Telescoping constructor pattern"
public class BuilderPattern {}
	// Telescoping constructor pattern - does not scale well!
	//on crée tous les constructeur possibles avec les attributs
	//On instancie un objet comme ca : NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35, 27);
	//autre possibilité : Constructeur par defaut + setter pour ajouter les attributs dans l'objet = JavaBeans Pattern
	//mieux mais beaucoup d'appel de methode pour construire l'objet et on ne peut pas construire un objet immutable facilement
	//on instancie la classe puis on sette les attributs
	/* NutritionFacts2 cocaCola = new NutritionFacts2();
		cocaCola.setServingSize(240);
		cocaCola.setServings(8);
		cocaCola.setCalories(100);
		cocaCola.setSodium(35);
		cocaCola.setCarbohydrate(27);*/

	// Builder Pattern
	class NutritionFacts {
		private final int servingSize;
		private final int servings;
		private final int calories;
		private final int fat;
		private final int sodium;
		private final int carbohydrate;
		public static class Builder {
			// Required parameters
			private final int servingSize;
			private final int servings;
			// Optional parameters - initialized to default values
			private int calories = 0;
			private int fat = 0;
			private int sodium = 0;
			private int carbohydrate = 0;
			public Builder(int servingSize, int servings) {
				this.servingSize = servingSize;
				this.servings = servings;
			}
			public Builder calories(int val)
			{ calories = val; return this; }
			public Builder fat(int val)
			{ fat = val; return this; }
			public Builder sodium(int val)
			{ sodium = val; return this; }
			public Builder carbohydrate(int val)
			{ carbohydrate = val; return this; }
			public NutritionFacts build() {
				return new NutritionFacts(this);
			}
		}
		private NutritionFacts(Builder builder) {
			servingSize = builder.servingSize;
			servings = builder.servings;
			calories = builder.calories;
			fat = builder.fat;
			sodium = builder.sodium;
			carbohydrate = builder.carbohydrate;
		}

		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
				.calories(100).sodium(35).carbohydrate(27).build();
	}