<html>
<head><title>Club Membership</title>
</head>
<body>

<h1>Club Membership</h1>

<p>Current membership of club: <tt>${club_name}</tt>

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
