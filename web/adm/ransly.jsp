<%-- 
    Document   : ransly
    Created on : May 2, 2015, 9:24:41 AM
    Author     : teibor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
   <form name="course" method="POST" action="papersExam.jsp">

       <h1> Data From Ransly</h1>
  
       Roll No <input type="text" name="roll1" id="roll1" value="BS-ZOO12015006"  size="20" /><br>
       Nehu Roll No <input type="text" name="nehuroll" id="nehuroll" value="NEHU12345"  size="20" /><br>

<input type="radio" name="status" id="status" value="Regular"  /> Regular
<input type="radio" name="status" id="status" value="Non Regular" /> Non Regular   
<input type="radio" name="status" id="status" value="Improvement" /> Improvement <br>
<br>
Year/ Semester <input type="text" name="yos" id="yos" value="s1"  size="5" /><br>
 
<input type="submit" value="Save" name="cmdSave"  />   
   
   </form>
</body>
</html>
