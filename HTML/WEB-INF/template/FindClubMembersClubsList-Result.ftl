<html>
<head><title>Join Club</title>
</head>
<body>

<h1>List club membership</h1>

<form method=post action="FindClubMembers">

<p>Select a club: 
<select
  name="club_name" size="1">
  <#list clubs as club>
  <option>${club}</option>
  </#list>
</select>

<p><input type=submit> <input type=reset>

</form>

<p><p>Back to the <a href="ShowMainWindow"> main window</a>

</body>
</html>
