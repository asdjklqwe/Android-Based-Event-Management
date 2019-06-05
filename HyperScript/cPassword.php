<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $currentPass = $_POST['currentPass'];
 $newPass = $_POST['newPass'];
 $cnewPass = $_POST['cnewPass'];
 
 require_once('Database.php');

 $sql = "UPDATE users SET password = '$cnewPass', cpassword = '$cnewPass' WHERE password = '$currentPass'";

 mysqli_close($connect);

}
?>