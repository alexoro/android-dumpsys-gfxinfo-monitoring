package android.gfxinfo.reader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: UAS
 * Date: 24.10.13
 * Time: 1:36
 */
public class GfxInfoReader {

    public Dump read(String deviceId, String appPackage) throws IOException, ParseException {
        String cmd = String.format(
                "adb shell dumpsys gfxinfo %s",
                appPackage);

        InputStream is = Runtime.getRuntime().exec(cmd).getInputStream();
        String body = readFromStream(is);
        is.close();

        LinkedList<DumpItem> items = new LinkedList<DumpItem>();
        Pattern p = Pattern.compile("(\\s+[0-9,.]{4})(\\s+[0-9,.]{4})(\\s+[0-9,.]{4})", Pattern.MULTILINE);
        Matcher m = p.matcher(body);
        DecimalFormat df = new DecimalFormat();
        while (m.find()) {
            items.add(new DumpItem(
                    df.parse(m.group(1).trim()).doubleValue(),
                    df.parse(m.group(2).trim()).doubleValue(),
                    df.parse(m.group(3).trim()).doubleValue()));
        }

        return new Dump(items);
    }

    protected String readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        for (int len;(len = is.read(bytes))>0;) {
            baos.write(bytes, 0, len);
        }
        return new String(baos.toByteArray(), "UTF-8");
    }

}