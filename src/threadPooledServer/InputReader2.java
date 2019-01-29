/**package threadPooledServer;

import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import jdk.internal.util.xml.impl.Input;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import resources.ExecutorServices;
import resources.Settings;
import java.util.concurrent.ScheduledFuture;

public class InputReader2 implements Runnable{
    private BufferedReader bufferedReader = null;
    private Boolean stopped = false;
    private ScheduledFuture<?> scheduledFuture = null;
    private InputSource is = null;

    InputReader2(BufferedReader bufferedReader) {

        this.bufferedReader = bufferedReader;
        InputSource is = new InputSource(bufferedReader);
    }
    public void run() {
        try {
            String inputLine;
            if ((inputLine = bufferedReader.readLine()) == null) {
                System.out.println("lost connection");
                stop();
            }
        } catch (IOException e) {
            stop();
            System.out.println("Exception caught when trying to listen on port "
                    + "xxx" + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    public void setScheduledFuture(ScheduledFuture<?> scheduledFuture){this.scheduledFuture=scheduledFuture;}


    public void main( String[] args )
    {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();


            DefaultHandler handler = new DefaultHandler() {

                boolean STN = false;
                boolean DATE = false;
                boolean TIME = false;
                boolean TEMP = false;

                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes)
                        throws SAXException {

                    System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("STN")) {
                        STN = true;
                    }

                    if (qName.equalsIgnoreCase("DATE")) {
                        DATE = true;
                    }

                    if (qName.equalsIgnoreCase("TIME")) {
                        TIME = true;
                    }

                    if (qName.equalsIgnoreCase("TEMP")) {
                        TEMP = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName)
                        throws SAXException {

                    System.out.println("End Element :" + qName);

                }

                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    System.out.println(new String(ch, start, length));


                    if (STN) {
                        System.out.println("Station : "
                                + new String(ch, start, length));
                        STN = false;
                    }

                    if (DATE) {
                        System.out.println("Date : "
                                + new String(ch, start, length));
                        DATE = false;
                    }

                    if (TIME) {
                        System.out.println("Time : ");
                        TIME = false;
                    }

                    if (TEMP) {
                        System.out.println("Temp : "
                                + new String(ch, start, length));
                        TEMP = false;
                    }

                }

            };

            saxParser.parse(is, handler);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void stop() {
        if (!stopped) {
            stopped = true;
            this.scheduledFuture.cancel(false);
            ExecutorServices.connections--;

            if (!ThreadPooledServerRunner.isRunning) {
                ThreadPooledServerRunner.isRunning = true;
                new Thread(ThreadPooledServerRunner.server).start();
            }
        }
    }
}
 **/