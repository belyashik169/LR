package work7;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class URLDepthPair {

    private String host;
    private int depth;
    private URL url;

    public URLDepthPair(String host, int depth) {
        this.host = host;
        this.depth = depth;
        try {
            url = new URL(host);
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
        }
    }

    public String getURL() {
        return host;
    }

    public int getDepth() {
        return depth;
    }

    public String getDocPath() {
        return url.getPath();
    }

    public String getWebHost() {
        return url.getHost();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof tt.URLDepthPair) {
            tt.URLDepthPair o = (tt.URLDepthPair) obj;
            return this.host.equals(o.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}