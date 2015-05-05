<%-- 
    Document   : index
    Created on : Jan 21, 2015, 11:39:38 PM
    Author     : B Mukhim
--%>

<%@page import="DBConnection.ConnectionPool"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
     Connection con = null;
     PreparedStatement pst = null;
     String sql;
     ServletContext context = null;
     ConnectionPool connectionPool = null;
%>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="../scripts/jquery/jquery-1.6.2.min.js"></script>
        <link href="style/test/style.css" rel="stylesheet" />
        <link href="style/test/bootstrap.min.css" rel="stylesheet" />
        <link href="style/test/formValidation.min.css" rel="stylesheet" />
        <link href="style/test/demo.css" rel="stylesheet" />
        <title>JSP Page</title>
        
<script>
$(document).ready(function() {
    $('#profileForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            fullName: {
                validators: {
                    notEmpty: {
                        message: 'The full name is required'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The full name must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z\s]+$/,
                        message: 'The full name can only consist of alphabetical and spaces'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
            childFullName: {
                // The children's full name are inputs with class .childFullName
                selector: '.childFullName',
                // The field is placed inside .col-xs-6 div instead of .form-group
                row: '.col-xs-6',
                validators: {
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The full name must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z\s]+$/,
                        message: 'The full name can only consist of alphabetical and spaces'
                    }
                }
            },
            childDob: {
                // The children's date of birth are inputs with class .childDob
                selector: '.childDob',
                // The field is placed inside .col-xs-4 div instead of .form-group
                row: '.col-xs-4',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The date of birth is not valid'
                    }
                }
            }
        }
    });
});
</script>

    </head>
    <body>
        

<form id="profileForm" method="post" class="form-horizontal">
    <div class="form-group">
        <label class="col-xs-3 control-label">Full name</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" name="fullName" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Email address</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" name="email" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Children</label>
        <div class="col-xs-9">
            <div class="form-group">
                <div class="col-xs-6">
                    <input type="text" class="form-control childFullName" name="child[0].fullName" placeholder="Full name" />
                </div>
                <div class="col-xs-4">
                    <input type="text" class="form-control childDob" name="child[0].dob" placeholder="Date of birth" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6">
                    <input type="text" class="form-control childFullName" name="child[1].fullName" placeholder="Full name" />
                </div>
                <div class="col-xs-4">
                    <input type="text" class="form-control childDob" name="child[1].dob" placeholder="Date of birth" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6">
                    <input type="text" class="form-control childFullName" name="child[2].fullName" placeholder="Full name" />
                </div>
                <div class="col-xs-4">
                    <input type="text" class="form-control childDob" name="child[2].dob" placeholder="Date of birth" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6">
                    <input type="text" class="form-control childFullName" name="child[3].fullName" placeholder="Full name" />
                </div>
                <div class="col-xs-4">
                    <input type="text" class="form-control childDob" name="child[3].dob" placeholder="Date of birth" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-6">
                    <input type="text" class="form-control childFullName" name="child[4].fullName" placeholder="Full name" />
                </div>
                <div class="col-xs-4">
                    <input type="text" class="form-control childDob" name="child[4].dob" placeholder="Date of birth" />
                </div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-9 col-xs-offset-3">
            <button type="submit" class="btn btn-default">Validate</button>
        </div>
    </div>
</form>

    </body>
</html>
