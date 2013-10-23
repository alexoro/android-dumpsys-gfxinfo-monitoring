package android.gfxinfo.web;

import android.gfxinfo.reader.Dump;
import android.gfxinfo.reader.GfxInfoReader;

public class Main {

    public static void main(String[] args) {
//        File f = new File("/resources/sample.txt");
//        System.out.println(f.exists());
        new Main().run();
    }

    public void run() {
//        System.out.print(getClass().getResource("./resources/sample.txt"));
        //System.out.print(ClassLoader.);
        try {
            Dump dump = new GfxInfoReader().read("", "com.whatsapp");
            System.out.print(dump);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}