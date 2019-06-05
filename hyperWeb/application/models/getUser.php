<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class getUser extends CI_Model
{


    function user(){

      $sql_str = "
        SELECT *
        FROM `users`
        WHERE user_type = 1
      ";
      return $this->db->query($sql_str);

     }

     function getAllUsers(){

      $sql_str = "
          Select a.id,a.firstname,a.lastname, a.username,a.card_no,b.deleted
          FROM users AS a
          LEFT JOIN card_account AS b
          ON (a.card_no = b.Card_No)
          WHERE a.user_type != 1 AND a.deleted != 1
      ";
      return $this->db->query($sql_str);

     }

     function getUserUpdate($id){

       $sql_str = "
         SELECT * FROM `users`
         WHERE id = {$id}
       ";
       return $this->db->query($sql_str);
     }

}
?>
