<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){

 require_once('Database.php');
 
 $sql = "SELECT * FROM event ORDER BY event_id DESC  LIMIT 1";
 
 $r = mysqli_query($connect,$sql);
 $id = mysqli_fetch_array($r,MYSQLI_ASSOC);
 $event_id = $id['event_id'];

 $sql = "SELECT * FROM ticket WHERE event_id = $event_id";

 $result = mysqli_query($connect,$sql);

 $ticket = array();
  
 while($row = $result->fetch_assoc())
 {
    $ticket[] = $row;
 }
  
 echo json_encode(array("tickets"=>$ticket));  
 
 mysqli_close($connect);
 }	
?>		