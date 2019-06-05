<?php 
 
class admin extends CI_Controller
{	

	 public function __construct(){
	 	  parent::__construct();

	 	  $this->load->helper('url');
	 	  $this->load->helper(array('form', 'url'));
          $this->load->library('form_validation');
          $this->load->model('getUser');
          $this->load->model('event_model');
          $this->load->library('session');
          $this->load->library('Uuid');
	 }

     public function index()
     {
          $this->load->view('template/login_header');
          $this->load->view('login_view');
          $this->load->view('template/login_footer');
     }

     public function dashboard(){

          $data['events_count'] = $this->event_model->countEvent();
          $data['users_count'] = $this->getUser->getAllUsers();
          $data['overall_ticket'] = $this->event_model->countPurchased();
          $data['overall_sales'] = $this->event_model->overallSales();
          $data['getEvents'] = $this->event_model->getEvent2();

          $this->load->view('template/dashboard_header');
          $this->load->view('dashboard',$data);
          $this->load->view('template/dashboard_footer');
     }

     public function user_list(){

          $data['user_lists'] = $this->getUser->getAllUsers();

          $this->load->view('template/dashboard_header');
          $this->load->view('view_user_list_view',$data);
          $this->load->view('template/dashboard_footer');
     }

     public function events_list()
     {


     	  $data['event_list'] = $this->event_model->getEvent();

          $this->load->view('template/dashboard_header');
          $this->load->view('view_events_list_view',$data);
          $this->load->view('template/dashboard_footer');
     }

     public function update_view($event_id){

     	  $data['event_details'] = $this->event_model->getEventDetails($event_id);

     	  $this->load->view('template/dashboard_header');
          $this->load->view('update_event_view',$data);
          $this->load->view('template/dashboard_footer');

     }

     public function event_details($event_id){

        $data['event_details'] = $this->event_model->getEventDetails($event_id);
        $data['ticket_details'] = $this->event_model->getTicket($event_id);
        $data['each_ticket'] = $this->event_model->getEachTicket($event_id);


        $this->load->view('template/dashboard_header');
        $this->load->view('event_details',$data);
        $this->load->view('template/dashboard_footer');

     }

     public function update_ticket_v($ticket_no){

        $data['ticket_details'] = $this->event_model->getTicketDetails($ticket_no);

        $this->load->view('template/dashboard_header');
        $this->load->view('update_ticket_view',$data);
        $this->load->view('template/dashboard_footer');

     }

     public function reports(){

        $data['event_list'] = $this->event_model->getEvent();

        $this->load->view('template/dashboard_header');
        $this->load->view('reports_view',$data);
        $this->load->view('template/dashboard_footer');

     }




     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
     public function login(){

     	$username = $this->input->post("user");
        $password = $this->input->post("pass");

        //set validations
        $this->form_validation->set_rules("user", "Username", "trim|required");
        $this->form_validation->set_rules("pass", "Password", "trim|required");

        $data = $this->getUser->user($username,$password)->row();

        if ($this->form_validation->run() == FALSE)

            {
                    redirect('/admin');
            }

            else
            {

            		if($data->username == $username && $data->password == $password){\

            			 redirect('/admin/dashboard');
            			}else{
            				redirect('/admin');
            			}

                   
            }

   
     }

     public function add_event(){

     	$event_name = $this->input->post("event_name");
        $location = $this->input->post("location");
        $start_date = $this->input->post("start_date");
        $end_date = $this->input->post("end_date");
        $description = $this->input->post("description");
        $starttime = $this->input->post("start_time");
        $endtime = $this->input->post("end_time");

        $data = array(
        'name' => $event_name,
        'location' => $location,
        'description'=> $description,
        'startdate' => $start_date,
        'enddate' => $end_date,
        'starttime' => $starttime,
        'endtime' => $endtime
	     );

	    $this->db->insert('event', $data);

	    
	    echo '<script type="text/javascript">alert("Successfuly Addedd!");</script>';

    	redirect('/admin/events_list');

     }

