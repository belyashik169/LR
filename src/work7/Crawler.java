package work7;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Crawler {

    private String prefix = "http";
    private int depth;
    private String host;

    private LinkedList<URLDepthPair> checked = new LinkedList<>();
    private LinkedList<URLDepthPair> unchecked = new LinkedList<>();

    public Crawler(String host, int depth) {
        this.host = host;
        this.depth = depth;
        unchecked.add(new URLDepthPair(this.host, this.depth));
    }

    public void Checking() throws IOException {
        while (unchecked.size() > 0) {
            Search(unchecked.removeFirst());
        }
        int i = 0;
        for (URLDepthPair list : checked) System.out.println(++i + ": " + list.getURL() + " [" + list.getDepth() + "]");
    }


    public void Search(URLDepthPair pair) throws IOException {
        checked.add(pair);
        if (pair.getDepth() == 0) return;
        Socket socket = new Socket(pair.getWebHost(), 80);
        socket.setSoTimeout(3000);

        PrintWriter myWriter = new PrintWriter(socket.getOutputStream(), true);
        myWriter.println("GET " + pair.getDocPath() + " HTTP/1.1");
        myWriter.println("Host: " + pair.getWebHost());
        myWriter.println("Connection: close");
        myWriter.println();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String input;
        //int i=0;
        while ((input = reader.readLine()) != null) {
            while (input.contains("a href=\"")) {
                String link;
                try {
                    input = input.substring(input.indexOf("a href=\"") + 8);
                    //System.out.println(++i + "#"+ input);
                    //link=input.substring(0, input.indexOf('\"'));
                    link = (input.startsWith(prefix)) ? input.substring(0, input.indexOf('\"')) : pair.getURL() + input.substring(1, input.indexOf('\"'));
                } catch (StringIndexOutOfBoundsException e) {
                    System.err.println(e);
                    break;
                }
                if (!(unchecked.contains(new URLDepthPair(link, 0)) | checked.contains(new URLDepthPair(link, 0))))
                    unchecked.add(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        reader.close();
        socket.close();
    }

    public static void main(String msi[]) throws IOException {
        //int i = 0;
        //msi = new String[]{"http://www.mtuci.ru/", "1", "http://isua-mtuci.ru/", "1", "http://www.tadviser.ru/", "1", "http://abiturient.isua-mtuci.ru", "1"};
        new Crawler(msi[0], Integer.parseInt(msi[1]))
                .Checking();
    }
}
