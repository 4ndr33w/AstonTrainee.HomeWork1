package Resources;

public class StaticResources {

    public static final double TRANSACTION_LIMIT = Double.parseDouble(Configuration.getProperties().getProperty("TRANSACTION_LIMIT"));
    public static final double CREDIT_LIMIT = Double.parseDouble(Configuration.getProperties().getProperty("CREDIT_LIMIT"));
    public static final double TRANSACTION_FEE= Double.parseDouble(Configuration.getProperties().getProperty("TRANSACTION_FEE"));
    public static final double INTEREST_RATE= Double.parseDouble(Configuration.getProperties().getProperty("INTEREST_RATE"));

    private static final String dbDataProvider = Configuration.getProperties().getProperty("DatabaseDataProvider");
    private static final String dbHost= Configuration.getProperties().getProperty("DatabaseHost");
    private static final String dbPort= Configuration.getProperties().getProperty("DatabasePort");
    private static final String dbName = Configuration.getProperties().getProperty("DatabaseName");

    public static final String datasourceUrl = dbDataProvider + "://" + dbHost + ":" + dbPort + "/" + dbName;

    public static final String dbUserName = Configuration.getProperties().getProperty("DatabaseUsername");
    public static final String dbPassword = Configuration.getProperties().getProperty("DatabasePassword");

    public static final String dbClientsTableName = Configuration.getProperties().getProperty("DatabaseClientsTableName");
}