     public function update_event($event_id){

     
     	$event_name = $this->input->post("event_name");
        $location = $this->input->post("location");
        $start_date = $this->input->post("start_date");
        $end_date = $this->input->post("end_date");
        $description = $this->input->post("description");
        $starttime = $this->input->post("start_time");
        $endtime = $this->input->post("end_time");

        $data = array(
        'name' => $event_name,
        'location' => $location,
        'description'=> $description,
        'startdate' => $start_date,
        'enddate' => $end_date,
        'starttime' => $starttime,
        'endtime' => $endtime,
        'event_id' => $event_id
	     );

        $this->db->set('name',$data['name'])
        		->set('location',$data['location'])
        		->set('startdate',$data['startdate'])
        		->set('enddate',$data['enddate'])
                ->set('description',$data['description'])
                ->set('starttime',$data['starttime'])
                ->set('endtime',$data['endtime'])
        		->where('event_id',$data['event_id'])
        		->update('event');

        redirect('/admin/events_list/'.$event_id);

         /**
            redirect('/admin/update_view'.$event_id);
        */
     }

     public function delete_event($event_id){

     	$this->db->set('deleted',1)
                 ->where('event_id',$event_id)
		         ->update('event');
		redirect('/admin/events_list');

     }

     public function add_ticket($event_id){

        $type = $this->input->post("type");
        $qty = $this->input->post("qty");
        $price = $this->input->post("price");

        $data = array(
        'type' => $type,
        'price' => $price,
        'quantity' => $qty,
        'event_id' => $event_id
	     );

	    $this->db->insert('ticket', $data);

        redirect('/admin/event_details/'.$event_id);


        /*
	    //Loop $i=0 $i<$qty $i++
	    //Insert Query
	    $data2 = array(
	    'event_id' => $event_id,
	    'type' => $type
	    );

	    for($i = 0 ; $i < $qty ; $i++){
	    	$this->db->insert('ticket',$data2);
	    }

	    redirect('/admin/ticket_category/'.$event_id);*/

     }

     public function activate_tickets($ticket_no){

        $data1 = $this->event_model->getTicketDetails($ticket_no)->row();
        $qrcode = $this->uuid->v4();
       

        for($i = 0 ; $i < $data1->quantity ; $i++){
                $data = array(

         'ticket_no' => $ticket_no,
         'event_id' => $data1->event_id,
         'qr_code' => $qrcode

        );
            $this->db->insert('ticket_details',$data);

                $qrcode = $this->uuid->v5('event_id');
        }

     

        redirect('/admin/event_details/'.$data1->event_id);

     }

     public function update_ticket($ticket_no){

        $data1 = $this->event_model->getTicketDetails($ticket_no)->row();

        $type = $this->input->post("type");
        $qty = $this->input->post("qty");
        $price = $this->input->post("price");

        $data = array(
        'type' => $type,
        'price' => $price,
        'quantity' => $qty
         );

        $this->db->set('type',$data['type'])
                 ->set('price',$data['price'])
                 ->set('quantity',$data['quantity'])
                 ->where('ticket_no',$ticket_no)
                 ->update('ticket');

        redirect('/admin/event_details/'.$data1->event_id);

     }

     public function delete_ticket($ticket_no){

        $data1 = $this->event_model->getTicketDetails($ticket_no)->row();

        $this->db->set('deleted',1)
                 ->where('ticket_no',$ticket_no)
                 ->where('event_id',$data1->event_id)
                 ->update('ticket');

        $this->db->set('deleted',1)
                 ->where('ticket_no',$ticket_no)
                 ->where('event_id',$data1->event_id)
                 ->update('ticket_details');

        redirect('/admin/event_details/'.$data1->event_id);

     }

     public function delete_each_ticket($ticket_details_no){

        $data = $this->event_model->getEachTicketDet($ticket_details_no)->row();

        $this->db->set('deleted',1)
                 ->where('ticket_details_no',$ticket_details_no)
                 ->where('event_id',$data->event_id)
                 ->update('ticket_details');

        redirect('/admin/event_details/'.$data->event_id);
     }

     public function delete_user($id){

        $this->db->where('id', $id);
        $this->db->delete('users');

        redirect('/admin/user_list');
     }

     public function getData(){

        header('Content-Type: application/json');

        $data1 = $this->event_model->getEvent2()->result();

        $data = array();

        foreach($data1 as $row){
            $data[] = $this->event_model->getTotalSales($row->event_id)->row();
        }

        print json_encode($data);


     }

     public function getData2(){

        header('Content-Type: application/json');

        $data1 = $this->event_model->getEvent2()->result();

        $data = array();

        foreach($data1 as $row){
            $data[] = $this->event_model->getExpectedSales($row->event_id)->row();
        }

        print json_encode($data);

     }


}

?>
