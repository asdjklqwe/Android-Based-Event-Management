<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class getHistory extends CI_Model
{


    function ticket_history(){



      $sql_str = "
      Select  a.ticket_history_id,a.ticket_event,a.ticket_event_location, a.ticket_type,a.ticket_price, a.ticket_date_of_purchase,a.ticket_qrcode,b.status,a.ticket_hypercard_no
      FROM ticket_purchased_history AS a
      JOIN ticket_details AS b
      ON(a.ticket_qrcode = b.qr_code)
      ";

      return $this->db->query($sql_str);

     }
}









/* Select  a.ticket_history_id,c.username, a.ticket_event,a.ticket_event_location, a.ticket_type,a.ticket_price, a.ticket_date_of_purchase,a.ticket_qrcode,b.status,a.ticket_hypercard_no
      FROM ticket_purchased_history AS a
      JOIN users as c ON(c.card_no = a.ticket_hypercard_no)
      JOIN ticket_details AS b
      ON(a.ticket_qrcode = b.qr_code)*/