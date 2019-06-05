<?php $row = $event_details->row();
      $id = $row->event_id;
?>
<div id="page-wrapper">

		<h1><span class="fa fa-calendar" aria-hidden="true"></span><?php echo $row->name;?> </h1>



    <?php if($row->finished == 0){ ?>
      <a href="#" class="btn btn-primary btn-xs pull-right" style="font-size: 14pt;margin-top:-30px;" data-toggle="modal" data-target="#addTicket<?php echo $id; ?>"  title="Add Ticket">Add Ticket</a>
      <?php }else{ ?>
      <a href="#" class="btn btn-primary btn-xs pull-right" style="background-color:gray;font-size: 14pt;margin-top:-30px;" title="Add Ticket">Add Ticket</a>
        <?php } ?>

	<div class="row">
		 <!-- <table id="myTable" class="table" >


        <th>Event Name</th>
        <th>Location</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Other Functions</th>


          <tr>
        <td><a href="<?php echo base_url(); ?>/admin/event_details/<?php echo $row->event_id; ?>"><strong><?php echo $row->name; ?></strong></a></td>
        <td><?php echo $row->location; ?></td>
        <td><?php echo $row->startdate; ?></td>
        <td><?php echo $row->enddate; ?></td>
        </tr>



      </table> -->
      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
         <table class="table table-bordered">
        <tr>
          <th>Venue:</th>
          <td><?php echo $row->location; ?></td>
        </tr>
        <tr>
          <th>Description:</th>
          <td><?php echo $row->description; ?></td>
        </tr>
        <tr>
          <th>Start Date:</th>
          <td><?php echo $row->startdate; ?></td>
        </tr>
        <tr>
          <th>End Date:</th>
          <td><?php echo $row->enddate; ?></td>
        </tr>
        <tr>
          <th>Start Time:</th>
          <td><?php echo $row->starttime; ?></td>
        </tr>
        <tr>
          <th>End Time:</th>
          <td><?php echo $row->endtime; ?></td>
        </tr>
      </table>
      </div>

      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <table class="table display" id="dataTables-example">
        <thead>
          <th>Type</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Function</th>
        </thead>
        <tbody>
          <?php
          foreach ($ticket_details->result() as $row1 ) {
          ?>
          <tr>

            <td><?php echo $row1->type; ?></td>
            <td><?php echo $row1->price; ?></td>
            <td><?php echo $row1->quantity; ?></td>
            <?php if($row->finished == 0){ ?>
            <td>
              <a href="<?php echo base_url(); ?>/admin/activate_tickets/<?php echo $row1->ticket_no;?>" onClick="return confirm('Activate Tickets?')" class="fa fa-ticket" style="font-size: 14pt;color:orange;"></a>
              <a href="<?php echo base_url(); ?>/admin/update_ticket_v/<?php echo $row1->ticket_no;?>" class="fa fa-pencil-square-o" style="font-size: 14pt;color: green;margin-left: 10px;"></a>
              <a href="<?php echo base_url(); ?>/admin/delete_ticket/<?php echo $row1->ticket_no;?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="font-size: 14pt;color:red;margin-left: 10px;"></a>
            </td>
            <?php }else{ ?>
              <td>
                <a href="#" class="fa fa-ticket" style="font-size: 14pt;color:gray;"></a>
                <a href="#" class="fa fa-pencil-square-o" style="font-size: 14pt;color: gray;margin-left: 10px;"></a>
                <a href="#" class="fa fa-trash" style="font-size: 14pt;color:gray;margin-left: 10px;"></a>
              </td>
              <?php } ?>
          </tr>
          <?php
          }
          ?>
          </tbody>
        </table>
      </div>

    </div>

    <div class="row">
      <table class="table display" id="dataTables-example">
      <thead>

        <th>Type</th>
        <th>Status</th>
        <th>Purchased</th>
        <th>QR code</th>
        <!--<th>Function</th>-->
      </thead>
        <tbody>
        <?php
          foreach ($each_ticket->result() as $row2 ) {
        ?>
         <tr>

           <td><?php echo $row2->type; ?></td>
           <td>
              <?php
                if($row2->status==0){
                  echo "<p style='color:green;'>Not Used </p>";
                }else{
                  echo "<p style='color:red;'>Used </p>";
                }
              ?>
           </td>
           <td>
            <?php
                if($row2->purchased==0){
                  echo "<p style='color:green;'>Unsold </p>";
                }else{
                  echo "<p style='color:red;'>Sold </p>";
                }
              ?>
           </td>
           <td>
             <?php echo $row2->qr_code; ?>
           </td>
           <!--<?php if($row->finished == 0){ ?>
            <td>
           <a href="<?php echo base_url('admin/delete_each_ticket/'.$row2->ticket_details_no); ?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="font-size: 14pt;color:red;margin-left: 10px;"></a>
           </td>

             <?php }else{ ?>
               <td>
                 <a href="#" class="fa fa-trash" style="font-size: 14pt;color:gray;margin-left: 10px;"></a>
               </td>
               <?php } ?>-->
         </tr>
        <?php
          }
        ?>
        </tbody>
      </table>

    </div>


</div>





<!-- Modal -->
<div id="addTicket<?php echo $id; ?>" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Ticket</h4>
      </div>
      <div class="modal-body" style="height:300px;">
        <form class="form-horizontal" action="<?php echo base_url('admin/add_ticket/'.$id); ?>" method="POST">
            <div class="form-group">

                  <label class="col-sm-2" for="name">Type:</label>
                  <div class="col-sm-10">


                    <input type="text" class="form-control" name="type" id="name" placeholder="VIP,Gold,Silver,GA" required="required" >
                  </div>


              &nbsp;
            </div>
                 <div class="form-group">
                  <label for="location" class="col-sm-2">Price:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="price" id="location" placeholder="Price" required="required">
                    </div>


                  </div>
                    &nbsp;
                    <div class="form-group">
                  <label for="location" class="col-sm-2">Quantity:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="qty" id="location" placeholder="Quantity" required="required">
                    </div>


                  </div>




                <button type="submit" class="btn btn-primary pull-right" style="margin-right: 15px;">Create</button>


      </form>
      </div>

    </div>

  </div>
</div>
