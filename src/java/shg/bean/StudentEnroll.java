/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shg.bean;

import shg.valid.Validator;

/**
 *
 * @author B Mukhim
 */
public class StudentEnroll {
    private String rollno;
    private String enrollYear;
    private String cmbCourseName;
    private String cmbCombination;
    private String txtStuName;
    private String txtDOB;
    private String radGender;
    private String txtNationality;
    private String radCategory;
    private String txtFName;
    private String txtMName;
    private String txtPPhno;
    private String txtPOccup;
    private String txtMAddress;
    private String txtPAddress;
    private String txtMobile;
    private String txtEmail;
    private String txtIncome;
    private String txtnehuRollno;

    /**
     * @return the cmbCourseName
     */
    public String getCmbCourseName() {
        return cmbCourseName;
    }

    /**
     * @param cmbCourseName the cmbCourseName to set
     */
    public void setCmbCourseName(String cmbCourseName) {
        this.cmbCourseName = cmbCourseName;
    }

    /**
     * @return the cmbCombination
     */
    public String getCmbCombination() {
        return cmbCombination;
    }

    /**
     * @param cmbCombination the cmbCombination to set
     */
    public void setCmbCombination(String cmbCombination) {
        this.cmbCombination = cmbCombination;
    }

    /**
     * @return the txtStuName
     */
    public String getTxtStuName() {
        return txtStuName;
    }

    /**
     * @param txtStuName the txtStuName to set
     */
    public void setTxtStuName(String txtStuName) {
        this.txtStuName = txtStuName;
    }

    /**
     * @return the txtDOB
     */
    public String getTxtDOB() {
        return txtDOB;
    }

    /**
     * @param txtDOB the txtDOB to set
     */
    public void setTxtDOB(String txtDOB) {
        this.txtDOB = txtDOB;
    }

    /**
     * @return the radGender
     */
    public String getRadGender() {
        return radGender;
    }

    /**
     * @param radGender the radGender to set
     */
    public void setRadGender(String radGender) {
        this.radGender = radGender;
    }

    /**
     * @return the txtNationality
     */
    public String getTxtNationality() {
        return txtNationality;
    }

    /**
     * @param txtNationality the txtNationality to set
     */
    public void setTxtNationality(String txtNationality) {
        this.txtNationality = txtNationality;
    }

    /**
     * @return the radCategory
     */
    public String getRadCategory() {
        return radCategory;
    }

    /**
     * @param radCategory the radCategory to set
     */
    public void setRadCategory(String radCategory) {
        this.radCategory = radCategory;
    }

    /**
     * @return the txtFName
     */
    public String getTxtFName() {
        return txtFName;
    }

    /**
     * @param txtFName the txtFName to set
     */
    public void setTxtFName(String txtFName) {
        this.txtFName = txtFName;
    }

    /**
     * @return the txtMName
     */
    public String getTxtMName() {
        return txtMName;
    }

    /**
     * @param txtMName the txtMName to set
     */
    public void setTxtMName(String txtMName) {
        this.txtMName = txtMName;
    }

    /**
     * @return the txtPPhno
     */
    public String getTxtPPhno() {
        return txtPPhno;
    }

    /**
     * @param txtPPhno the txtPPhno to set
     */
    public void setTxtPPhno(String txtPPhno) {
        this.txtPPhno = txtPPhno;
    }

    /**
     * @return the txtPOccup
     */
    public String getTxtPOccup() {
        return txtPOccup;
    }

    /**
     * @param txtPOccup the txtPOccup to set
     */
    public void setTxtPOccup(String txtPOccup) {
        this.txtPOccup = txtPOccup;
    }

    /**
     * @return the txtMAddress
     */
    public String getTxtMAddress() {
        return txtMAddress;
    }

    /**
     * @param txtMAddress the txtMAddress to set
     */
    public void setTxtMAddress(String txtMAddress) {
        this.txtMAddress = txtMAddress;
    }

    /**
     * @return the txtPAddress
     */
    public String getTxtPAddress() {
        return txtPAddress;
    }

    /**
     * @param txtPAddress the txtPAddress to set
     */
    public void setTxtPAddress(String txtPAddress) {
        this.txtPAddress = txtPAddress;
    }

    /**
     * @return the txtMobile
     */
    public String getTxtMobile() {
        return txtMobile;
    }

    /**
     * @param txtMobile the txtMobile to set
     */
    public void setTxtMobile(String txtMobile) {
        this.txtMobile = txtMobile;
    }

    /**
     * @return the txtEmail
     */
    public String getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtIncome
     */
    public String getTxtIncome() {
        return txtIncome;
    }

    /**
     * @param txtIncome the txtIncome to set
     */
    public void setTxtIncome(String txtIncome) {
        this.txtIncome = txtIncome;
    }

