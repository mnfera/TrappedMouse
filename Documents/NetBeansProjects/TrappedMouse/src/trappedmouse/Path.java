package trappedmouse;

import java.io.File;
import java.nio.file.FileSystems;
/** 
 * Contém métodos para criar caminhos de arquivos e pastas na Área de Trabalho segundo o sistema operativo
 *
 * @author Mikael
 */
public class Path {
    
    static String dsktop = System.getProperty("os.name").toLowerCase().contains("windows")?"Desktop":"Área\\ de\\ Trabalho";
    /**
     * 
     * @return p = Path to desktop 
     */
    public static Path pathToDesktop(){
         
        Path p = (Path) FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + dsktop);
            return p;
    }
    /**
     * 
     * @param nameOfFolder 
     * @return p = Path to folder on desktop
     */
    public static Path pathToFolderOnDesktop(String nameOfFolder){
        Path p = (Path) FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + dsktop
            + File.separator
            + nameOfFolder);
        return p;
    }
    /**
     * 
     * @param nameOfFile
     * @return p = Path to file on desktop
     */
    public static Path pathToFileOnDesktop(String nameOfFile){
        Path p = (Path) FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + dsktop
            + File.separator
            + nameOfFile);
        return p;
    }
    /**
     * 
     * @param nameOfFolder
     * @param nameOfFile
     * @return p = Path to file in folder on desktop
     */
    public static Path pathToFileInFolderOnDesktop(String nameOfFolder, String nameOfFile){
        Path p = (Path) FileSystems.getDefault().getPath(System.getProperty("user.home"),
            File.separator
            + dsktop
            + File.separator
            + nameOfFolder
            + File.separator
            + nameOfFile);
        return p; 
    }
}
