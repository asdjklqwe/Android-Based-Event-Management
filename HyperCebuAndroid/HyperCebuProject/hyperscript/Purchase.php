<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 	$card_no = $_POST['card_no'];
 	$sql = "SELECT card_amount FROM card_member WHERE card_no = '$card_no' ";

 	$r = mysqli_query($connect,$sql);
 	$id = mysqli_fetch_array($r,MYSQLI_ASSOC);

 	echo json_encode(array("result"=>$ticket))

 	$sql = "UPDATE card_member SET card_amount = '$card_amount' WHERE card_no = '$card_no'";
 	$card_amount = $_POST['card_amount'];

  	require_once('Database.php');
 	if(mysqli_query($connect,$sql)){
	echo "Transaction Success";
	}else{
	echo 'error';
	}
?>