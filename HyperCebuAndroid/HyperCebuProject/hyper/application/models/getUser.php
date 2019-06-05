<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class getUser extends CI_Model
{
   
              
    function user($username, $password){  
          /*$sql_str = "
            SELECT * 
            FROM `users`
            WHERE `username` = '{$username}'
            AND `password` = '{$password}'
          ";

          return $this->db->query($sql_str);*/

          $this ->db-> select('*');
           $this ->db-> from('users');
          
           $this ->db-> where('username', $username);
           $this ->db-> where('password',$password);
           
           $this ->db-> limit(1);
         
           $query = $this->db->get();

           return $query;
         

     }   

     function getAllUsers(){

      $sql_str = "
        SELECT * FROM `users`
        WHERE `user_type` = 2
      ";
      return $this->db->query($sql_str);
      
     }  
     
}
?>