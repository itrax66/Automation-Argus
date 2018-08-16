package Utils;

public class CharRemover {

    public static String removeCharAtString(String s,int index){
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(index);
        sb.delete(0,index);
        return sb.toString();
    }

}
