package FileManagers;

public class RunProperties {

    //This class holds the properties for the project
    public static String getProperty(String property){
        return JsonParser.getInstance().getValueFromString(property);
    }
    public static String getUrl(){
        return getProperty("url");
    }
    public static long getImplicitInSeconds(){return Long.parseLong(getProperty("implicitInSeconds"));}
    public static long getPageToLoadInSeconds(){return Long.parseLong(getProperty("pageToLoadInSeconds"));}
    public static long getExplicitInSeconds(){return Long.parseLong(getProperty("explicitInSeconds"));}
    public static String getDbUrl(){return getProperty("DbUrl");}
    public static String getDbPort(){return getProperty("DbPort");}
    public static String getDbUser(){return getProperty("DbUser");}
    public static String getDbPassword(){return getProperty("DbPassword");}
    public static String getDatabaseName(){return getProperty("DataBaseName");}




}
