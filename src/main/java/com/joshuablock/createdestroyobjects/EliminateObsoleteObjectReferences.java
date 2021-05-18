package com.joshuablock.createdestroyobjects;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Item 7: Eliminate obsolete object references
 *
 * A chaque fois qu'un programmeur travaille sur un objet qui gere sa propre memoire (comme une Collection)
 * il doit etre vigilant aux memory leaks
 * D'autres sources de memory leaks : les caches et les listeners et callbacks ==> WeakhashMap
 * concept de strong, soft et weak references
 * Strong reference : non eligible pour le GC
 * Soft reference : eligible pour le GC en cas de besoin
 * Weak reference : le GC peut le nettoyer sans attendre d'en avoir besoin
 */
public class EliminateObsoleteObjectReferences {
    //implementation mauvaise d'une stack
    // Can you spot the "memory leak"?
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;
        public EliminateObsoleteObjectReferences() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }
        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }
        public Object pop() {
            if (size == 0)
                throw new EmptyStackException();
            /**
             * ici on sort un element de la stack mais l'element devenu obsolete est toujours
             * dans la java stack et ne sont pas nettoyés par le GC ==>  unintentional object retention
             * Il faut forcer manuellement le dereferencement de l'objet sorti de la stack
             *  return elements[--size];
             */
            Object result = elements[--size];
            elements[size] = null; // Eliminate obsolete reference
            return result;
            /**
             * mais attention avec cette pratique, a ne pas en abuser
             */
        }
        /**
         * Ensure space for at least one more element, roughly
         * doubling the capacity each time the array needs to grow.
         */
        private void ensureCapacity() {
            if (elements.length == size)
                elements = Arrays.copyOf(elements, 2 * size + 1);
        }
}
