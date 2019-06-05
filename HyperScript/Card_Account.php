<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 require_once('Database.php');

 $Card_No = $_POST['Card_No'];
 
 $sql = "SELECT * FROM card_account WHERE Card_No = '$Card_No'";
 
 $res= mysqli_query($connect,$sql);
 
 $result = array();
    while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			'Card_No'=>$row['Card_No'],
			'Card_Amount'=>$row['Card_Amount'],
		));
	}

	echo json_encode(array("result"=>$result));

 mysqli_close($connect);
 }
 ?>	