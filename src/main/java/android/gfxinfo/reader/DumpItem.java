package android.gfxinfo.reader;

/**
 * User: UAS
 * Date: 24.10.13
 * Time: 1:36
 */
public class DumpItem {

    final public double draw;
    final public double process;
    final public double execute;

    public DumpItem(double draw, double process, double execute) {
        this.draw = draw;
        this.process = process;
        this.execute = execute;
    }

}