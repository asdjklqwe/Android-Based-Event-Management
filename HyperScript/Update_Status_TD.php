<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $ticket_no = $_POST['ticket_no'];
	 $qr_code = $_POST['qr_code'];

	 $sql = "UPDATE ticket_details SET status = '1' WHERE ticket_no = '$ticket_no' AND qr_code = '$qr_code'";
	 
	 $res= mysqli_query($connect,$sql);
	 mysqli_close($connect);
 }
 ?>	