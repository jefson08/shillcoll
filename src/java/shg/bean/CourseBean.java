package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class CourseBean 

{
    private String txtCourseSeat;
    private String txtCourseCode;
    private String txtCourseName;
    
    
     
     
    public String getTxtCourseName() {
          return txtCourseName;
    }
    public String getTxtCourseCode() {
          return txtCourseCode;
    }
    public String getTxtCourseSeat() {
          return txtCourseSeat;
    }
    
    
    
    public boolean isTxtCourseNameValid() {
        if (!Validator.isEmpty(getTxtCourseName())) {
            return false;
        }
        return true;
    }
    public boolean isTxtCourseCodeValid() {
        if (!Validator.isEmpty(getTxtCourseCode())) {
            return false;
        }
        return true;
    }
    public boolean isTxtCourseSeatValid() {
        if ((!Validator.isEmpty(getTxtCourseSeat()))|| (Validator.containsIllegalCharacters(getTxtCourseSeat()))){
            return false;
        }
        return true;
    }

    /**
     * @param txtCourseSeat the txtCourseSeat to set
     */
    public void setTxtCourseSeat(String txtCourseSeat) {
        this.txtCourseSeat = txtCourseSeat;
    }

    /**
     * @param txtCourseCode the txtCourseCode to set
     */
    public void setTxtCourseCode(String txtCourseCode) {
        this.txtCourseCode = txtCourseCode;
    }

    /**
     * @param txtCourseName the txtCourseName to set
     */
    public void setTxtCourseName(String txtCourseName) {
        this.txtCourseName = txtCourseName;
    }
}