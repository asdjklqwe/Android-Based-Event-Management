<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $ticket_no = $_POST['ticket_no'];
	 $type = $_POST['type'];
	 $quantity = $_POST['quantity'];

	 $sql = "UPDATE ticket SET quantity= '$quantity' WHERE ticket_no = '$ticket_no' AND type= '$type'";
	 
	 $res= mysqli_query($connect,$sql);
	 mysqli_close($connect);
 }
 ?>	