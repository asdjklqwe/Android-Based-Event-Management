<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class getCard extends CI_Model
{


    function card_account(){

      /*
      put username in card detail
      $sql_str = "

      SELECT a.Card_No, a.Card_Amount, b.username
      FROM card_account AS a
      JOIN users AS b
      ON(a.Card_No = b.card_no)
      WHERE a.deleted = 0
      ";*/

      $sql_str = "
        SELECT *
        FROM `card_account`
        WHERE deleted = 0
      ";

      return $this->db->query($sql_str);

     }

    /*function card_notused(){

       $sql_str = "
         SELECT *
         FROM `card_account`
         WHERE used = 0
         LIMIT 5
       ";
       return $this->db->query($sql_str);

      }*/



      function card_accounts($card_no){

        $sql_str = "
          SELECT *
          FROM `card_account`
          WHERE Card_No = {$card_no}
        ";
        return $this->db->query($sql_str);

       }

       function card_amount($card_amount,$card_no){

      $sql_str = "
        UPDATE `card_account`
        SET `Card_Amount` = `Card_Amount` + {$card_amount}
        WHERE `Card_No` = {$card_no}";
        return $this->db->query($sql_str);

     }


       function card_history($Card_No) {
         $res = array();
        $card_info = "
                      SELECT CONCAT(users.firstname,\" \",users.lastname) as CardHolderName,
                      card_account.Card_No as CardNumber,
                      users.id as user_id
                      FROM card_account
                      INNER JOIN users ON card_account.Card_No = users.card_no
                      WHERE
                        card_account.Card_No = {$Card_No}";
        $query1 = $this->db->query($card_info);
        if($query1->num_rows() > 0) {
            $res['cardInfo'] = $query1->result()[0];
            //die(var_dump($res['cardInfo']));
            $transaction_history = "
            SELECT
                ticket_purchased_history.ticket_date_of_purchase as DatePurchased,
                ticket_purchased_history.ticket_event as EventName,
                ticket_purchased_history.ticket_qrcode as qrcode,
                ticket_purchased_history.ticket_type as Type
              FROM
                  ticket_purchased_history
              WHERE
                  ticket_purchased_history.ticket_hypercard_no = {$Card_No};
            ";

            $query2 = $this->db->query($transaction_history);
            if($query2->num_rows() > 0)
            {
                $res['transactions'] = $query2->result();
            }
            //die(var_dump($query2->result()));
            //die(var_dump($res));
        }
        return $res;
      }

      function getCardBalance($cardNo) {
        $query = "SELECT card_account.Card_Amount as CardAmount, card_account.Card_No as CardNo FROM card_account WHERE card_account.Card_No = {$cardNo}";

        return $this->db->query($query);

      }

  //          SELECT
  //     CONCAT(
  //         users.firstname, " ", users.lastname
  //     ) as CardholderName,
  //     card_account.Card_No as CardNumber,
  //     ticket_details.date_of_purchased as DatePurchased,
  //     event.name as EventName
  // FROM
  //     card_account
  //     INNER JOIN users ON card_account.Card_No = users.card_no
  //     INNER JOIN ticket_details ON users.id = ticket_details.user_id
  //     INNER JOIN event ON ticket_details.event_id = event.event_id
  // WHERE
  //     card_account.Card_No = 345

}
?>
