<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class event_model extends CI_Model
{
   
              
    function getEvent()
     {  
          /*$sql_str = "
            SELECT * 
            FROM `users`
            WHERE `username` = '{$username}'
            AND `password` = '{$password}'
          ";

          return $this->db->query($sql_str);*/
        
           $sql_str = "
            SELECT *
            FROM event
            WHERE deleted = 0
           ";

          return $this->db->query($sql_str);
         

     }

     function getEvent2()
     {  
          /*$sql_str = "
            SELECT * 
            FROM `users`
            WHERE `username` = '{$username}'
            AND `password` = '{$password}'
          ";

          return $this->db->query($sql_str);*/
        
           $sql_str = "
            SELECT *
            FROM event
           ";

          return $this->db->query($sql_str);
         

     }

   function getEventDetails($event_id){

    $sql_str = "

      SELECT * FROM `event` WHERE event_id = {$event_id}

    ";

    return $this->db->query($sql_str);

   }

   function getTicket($event_id){

    $sql_str = "

     SELECT * FROM `ticket` WHERE event_id = {$event_id} AND deleted = 0

    ";

    return $this->db->query($sql_str);

   }

   function getTicketDetails($ticket_no){

    $sql_str = "
      SELECT * FROM `ticket` WHERE ticket_no = {$ticket_no}
    ";

    return $this->db->query($sql_str);

   }

   function getEachTicket($event_id){

    $sql_str = "
      SELECT a.ticket_details_no,b.type,a.status,a.purchased,a.qr_code
      FROM ticket_details AS a
      JOIN ticket AS b
      ON(a.ticket_no = b.ticket_no)
      WHERE b.event_id = {$event_id} AND a.deleted = 0
    ";

    return $this->db->query($sql_str);

   }

   function getEachTicketDet($ticket_details_no){

    $sql_str = "
      SELECT * FROM ticket_details WHERE ticket_details_no = {$ticket_details_no}
    ";

    return $this->db->query($sql_str);

   }

   function countEvent(){

    $sql_str = "
      SELECT *
      FROM event;
    ";

    return $this->db->query($sql_str);

   }

   function countPurchased(){
    $sql_str = "
      SELECT * 
      FROM ticket_details
      WHERE purchased = 1
    ";
    return $this->db->query($sql_str);
   }


   function overallSales(){
    $sql_str = "
      SELECT SUM(a.price)AS total
      FROM ticket AS a
      JOIN ticket_details AS b
      ON (a.ticket_no = b.ticket_no)
      WHERE b.purchased = 1 
    ";

    return $this->db->query($sql_str);
   }

   function getTotalSales($event_id){
    $sql_str = "

      SELECT c.name,SUM(a.price)AS total
      FROM ticket AS a
      JOIN ticket_details AS b
      ON (a.ticket_no = b.ticket_no)
      JOIN event AS c
      ON (b.event_id = c.event_id)
      WHERE b.purchased = 1 AND c.event_id = {$event_id}

    ";

    return $this->db->query($sql_str);
   }


   function getExpectedSales($event_id){
    $sql_str = "
      SELECT b.name,SUM(a.price * a.quantity) AS total
      FROM ticket AS a
      JOIN event AS b
      ON(a.event_id = b.event_id)
      WHERE a.event_id = {$event_id}
    ";
    return $this->db->query($sql_str);
   }

   function getTotalTickets($event_id){
    $sql_str = "
      SELECT * FROM `ticket_details` WHERE event_id = {$event_id} AND deleted = 0
    ";
    return $this->db->query($sql_str);
   }

   function getRemainingTickets($event_id){
    $sql_str = "
      SELECT * FROM `ticket_details` WHERE event_id = {$event_id} AND purchased = 0 AND deleted = 0
    ";
    return $this->db->query($sql_str);
   }

   function getSoldTickets($event_id){
    $sql_str = "
      SELECT * FROM `ticket_details` WHERE event_id = {$event_id} AND purchased = 1 AND deleted = 0
    ";
    return $this->db->query($sql_str);
   }

   function getUnusedTickets($event_id){
    $sql_str = "
      SELECT * FROM `ticket_details` WHERE event_id = {$event_id} AND status = 0 AND purchased = 1 AND deleted = 0
    ";
    return $this->db->query($sql_str);
   }

   function getSales($event_id){
    $sql_str = "
      SELECT a.type, a.price, b.purchased, SUM(a.price) AS total
      FROM ticket AS a
      JOIN ticket_details AS b
      ON(a.ticket_no = b.ticket_no)
      WHERE b.purchased = 1 AND b.event_id = {$event_id} AND b.deleted = 0
    ";
       return $this->db->query($sql_str);
   }



     
}
?>