
<!DOCTYPE html>
<%@ include file="Header.jsp" %>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/FormRegistrazione.css" rel="stylesheet" type="text/css">

</head>

<body>

<label>Sono un nuovo cliente</label>

<form  class="reg"name='registration'>

<ul>
<li><label for="userid">UserID:</label></li>
<li><input type="text" name="userid" size="12" /></li>
<li><label for="passid">Password:</label></li>
<li><input type="password" name="passid" size="12" /></li>
<li><label for="username">Nome:</label></li>
<li><input type="text" name="nome" size="50" /></li>
<li><label for="surname">Cognome:</label></li>
<li><input type="text" name="cognome" size="50" /></li>
<li><label for="number">Telefono:</label></li>
<li><input type="text" name="telefono" size="30" /></li>
<li><label for="email">Email:</label></li>
<li><input type="text" name="email" size="50" /></li>
<li><input type="submit" name="submit" value="Invia" /></li>
</ul>
</form>


</body>
</html>