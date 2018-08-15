package FileManagers;

public class RunProperties {

    public static String getProperty(String property){
        return JsonParser.getInstance().getValueFromString(property);
    }
    public static String getUrl(){
        return getProperty("url");
    }
    public static long getImplicitInSeconds(){return Long.parseLong(getProperty("implicitInSeconds"));}
    public static long getPageToLoadInSeconds(){return Long.parseLong(getProperty("pageToLoadInSeconds"));}
    public static long getExplicitInSeconds(){return Long.parseLong(getProperty("explicitInSeconds"));}

}
