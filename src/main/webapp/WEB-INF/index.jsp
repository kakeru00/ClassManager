<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- release v4.3.5, copyright 2014 - 2016 Kartik Visweswaran -->
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>Krajee JQuery Plugins - &copy; Kartik</title>
        <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/resources/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/fileinput.min.js" type="text/javascript"></script>
  

    </head>
    <body>
<input id="file-0a" class="file" type="file" multiple data-min-file-count="1">
        <div class="container kv-main">
            <div class="page-header">
            <h1>Bootstrap File Input Example <small><a href="https://github.com/kartik-v/bootstrap-fileinput-samples"><i class="glyphicon glyphicon-download"></i> Download Sample Files</a></small></h1>
            </div>
            <form enctype="multipart/form-data">
                <input id="file-0a" class="file" type="file" multiple data-min-file-count="1">
                <br>
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-default">Reset</button>
            </form>
            <hr>
            <form enctype="multipart/form-data">
                <label>Test invalid input type</label>
                <input id="file-0b" class="file" type="text" multiple data-min-file-count="1">
                <script>
                $(document).on('ready', function(){$("#file-0b").fileinput();});
                </script>
            </form>
            <hr>
            <form enctype="multipart/form-data">
                <input id="file-0a" class="file" type="file" multiple data-min-file-count="3">
                <hr>
                 <div class="form-group">
                    <input id="file-0b" class="file" type="file">
                </div>
                <hr>
                <div class="form-group">
                    <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
                </div>
                <hr>
                <div class="form-group">
                    <input id="file-2" type="file" class="file" readonly data-show-upload="false">
                </div> 
                <hr>
                <div class="form-group">
                    <label>Preview File Icon</label>
                    <input id="file-3" type="file" multiple=true>
                </div>
                <hr>
                <div class="form-group">
                    <input id="file-4" type="file" class="file" data-upload-url="#">
                </div>
                <hr>
                <div class="form-group">
                    <button class="btn btn-warning" type="button">Disable Test</button>
                    <button class="btn btn-info" type="reset">Refresh Test</button>
                    <button class="btn btn-primary">Submit</button>
                    <button class="btn btn-default" type="reset">Reset</button>
                </div>
                <hr>
                <div class="form-group">
                    <input type="file" class="file" id="test-upload" multiple>
                    <div id="errorBlock" class="help-block"></div>
                </div>
                <hr>
                <div class="form-group">
                    <input id="file-5" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#">
                </div>
            </form>
            
            
            <hr>
            <h4>Multi Language Inputs</h4>
            <form enctype="multipart/form-data">
                <label>French Input</label>
                <input id="file-fr" name="file-fr[]" type="file" multiple>
                <hr style="border: 2px dotted">
                <label>Spanish Input</label>
                <input id="file-es" name="file-es[]" type="file" multiple>
            </form>
            <hr>
            <br>
        </div>
    </body>
	
</html>