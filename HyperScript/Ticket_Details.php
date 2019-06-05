<?php
  if($_SERVER['REQUEST_METHOD']=='POST'){
 
	 require_once('Database.php');

	 $ticket_no  = $_POST['ticket_no'];

	 $sql = "SELECT * FROM ticket_details WHERE purchased = '0' AND ticket_no = '$ticket_no'";
	 
	 $res= mysqli_query($connect,$sql);
	 
	 $result = array();
	    while($row = mysqli_fetch_array($res)){
			array_push($result,
			array(
				'ticket_details_no'=>$row['ticket_details_no'],
				'status'=>$row['status'],
				'purchased'=>$row['purchased'],
				'qr_code'=>$row['qr_code'],
				'deleted'=>$row['deleted'],
				'ticket_no'=>$row['ticket_no'],
				'user_id'=>$row['user_id']
			));
		}

	echo json_encode(array("result"=>$result));

	mysqli_close($connect);
	}
 ?>	