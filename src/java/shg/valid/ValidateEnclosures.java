/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.valid;

/**
 *
 * @author kishore
 */
public class ValidateEnclosures {

    /*---  check the file type to be uploaded ---------------------------------*/
    public static boolean isValidFileType(String filename) {
        if (filename.trim().toLowerCase().endsWith(".jpg")
                || filename.trim().toLowerCase().endsWith(".jpeg")
                || filename.trim().toLowerCase().endsWith(".jpe")
                || filename.trim().toLowerCase().endsWith(".png")
                || filename.trim().toLowerCase().endsWith(".gif")) {
        } else {
            return false;
        }
        return true;
    }

    /***************************************************************************/
    /*----------  get file type error message ---------------------------------*/
    public static String getFileTypeErrorMessage(String filename, String outputpage) {
        return "../" + outputpage + "?message=Sorry, cannot upload the files; Only .jpg/.png./.gif are permitted";
    }

    /***************************************************************************/

    /*---  check the file size to be uploaded ---------------------------------*/
    public static boolean isFilesSizeValid(long filesize) {
        if (filesize > 1048576) {  // calculated in bytes // 1MB MAx
            return false;
        }
        return true;
    }

    /***************************************************************************/

    /*----------  get file size error message ---------------------------------*/
    public static String getFileSizeErrorMessage(String filename, long filesize, String outputpage) {
        return "../" + outputpage + "?message=Sorry, cannot upload the files; Maximum file size is 1 MB only";
    }

    /***************************************************************************/

    /*---  check the Null Byte Injection ---------------------------------*/
    public static boolean noNullByteInjected(String itemName) {

        if (itemName.indexOf("%00") != -1
                || itemName.indexOf(".exe") != -1
                || itemName.indexOf(".sh") != -1
                || itemName.indexOf(".bat") != -1) {
            return false;
        }
        return true;
    }
    /***************************************************************************/
}
