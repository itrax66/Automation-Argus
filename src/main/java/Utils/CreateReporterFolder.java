package Utils;

import java.io.File;

public class CreateReporterFolder {
    static CreateReporterFolder report;
    static String sFolderName;
    static String sPath;

    private CreateReporterFolder(String folderName){
        sPath = "src/test/Reports/" + folderName;
        new File(sPath + "/screenshots").mkdirs();

    }
    public static CreateReporterFolder getInstance(String folderName){
        if(report == null){
            report = new CreateReporterFolder(folderName);
            sFolderName = folderName;
        }
        return report;
    }
    public static String getsPath(){
        return sPath;
    }
}
