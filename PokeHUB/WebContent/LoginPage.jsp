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
<img class="firstimg" src="Image/Logo.png" alt="logo">
</div>
<div class="destra">

<ul class="testi">
<form action="Login" method="post" >
    <li> <label for="username">Login</label>
    <br>
     &nbsp;
      &nbsp;
       &nbsp;
        &nbsp;
      
          
     
     <input id="username" type="text" name="username" placeholder="enter login" required> </li>
   <li>  <br>   
     <br>
     <br>
     <label for="password">Password</label><br>
     <input id="password" type="password" name="password" placeholder="enter password" required> </li></ul>
    
     <ul class="bottoni">
     <li class="left"> <button type="submit" class="login" value="Login">Login</button></li>
     <li class="right"><button type="Reset" class="login" value="Reset">Reset</button></li>
     </ul>

</form>

</div>

<div class=boxassolut>
<%@ include file="Footer.html" %>
</div>
</body>

</html>

