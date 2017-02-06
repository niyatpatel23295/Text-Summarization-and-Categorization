<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="index.css"></link>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
<title>Summarization and Categorization</title>
</head>
<body>

<div id="content">
  	<!-- <img type ="background-image" src="website.jpg" id="bg" alt="image"/> -->
  	<ul style="list-style-type:none;">
		<li>
			<label style="margin-top:0;margin-bottom:0;padding-left:15%;font-size:15px;color:white;font-family: verdana;">Text: </label>
				<textarea rows="28" cols="100" placeholder="Insert your text and cilck output" style="display:block;margin-left:auto;margin-right:auto;margin-bottom:5px;background-color:whitesmoke;" onselect="wordtoken();" id="abc"></textarea>
		</li>
		<li>
			<button type="submit" style="border: 1px solid black; border-radius: 0px;background-color:whitesmoke;color: black;margin-left:45%;" onclick="cleartext();">Clear</button>
			<button type="submit" style="border: 1px solid black; border-radius: 0px;background-color:whitesmoke;color: black;margin-right:45%;">Output</button>
		</li>
		<li>
			<label style="margin-top:0;margin-bottom:0;padding-left:15%;color:white;font-size:15px;font-family:verdana;">Category: </label>
			<textarea rows="1" cols="100" placeholder="Category of the text" style="display:block;margin-left:auto;margin-right:auto;background-color:whitesmoke;color:black;" disabled></textarea>
		</li>
		<li>
			<label style="margin-top:0;margin-bottom:0;padding-left:15%;color:white;font-size:15px;font-family:verdana;">Summary: </label>
			<textarea rows="5" cols="100" placeholder="Summary of the text" style="display:block;margin-left:auto;margin-right:auto;background-color:whitesmoke;" disabled></textarea>
		</li>
		</ul>
   	</div>
  	
<div id="footer">
	<ul style="list-style-type:none;marign:0;text-align:center;">
		<li style="display:inline;margin-top:auto;margin-bottom:auto;font-size:15px;font-family:verdana;padding-right:15px;"> Created By NIYAT PATEL</li>
	<!--  
	<li style="display:inline;padding-left: 30px;"><i class="fa fa-facebook" style="font-size: 25px;padding-right: 10px;"></i></li>  
	<li style="display:inline;"><i class="fa fa-twitter" style="font-size: 25px;"></i></li>
	-->
		<li style="display:inline;">
			<i class="fa fa-facebook" style="font-size: 20px;padding-right: 5px;"></i>
			<i class="fa fa-twitter" style="font-size: 20px;"></i>
		</li>
	</ul>
 </div>
 
 </div>
 	 <script type="text/javascript" charset="utf-8">
 	 $(documnet).ready(function() {
  	  	 function cleartext()
  	  	 {
  	  		 document.getElementById('abc').value = "";
  	  	 }
  	  	 function wordtoken()
  	  	 {
  	  		 var str = document.getElementById('abc').value;
  	  		 var tokens = str.split(" ");
  	  		 for(var i=0;i<tokens.length;i++)
  	  			 {
  	  			 	document.getElementById('abc').innerHTML="<a class='words'>tokens[i]</a>";
   	  			 } 	
  	  	 }
 	});
 	 </script>
</body>
</html>