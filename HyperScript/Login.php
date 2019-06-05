<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 require_once('Database.php');

 $username = $_POST['username'];
 $password = $_POST['password'];
 
 $sql = "SELECT s.*, ca.Card_Amount FROM users s JOIN card_account ca ON s.card_no = ca.Card_No WHERE username = '$username' AND password ='$password'";
 
 $res= mysqli_query($connect,$sql);
 
 $result = array();
    while($row = mysqli_fetch_array($res)){
		array_push($result,
		array(
			'id'=>$row[0],
			'firstname'=>$row[1],
			'lastname'=>$row[2],
			'card_no'=>$row[7],
			'Card_Amount'=>$row[9]
		));
	}

	echo json_encode(array("result"=>$result));

 mysqli_close($connect);
 }
 ?>	