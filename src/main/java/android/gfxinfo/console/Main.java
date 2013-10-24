package android.gfxinfo.console;

import android.gfxinfo.reader.Dump;
import android.gfxinfo.reader.GfxInfoReader;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("You must specify one arg with package name of application");
        } else {
            new Main().run(args[0]);
        }
    }

    public void run(String packageName) {
        GfxInfoReader reader = new GfxInfoReader("", packageName);
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTaskImpl(reader), 0L, 5000L);
    }


    class TimerTaskImpl extends TimerTask {

        private GfxInfoReader mReader;

        TimerTaskImpl(GfxInfoReader reader) {
            mReader = reader;
        }

        @Override
        public void run() {
            try {
                Dump dump = mReader.read();
                String msg = String.format(
                        "Time: %d\nFrames: %d\n" +
                                "Draw: %.2f ms\nProcess: %.2f ms\nExecute: %.2f ms\n" +
                                "Per frame: %.2f ms\nMax frames: %d\n\n",
                        System.currentTimeMillis(),
                        dump.framesCount(),
                        dump.avgDraw(), dump.avgProcess(), dump.avgExecute(),
                        dump.avgPerFrame(), (int)dump.avgMaxFrames());
                System.out.println(msg);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }

}