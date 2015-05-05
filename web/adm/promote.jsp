<%-- 
    Document   : promote
    Created on : May 1, 2015, 10:13:41 PM
    Author     : B Mukhim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="../scripts/adm/promote.js"></script>
        <title>Promote to next Class</title>
    </head>
    <body>
        <form name="promote" method="POST">
            <table>
                <tr>
                    <td colspan="3" style="text-align: center">
                        <label><input type="radio" name="radYearOrSem" id="radYearOrSem" value="s" />Semester</label>
                        <label><input type="radio" name="radYearOrSem" id="radYearOrSem" value="y" />Year</label>
                    </td>
                </tr>
                <tr>
                    <td>Stream</td><td> : </td><td><select name="cmbStream" id="cmbStream"></select></td>
                </tr>
                <tr>
                    <td>Course</td><td> : </td><td><select id="cmbCourse" name="cmbCourse"></select></td>
                </tr>
                <tr>
                    <td>Select Year or Semester</td><td> : </td> <td><select id="cmbYearOrSemNo" name="cmbYearOrSemNo"></select></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" name="promote" value="Promote" /></td>
                </tr>
            </table>        
        </form>
    </body>
</html>
