package android.gfxinfo.reader;

import java.util.LinkedList;

/**
 * User: UAS
 * Date: 24.10.13
 * Time: 1:38
 */
public class Dump {

    private LinkedList<DumpItem> mDumpItems = new LinkedList<DumpItem>();
    private int mFramesCount = 0;
    private double mAvgDraw = 0.0;
    private double mAvgProcess = 0.0;
    private double mAvgExecute = 0.0;
    private double mAvgPerFrame = 0.0;
    private double mAvgMaxFrames = 0.0;

    public Dump(LinkedList<DumpItem> dump) {
        if (!dump.isEmpty()) {
            double[] avg = new double[] {0.0, 0.0, 0.0, 0.0};

            for (DumpItem item: dump) {
                avg[0] += item.draw;
                avg[1] += item.process;
                avg[2] += item.execute;
                avg[3] += item.draw + item.process + item.execute;
            }

            mDumpItems = dump;
            mFramesCount = dump.size();
            mAvgDraw = avg[0] / dump.size();
            mAvgProcess = avg[1] / dump.size();
            mAvgExecute = avg[2] / dump.size();
            mAvgPerFrame = avg[3] / dump.size();
            mAvgMaxFrames = 60 * 16 / mAvgPerFrame;
        }
    }

    public LinkedList<DumpItem> items() {
        return mDumpItems;
    }

    public int framesCount() {
        return mFramesCount;
    }

    public double avgDraw() {
        return mAvgDraw;
    }

    public double avgProcess() {
        return mAvgProcess;
    }

    public double avgExecute() {
        return mAvgExecute;
    }

    public double avgPerFrame() {
        return mAvgPerFrame;
    }

    public double avgMaxFrames() {
        return mAvgMaxFrames;
    }

}