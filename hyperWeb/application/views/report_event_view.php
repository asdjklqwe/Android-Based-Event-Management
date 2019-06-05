<?php $row = $event_details->row(); ?>
<div id="page-wrapper">
  <h1><span class="fa fa-exclamation-circle" aria-hidden="true"></span><?php echo $row->name;?> </h1>
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <table class="table table-hover display" id="dataTables-example">

    <thead>
        <th>Ticket Number</th>
        <th>Purchased By</th>
        <th>Date of Purchase</th>
        <th>Ticket Type</th>
        <th>Status</th>
   </thead>

   <tbody>
     <?php
   foreach ($event_det_ticket->result() as $row ) {
   ?>
        <tr>
          <td><?php echo $row->ticket_details_no; ?></td>
          <td><?php echo $row->lastname; ?></td>
          <td><?php echo $row->ticket_date_of_purchase; ?></td>
          <td><?php echo $row->type; ?></td>
          <td>
            <?php
              if($row->status==0){
                echo "<p style='color:green;'>Not Used </p>";
              }else{
                echo "<p style='color:red;'>Used </p>";
              }
            ?>
          </td>

        </tr>
   <?php
   }
   ?>
   </tbody>
   </table>
      </div>
    </div>
</div>
