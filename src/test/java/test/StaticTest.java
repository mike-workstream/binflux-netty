package test;

import eu.binflux.netty.endpoint.EndpointBuilder;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticTest {

    public static EndpointBuilder BUILDER;

    public static RandomRequest RANDOM_REQUEST;
    public static String testString;
    public static long testLong;
    public static byte[] testBytes;

    static {
        BUILDER = EndpointBuilder.newBuilder().eventExecutor(5);

        Random random = new Random();
        testBytes = new byte[1000];
        random.nextBytes(testBytes);

        testString = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat";
        testLong = System.currentTimeMillis();

        RANDOM_REQUEST = new RandomRequest(testString, testLong, testBytes);
    }

    public static int getPacketsPerSec(int amount, long time) {
        return Math.round(amount / (time * (1 / 1000000000f)));
    }

    public static void adjustAverage(AtomicInteger average, int packetsPerSec) {
        if(average.get() != 0) {
            average.set(Math.round(packetsPerSec + average.get()) / 2);
        } else {
            average.set(packetsPerSec);
        }
    }

    public static void printByteArray(String prefix, byte[] bytes) {
        StringBuilder setBuilder = new StringBuilder();
        if (bytes != null) {
            for (byte cont : bytes) {
                setBuilder.append(cont).append(" ");
            }
        }
        System.out.println(prefix + setBuilder.toString());
    }
}
