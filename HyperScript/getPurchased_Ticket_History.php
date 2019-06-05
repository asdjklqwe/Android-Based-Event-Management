<?php
  if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $ticket_hypercard_no = $_POST['ticket_hypercard_no'];

	 $sql = "SELECT * FROM ticket_purchased_history WHERE ticket_hypercard_no = '$ticket_hypercard_no'";
	 
	 $res= mysqli_query($connect,$sql);
	 
	 $result = array();
	    while($row = mysqli_fetch_array($res)){
			array_push($result,
			array(
				'ticket_event'=>$row['ticket_event'],
				'ticket_event_location'=>$row['ticket_event_location'],
				'ticket_type'=>$row['ticket_type'],
				'ticket_price'=>$row['ticket_price'],
				'ticket_no'=>$row['ticket_no'],
				'ticket_enddate'=>$row['ticket_enddate'],
				'ticket_qrcode'=>$row['ticket_qrcode'],
				'ticket_hypercard_no'=>$row['ticket_hypercard_no'],
				'ticket_date_of_purchase'=>$row['ticket_date_of_purchase']
			));
		}
	echo json_encode(array("result"=>$result));
	mysqli_close($connect);
	}
 ?>	