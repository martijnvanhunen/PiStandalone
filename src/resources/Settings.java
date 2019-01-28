package resources;

public final class Settings {
    private Settings(){}

    public static final class ServerSettings{
        private ServerSettings(){}

        public static final int SERVER_PORT = 7789;
    }
    public  static  final class FileSettings{
        private FileSettings(){}

        //public static final String PATH = "/home/pi/rsync/"; //Path to measurement data
        public static final String PATH = "C:/temp/bla/"; //Path to measurement data
        public static final int MESSAGE_SIZE = 100; //amount of measurements from a cluster with each write(1 per file)
        public static final int CLUSTER_SIZE = 10;
        public static final int FILE_INTERVAL = 1; //how many minutes until a new file is created?
    }
}
