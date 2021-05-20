package com.joshuablock.createdestroyobjects;

import java.io.*;

/**
 * Item 9: Prefer try-with-resources to try-finally
 *
 * Conseil : une classe qui manipule des resources qui doivent etre fermées
 * devrait implementer AutoCloseable
 */
public class TryWithRessourcesBlock implements AutoCloseable{

    // try-finally - No longer the best way to close resources!
    //historique
    // les block try et finally sont susceptible de lancer des exceptions
    //ce qui fait que la deuxieme exception va masquer la premiere et on aura
    //pas la vraie cause
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    // try-finally is ugly when used with more than one resource!
    //un bloc try par ressource = moche
    static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[10];
                int n;
                while ((n = in.read(buf)) >= 0)
                    out.write(buf, 0, n);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }


    // try-with-resources - the the best way to close resources!
    //pas besoin du bloc finally pour close les resources
    static String firstLineOfFileBis(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        }
    }

    //And here’s how our second example looks using try-with-resources:
    // try-with-resources on multiple resources - short and sweet
    static void copyBis(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[10];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }
    }

    // try-with-resources with a catch clause
    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }

    @Override
    public void close() throws Exception {

    }
}
