<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $firstname = $_POST['firstname'];
 $lastname = $_POST['lastname'];
 $username = $_POST['username'];
 $password = $_POST['password'];
 $cpassword= $_POST['cpassword'];
 
 require_once('Database.php');

 $sql = "SELECT * FROM users WHERE username = '$username'";

 $Card_No = mt_rand(9999999,9999999999);;

 $r = mysqli_query($connect,$sql); 
 $check = mysqli_num_rows($r);

       if($check == 0){
         $sql = "INSERT INTO users (firstname, lastname, username, password, cpassword, card_no) VALUES ('$firstname', '$lastname', '$username', '$password', '$cpassword', '$Card_No')";
         mysqli_query($connect,$sql); 

         $sql1 = "SELECT * FROM card_account WHERE Card_No = '$Card_No'";
       
         $res = mysqli_query($connect,$sql1); 
         $check1 = mysqli_num_rows($res);

        if($check1 == 0){
         $sql1 = "INSERT INTO card_account (Card_No) VALUES ('$Card_No')";
         mysqli_query($connect,$sql1); 
         //echo 'Card number Successfully added';
       }else{
         //echo 'Card number already exist';
       }
       
   echo 'Successfully Signup';
 }else{
   echo 'Username already exist';
 }


 mysqli_close($connect);
}
?>