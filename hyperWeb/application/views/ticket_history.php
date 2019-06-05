<div id="page-wrapper">


	<div class="row">
     <h1><span class="fa fa-users" aria-hidden="true">Ticket History</span></h1>
          <div class="panel-body">
               <table class="table table-hover display" id="dataTables-example">
               <thead>
                    <th>Ticket ID</th>
                    <th>Event</th>
                    <th>Location</th>
                    <th>Ticket Type</th>
                    <th>Ticket Price</th>
                    <th>QR Code</th>
                    <th>Date of Purchased</th>
										<th>Status</th>
                    <th>Ticket Hyper Card</th>

               </thead>

               <tbody>
            <?php
          foreach ($ticket_historys->result() as $row ) {
          ?>


               <tr>
                    <td><?php echo $row->ticket_history_id; ?></td>
                    <td><?php echo $row->ticket_event; ?></td>
                    <td><?php echo $row->ticket_event_location; ?></td>
                    <td><?php echo $row->ticket_type; ?></td>
                    <td><?php echo $row->ticket_price; ?></td>
                    <td><?php echo $row->ticket_qrcode; ?></td>
                    <td><?php echo $row->ticket_date_of_purchase; ?></td>
                    <td>
											<?php
					                if($row->status==0){
					                  echo "<p style='color:green;'>Not Used </p>";
					                }else{
					                  echo "<p style='color:red;'>Used </p>";
					                }
					                    ?>
										</td>
                    <td><?php echo $row->ticket_hypercard_no; ?></td>

               </tr>
          <?php
          }
          ?>


          </tbody>
          </table>
          </div>


    </div>


</div>