    public boolean isCmbCourseNameValid() {
        if (Validator.isNullValue(getCmbCourseName()) || getCmbCourseName().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isCmbCombinationValid() {
        if (Validator.isNullValue(getCmbCombination()) || getCmbCombination().equals("-1")) {
            return false;
        }
        return true;
    }

    public boolean isTxtStuNameValid() {
        if ((Validator.isNullValue(getTxtStuName())) || Validator.containsIllegalCharacters(getTxtStuName()) || !(Validator.isText(getTxtStuName()))) {
            return false;
        }
        return true;
    }

    public boolean isTxtDOBValid() {
        if (Validator.isNullValue(getTxtDOB())
                || (!Validator.checkRegexp(getTxtDOB(), "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19[0-9][0-9]|20[0-9][0-9]|21[0-9][0-9])"))
                || Validator.isDateAfterToday(getTxtDOB())) {
            return false;
        }
        return true;
    }

    public boolean isRadGenderValid() {
        if (Validator.isNullValue(getRadGender())) {
            return false;
        }
        if (getRadGender().trim().length() == 0 || !(getRadGender().toLowerCase().equals("m") || getRadGender().toLowerCase().equals("f"))) {
            return false;
        }
        return true;
    }

    public boolean isTxtNationalityValid() {
        if ((Validator.isNullValue(getTxtNationality()))
                || Validator.containsIllegalCharacters(getTxtNationality())
                || !(Validator.isText(getTxtNationality()))) {
            return false;
        }
        return true;
    }

    public boolean isRadCategoryValid() {
        if (Validator.isNullValue(getRadCategory())) {
            return false;
        }
        if (getRadCategory().trim().length() == 0 || !(getRadCategory().toLowerCase().equals("sc") || getRadCategory().toLowerCase().equals("st"))) {
            return false;
        }
        return true;
    }

    public boolean isTxtFNameValid() {
        if ((Validator.isNullValue(getTxtFName()))
                || Validator.containsIllegalCharacters(getTxtFName())
                || !(Validator.isText(getTxtFName()))) {
            return false;
        }
        return true;
    }

    public boolean isTxtMNameValid() {
        if ((Validator.isNullValue(getTxtMName()))
                || Validator.containsIllegalCharacters(getTxtMName())
                || !(Validator.isText(getTxtMName()))) {
            return false;
        }
        return true;
    }

    public boolean isTxtPPhnoValid() {
        if (!Validator.isNullValue(getTxtPPhno())) {
            if (!Validator.isNumeric(getTxtPPhno()) || !Validator.isValidLength(getTxtPPhno(), 10, 10)) {
                return false;
            }
        }
        else{
            txtPPhno="";
        }
        return true;
    }

    public boolean isTxtPOccupValid() {
        if (!Validator.isNullValue(getTxtPOccup())) {
            if (Validator.containsIllegalCharacters(getTxtPOccup())
                    || !(Validator.isText(getTxtPOccup()))) {
                return false;
            }
        }
        else{
            txtPOccup="";
        }
        return true;
    }

    public boolean isTxtMAddressValid() {
        if (Validator.isNullValue(getTxtMAddress())) {
            return false;
        }
        return true;
    }

    public boolean isTxtPAddressValid() {
        if (Validator.isNullValue(getTxtPAddress())) {
            return false;
        }
        return true;
    }

    public boolean isTxtMobileValid() {
        if (!Validator.isNullValue(getTxtMobile())) {
            if (!Validator.isNumeric(getTxtMobile()) || !Validator.isValidLength(getTxtMobile(), 10, 10)) {
                return false;
            }
        }
        else{
            txtMobile="";
        }
        return true;
    }

    public boolean isTxtEmailValid() {
        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Validator.isNullValue(getTxtEmail())) {
            if (!Validator.checkRegexp(getTxtEmail(), emailreg)) {
                return false;
            }
        }
        else{
            txtEmail="";
        }
        return true;
    }
    
    public boolean isTxtIncomeValid() {
        if (!Validator.isNullValue(getTxtIncome())) {
            if (!Validator.isNumeric(getTxtIncome())) {
                return false;
            }
        }
        else{
            txtIncome="0";
        }
        return true;
    }

    /**
     * @return the rollno
     */
    public String getRollno() {
        return rollno;
    }

    /**
     * @param rollno the rollno to set
     */
    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    /**
     * @return the enrollYear
     */
    public String getEnrollYear() {
        return enrollYear;
    }

    /**
     * @param enrollYear the enrollYear to set
     */
    public void setEnrollYear(String enrollYear) {
        this.enrollYear = enrollYear;
    }

    /**
     * @return the txtnehuRollno
     */
    public String getTxtnehuRollno() {
        return txtnehuRollno;
    }

    /**
     * @param txtnehuRollno the txtnehuRollno to set
     */
    public void setTxtnehuRollno(String txtnehuRollno) {
        this.txtnehuRollno = txtnehuRollno;
    }

    
}
