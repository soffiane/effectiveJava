package betterprogramming.polymorphismToLambdas;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Exemple basique de fonctions polymorphiques
 * <p>
 * Probleme ici : si je veux ajouter un animal, il faut une nouvelle classe = boilerplate
 */
class Application {
    static abstract class Pet {
        public abstract String vocalize();

        public void disturb() {
            System.out.println(vocalize());
        }
    }

    static class Dog extends Pet {
        public String vocalize() {
            return "bark";
        }
    }

    static class Cat extends Pet {
        public String vocalize() {
            return "meow";
        }
    }

    public static void main(String[] args) {
        Pet cat = new Cat();
        Pet dog = new Dog();
        cat.disturb();
        dog.disturb();
    }
}

/**
 * meme chose mais avec des Functions et une interface pour pouvoir ajouter des animaux facilement
 */
class ApplicationFunction {
    interface Pet {
        String vocalize();
    }

    static void disturbPet(Pet p) {
        System.out.println(p.vocalize());
    }

    public static void main(String[] args) {
        Pet cat = () -> "meow";
        Pet dog = () -> "bark";
        Pet snake = () -> "hiss";
        disturbPet(cat);
        disturbPet(dog);
        disturbPet(snake);
    }
}

/**
 * on peut encore reduire le code precedent de cette maniere
 * de telle sorte a avoir une fonction qui prend un Supplier
 */
class ApplicationSupplier {
    //Supplier est la Function qui ne prend aucun parametre et renvoie un resultat, ici un String
    static void disturbPet(Supplier<String> petVocalization) {
        System.out.println(petVocalization.get());
    }

    public static void main(String[] args) {
        disturbPet(() -> "meow");
        disturbPet(() -> "bark");
        disturbPet(() -> "hiss");

        List<Supplier<String>> yourPetVocalizations = Arrays.asList(
                () -> "bark",
                () -> "meow",
                () -> "hiss");
        yourPetVocalizations.forEach(ApplicationSupplier::disturbPet);

        readFileAndDisplayAnimals();
    }

    /**
     * Lire un fichier et afficher la liste des animaux dedans
     */
    private static void readFileAndDisplayAnimals() {
        File dir = new File(".");
        getFiles(dir)
                .map(f -> f.getAbsolutePath()
                        .substring(dir.getAbsolutePath().length())
                        + " " + f.length())
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * parcourir arboresence de fichier jusqu'a trouver le fichier
     *
     * @param file
     * @return
     */
    private static Stream<File> getFiles(File file) {
        return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(ApplicationSupplier::isValidFile)
                .flatMap(f -> {
                    if (f.isDirectory()) {
                        return getFiles(f);
                    } else {
                        return Stream.of(f);
                    }
                });
    }

    private static boolean isValidFile(File f) {
        return f != null && !f.getName().startsWith(".");
    }
}