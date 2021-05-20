package com.joshuablock.createdestroyobjects;



/**
 * Item 8: Avoid finalizers and cleaners
 *
 * Les finalizers sont imprevisibles, dangereux et souvent inutiles
 * Les cleaners qui remplacent les finalizers depreciés sont moins dangereux mais quand meme
 * imprevisibles. Ne pas faire de traitement temps-réel dans un finalizer/cleaner
 * Utiliser plutot l'interface AutoCloseable
 *
 * Certaines classes ont des finalizer qui servent de filet de securité : FileInputStream, FileOutputStream, ThreadPoolExecution, sql.Connection...
 */
public class AvoidFinalizersAndCleaners {
}

// An autocloseable class using a cleaner as a safety net
//exemple java 9
class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    // Resource that requires cleaning. Must not refer to Room!
    private static class State implements Runnable {
        int numJunkPiles; // Number of junk piles in this room
        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }
        // Invoked by close method or cleaner
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }
    // The state of this room, shared with our cleanable
    private final State state;
    // Our cleanable. Cleans the room when it’s eligible for gc
    private final Cleaner.Cleanable cleanable;
    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }
    @Override
    public void close() {
        cleanable.clean();
    }
}
