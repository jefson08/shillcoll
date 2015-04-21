package shg.util;




import java.io.File;

import java.util.StringTokenizer;
import javax.servlet.ServletContext;

import shg.valid.ValidateEnclosures;
import org.apache.commons.fileupload.FileItem;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nic
 */
public class uploadFile {
    
    public static File saveUploadFile(FileItem item, ServletContext context) {
            String itemName = "";
            String fileName = "";
            File savedFile = null;
            try{
                    String contenttype = item.getContentType(); // Getting the content type of the file
                    
                    if (contenttype.equals("image/jpeg") || contenttype.equals("image/pjpeg") || contenttype.equals("image/gif") || contenttype.equals("image/png") || contenttype.equals("image/x-png") || contenttype.equals("image/tiff")) {
                        
                    } else {
                        throw new Exception("6 - Invalid File Name");
                    }//END

                    // if element is a file object

                    
                    itemName = item.getName();
                    if (itemName == null || itemName.trim().length() == 0) {
                        
                    }
                    /*------------ validate null byte injection ------------*/
                    if (!ValidateEnclosures.noNullByteInjected(itemName)) {
                        throw new Exception("2-not a valid file/path");
                    }
                    /*---- xxxxxxxxxxxxxxxxxxxxxxx -------------------------*/

                    //Retrieving the file name
                    StringTokenizer stk = new StringTokenizer(itemName, "\\");
                    int i = stk.countTokens();
                    int j = 0;
                    while (stk.hasMoreTokens()) {
                        j++;
                        fileName = stk.nextToken();
                    }
                    //VALIDATE FILE

                    /*----  validate type of the file to be uploaded ------*/
                    if (!ValidateEnclosures.isValidFileType(fileName)) {
                        throw new Exception("3-File type not valid for upload");
                    }/*------*/

                    /*----  validate size of the file to be uploaded ------*/
                    if (!ValidateEnclosures.isFilesSizeValid(item.getSize())) {
                        throw new Exception("4-File size beyond permissible limit for upload");
                    }/*------*/


                    /*----  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ------*/

                    /* generate random file names */
                    fileName = item.getName();
                    String ext= fileName.substring(fileName.lastIndexOf('.', fileName.length())+1);
                    fileName = new java.util.Random().nextInt(10000) + System.currentTimeMillis()+"."+ext;// + enclosureCode;
                    /*----------------------------*/
                    
                    savedFile = new File(context.getRealPath("/") + "/temp/" + fileName);
//                    System.out.println("Random "+fileName);
                    //Write the file from Memory to Disk
                    item.write(savedFile);
                    //Insert the Enclosure to the Database
                    
            }catch(Exception e){
                System.out.println(" saveUploadFile ---"+ e);
            }
            return savedFile;
    }
}
