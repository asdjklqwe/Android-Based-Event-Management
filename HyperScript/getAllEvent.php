<?php 
 	//Creating sql query
 	$sql = "SELECT * FROM event ORDER BY event_id DESC ";
 		//Importing Database Script 
	require_once('Database.php');
 
	$res= mysqli_query($connect,$sql);

	//creating a blank array
	$result = array();
	//looping through all the records fetched

	//Alarm_Title,Alarm_Status,Alarm_Interval,Alarm_Date,Alarm_Time
	while ($row = mysqli_fetch_array($res)){
		# code...
		array_push($result, array(
				"name"=>$row['name'],
				"location"=>$row['location'],
				"description"=>$row['description']
			));
	}
	echo json_encode(array('result'=>$result));
 ?>	