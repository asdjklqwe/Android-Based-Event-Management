<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $Card_No = $_POST['Card_No'];
	 $Card_Amount = $_POST['Card_Amount'];
	 
	 $sql = "UPDATE card_account SET Card_Amount= '$Card_Amount' WHERE Card_No = '$Card_No'";
	 
	 $res= mysqli_query($connect,$sql);
	 mysqli_close($connect);
 }
 ?>	