<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h1>Currently active clubs</h1>

<table width="70%"  border="0" cellpadding="1" cellspacing="2" >
  <tr>
    <td><b>Id</b></td>
    <td><b>Name</b></td>
    <td><b>Address</b></td>
    <td><b>Established</b></td>
    <td><b>Established By</b></td>
  </tr>
 <#list clubs as club>
  <tr>
    <td>${club[0]}</td>
    <td>${club[1]}</td>
    <td>${club[2]}</td>
    <td>${club[3]}</td>
    <td>${club[4]}</td>
  </tr>
 </#list></table>
  
<p><p>Back to the <a href="ShowMainWindow"> main window</a>
  
</body>
</html>
