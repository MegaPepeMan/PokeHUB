<!DOCTYPE HTML>
<html>

<head>
<style>
@import url('http://fonts.cdnfonts.com/css/berlin-sans-fb-demi');
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="CSS/Login.css" rel="stylesheet" type="text/css">
<title>Pagina di login</title>

</head>
<body>
<%@ include file="Header.jsp" %>
<div class="sinistra">
<img class="secondimg" src="Image/sotto.png">
</div>
<div class="destra2">
<img class="firstimg" src="Image/logo.png">
</div>
<form action="Login" method="post" >
<div class="destra">

<ul class="testi">
    <li> <label for="username">Login</label>
     &nbsp;
      &nbsp;
       &nbsp;
        &nbsp;
      
          
     
     <input id="username" type="text" name="username" placeholder="enter login" required> </li>
   <li>  <br>   
     <br>
     <br>
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password" required> </li></ul>
    
     <ul class="bottoni">
     <li class="left"> <button type="submit" class="login" value="Login">Login</button></li>
     <li class="right"><button type="Reset" class="login" value="Reset">Reset</button></li>
     </ul>


</div>

</form>

</body>

</html>

