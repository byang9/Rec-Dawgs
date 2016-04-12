<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Currently known persons</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Id</b></td>
    <td><b>UserName</b></td>
    <td><b>FirstName</b></td>
    <td><b>LastName</b></td>
    <td><b>Address</b></td>
    <td><b>Phone</b></td>
    <td><b>Email</b></td>
  </tr>
 <#list persons as person>
  <tr>
    <td>${person[0]}</td>
    <td>${person[1]}</td>
    <td>${person[2]}</td>
    <td>${person[3]}</td>
    <td>${person[4]}</td>
    <td>${person[5]}</td>
    <td>${person[6]}</td>
  </tr>
 </#list></table>

<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
