/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shg.uploadimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import megnic.util.ValidateEnclosures;
import javax.servlet.ServletContext;
import org.apache.commons.fileupload.FileUploadException;
import shg.util.uploadFile;

/**
 *
 * @author nic
 */
public class showUploadImage extends HttpServlet {
    String sql = "";
    ServletContext context = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        UploadListener listener = new UploadListener(request, 30);
        // Create a factory for disk-based file items
        FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        List items = null;
        String itemName = "";
        File savedFile = null;
        FileItem item = null;
        FileInputStream fis = null;
        
        try {
            // process uploads ..
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            out.println("Sorry an error occured while trying to process the request ");
            System.out.println("ERROR generated while trying to upload enclosures  " + e);
            listener = null;
            factory = null;
            upload = null;
            return;
        }
        
        Iterator itr = items.iterator();
        // Loop through all the elements in the form 
        
        try {
            while (itr.hasNext()) {
                item = (FileItem) itr.next();
                
                if (!(item.isFormField())) {
                    
                    itemName=item.getName();
//                    System.out.println("item NAME --- "+ item.getFieldName());
                    if (itemName != null && itemName.trim().length() > 0 && item.getFieldName().equals("photo")) {
                        
                        savedFile=uploadFile.saveUploadFile(item,getServletContext());       /*   CHECK CONTENT OF THE IMAGE  */
                        if (savedFile==null){
                            out.print("0Cannot upload photo. The image type is either invalid or too big."
                                    + " \n Valid File types are .gif, .jpg, .png, .tiff"
                                    + " \n Maximum file size is 1Mb");
                            
                            return;
                        }
                        fis = null;
                        fis = new FileInputStream(savedFile);
                        String imag=savedFile.toString();
                        imag=imag.substring(imag.lastIndexOf('\\',imag.length())+1, imag.length());
                        //out.print("<img src='../temp/"+imag+"' width='190' height='200'></img>");
//                        System.out.println("1../temp/"+imag+"");
                        out.print("1../temp/"+imag+"");
                        return;
                    }
//                    System.out.println("HURRRRAYYYY "+ itemName);
                } 
            }
        }catch(Exception e){
            System.out.println("getName -- exception "+e);
        }
        finally{
            listener = null;
            factory = null;
            upload = null;
            items = null;
            itemName =null;
            savedFile = null;
            item = null;
            fis = null;
        }
    }
}

   