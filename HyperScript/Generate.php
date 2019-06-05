<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 require_once('Database.php');

 $sql = "SELECT * FROM event ORDER BY event_id DESC  LIMIT 1";

 $r = mysqli_query($connect,$sql);
 $id = mysqli_fetch_array($r,MYSQLI_ASSOC);

 $event_id = $id['event_id'];
 
 $sql = "SELECT * FROM ticket WHERE event_id = $event_id";
 
 $r = mysqli_query($connect,$sql);
 $id = mysqli_fetch_array($r,MYSQLI_ASSOC);

 $ticket_no = $id['ticket_no'];

 echo $ticket_no;
 
 
 mysqli_close($connect);
 }	
?>