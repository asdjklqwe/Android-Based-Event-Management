<?php if ( ! defined('BASEPATH')) exit('no direct script access allowed');



class getHistory extends CI_Model
{


	function ticket_history(){

		$sql_str = "

		Select a.ticket_history_id, a.event, a.ticket_type, a.ticket_price, a.ticket_qrcode, b.status, a.ticket_hypercard_no
		FROM ticket_purchased_history AS a
		JOIN ticket_details AS b
		ON(a.ticket_qrcode = b.qr_code)

		";


		return $this->db->query($sql_str);

	}








}