<?php
 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $ticket_event = $_POST['ticket_event'];
	 $ticket_event_location = $_POST['ticket_event_location'];
	 $ticket_type = $_POST['ticket_type'];
	 $ticket_price = $_POST['ticket_price'];
	 $ticket_no = $_POST['ticket_no'];
	 $ticket_enddate = $_POST['ticket_enddate'];
	 $ticket_qrcode = $_POST['ticket_qrcode'];
	 $ticket_hypercard_no = $_POST['ticket_hypercard_no']; 

	 $sql = "INSERT INTO ticket_purchased_history(ticket_event,ticket_event_location,ticket_type, ticket_price,ticket_no,ticket_enddate, ticket_qrcode, ticket_hypercard_no) VALUES ('$ticket_event', '$ticket_event_location', '$ticket_type','$ticket_price','$ticket_no','$ticket_enddate', '$ticket_qrcode','$ticket_hypercard_no')";
	 
	 $res= mysqli_query($connect,$sql);
	 mysqli_close($connect);
 }
 ?>	