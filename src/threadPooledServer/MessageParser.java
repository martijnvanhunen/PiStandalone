package threadPooledServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class MessageParser implements Runnable {
    private final BlockingQueue parsedMessageQueue;
    BlockingQueue incomingMessageQueue;

    public MessageParser(BlockingQueue incomingMessageQueue, BlockingQueue parsedMessageQueue) {
        this.incomingMessageQueue = incomingMessageQueue;
        this.parsedMessageQueue = parsedMessageQueue;
    }

    public void run() {
        while (true) {
            try {
                byte[] nextMessage = (byte[]) incomingMessageQueue.poll(5, TimeUnit.NANOSECONDS);
                if (nextMessage == null) {
                    Thread.sleep(5);
                } else {
                    parse(nextMessage);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

        private void parse(byte[] nextMessage){
            List<byte[]> values = new ArrayList<>();
            Integer index = 0;
            for (int field = 1; field <= 14; field++) {
                byte[] data = new byte[10];
                index = findNextValue(nextMessage, data, field, index);
                values.add(data);
            }
            try {
                parsedMessageQueue.put(values);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int findNextValue ( byte[] nextMessage, byte[] data, int field, Integer index){
            switch (field) {
                case 1:
                    break;
                case 2:
                case 6:
                    index += 15;
                    break;
                case 3:
                case 4:
                case 5:
                case 8:
                case 10:
                case 11:
                    index += 16;
                    break;
                case 7:
                    index += 14;
                    break;
                case 9:
                    index += 17;
                    break;
                case 12:
                case 13:
                case 14:
                    index += 18;
                    break;
            }
            byte a = nextMessage[index];
            int x = 0;
            while (a != 60 && x != 10) {
                index++;
                data[x++] = a;
                a = nextMessage[index];
            }
            return index;
        }
    }

