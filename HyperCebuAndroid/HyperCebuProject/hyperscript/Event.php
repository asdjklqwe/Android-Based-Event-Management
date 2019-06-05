<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $id  = $_GET['id'];
 
 require_once('Database.php');
 
 $sql = "SELECT * 
         FROM event
        ORDER BY event_id DESC 
        LIMIT 1";
 
 $r = mysqli_query($connect,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array("name"=>$res['name'],"location"=>$res['location'],"description"=>$res['description'],"startdate"=>$res['startdate'],"enddate"=>$res['enddate'],"starttime"=>$res['starttime'],"endtime"=>$res['endtime']) );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($connect);
 
 }	