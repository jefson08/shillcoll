/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.dao;

import DBConnection.ConnectionPool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import shg.uploadimage.MonitoredDiskFileItemFactory;
import shg.uploadimage.UploadListener;
import shg.util.uploadFile;

/**
 *
 * @author B Mukhim
 */
public class saveImageToDB {

    private UploadListener listener = null;
    // Create a factory for disk-based file items
    private FileItemFactory factory = null;
    // Create a new file upload handler
    private ServletFileUpload upload = null;
    private List items = null;
    private FileItem item = null;
    private FileItem photo = null;
    private File img = null;
    private FileInputStream fis = null;
    private String rollno = null;
    private ConnectionPool connectionPool = null;
    private Connection con = null;
     private PreparedStatement pst = null;

    public String saveImage(HttpServletRequest request, ServletContext context) {
//        System.out.println("uploooooaaad");
        String result = null;
        listener = new UploadListener(request, 30);
        // Create a factory for disk-based file items
        factory = new MonitoredDiskFileItemFactory(listener);
        // Create a new file upload handler
        upload = new ServletFileUpload(factory);

        try {
            // process uploads ..
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            result = "Server has Encountered an ERROR while trying to upload student's photo ";
            System.out.println("Server has Encountered an ERROR while trying to upload student's photo -- Error 1 --   " + e);
            listener = null;
            factory = null;
            upload = null;
            return result;
        }
        Iterator itr = items.iterator();
        try {
            while (itr.hasNext()) {
                item = (FileItem) itr.next();

                if (item.isFormField()) {
                    if (item.getFieldName().equals("rollno")) {
                        rollno = item.getString();
                    }
                } else {
                    if (item.getFieldName().equals("photo")) {
                        photo = item;
                    }
                }
            }

        } catch (Exception e) {
            result = "Server has Encountered an ERROR while trying to upload student's photo";
            System.out.println("Server has Encountered an ERROR while trying to upload student's photo -- Error 2 -- " + e);
            return result;
        }
        try {
            if (photo.getName().trim().length() != 0) {
                img = uploadFile.saveUploadFile(photo, context);
                if (img == null) {
                    result = "Cannot upload photo. The image type is either invalid or too big."
                            + " \n Valid File types are .gif, .jpg, .png, .tiff"
                            + " \n Maximum file size is 1Mb";

                    return result;
                }
                fis = null;
                fis = new FileInputStream(img);
            }
        } catch (FileNotFoundException e) {
            result = "Server has Encountered an ERROR while trying to upload student's photo";
            System.out.println("Server has Encountered an ERROR while trying to upload student's photo -- Error 3 -- " + e);
            return result;
        }

        if (rollno == null) {
            result = "Server has Encountered an ERROR while trying to upload student's photo";
            System.out.println("Server has Encountered an ERROR while trying to upload student's photo -- Error 4 -- ");
            return result;
        }

        try {
            connectionPool = (ConnectionPool) context.getAttribute("ConnectionPool");
            con = connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
            listener = null;
            factory = null;
            upload = null;
            result = "Server has Encountered an ERROR while trying to upload student's photo";
            System.out.println("Server has Encountered an ERROR while trying to upload student's photo -- Error 4 -- ");
            return result;
        }

        try {
            con.setAutoCommit(false);
            String sql="Update studentdetails set photo=? where rollno=?";
            pst=con.prepareStatement(sql);
            pst.setBinaryStream(1, fis, (int) img.length());
            pst.setString(2, rollno);
            int affectedRow = pst.executeUpdate();
            if(affectedRow <= 0){
                result = "Server has Encountered an ERROR while trying to upload student's photo";
                System.out.println("Server has Encountered an ERROR while trying to save student's photo to database  -- Error -- 5 ");
                throw new SQLException(result);
            }
            con.commit();
            result = "1";
        }
        catch (SQLException e){
             try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("RollBack operation error.");
                
            }
                result="Sorry, an error occured while trying to preform the requested operation. SQL ERROR";
                System.out.println("Error -- 6 -- Exception thrown by class " + this.getClass() + " at " + new java.util.Date() + " :: " + e);
                return result;
        } finally {
            connectionPool.free(con);
            con = null;            
            connectionPool = null;
            listener = null;
            factory = null;
            upload = null;
        }

        return result;
    }
}
