<?php

 require_once('Database.php');

$sql = "SELECT * FROM event ORDER BY event_id DESC  LIMIT 1";
 
 $r = mysqli_query($connect,$sql);
 $id = mysqli_fetch_array($r,MYSQLI_ASSOC);
 $event_id = $id['event_id'];
 //echo $event_id;
 
 $sql = "SELECT filename from event where event_id = '$event_id'";
 
 $res = mysqli_query($connect,$sql);

 $row = mysqli_fetch_array($res)
 
 $result = array();
 
 //$url = "http://192.168.254.2/hyperScript/getImage.php?id=";
 
 array_push($result,array('url'=>$row['event_id']));

 
 echo json_encode(array("result"=>$result));
 
 
 mysqli_close($connect);

 ?>


