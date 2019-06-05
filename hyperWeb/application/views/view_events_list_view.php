
<div id="page-wrapper">

<?php $date_today = date("M-y-d"); ?>

	<div class="row">
		<h1><span class="fa fa-calendar" aria-hidden="true">List of Events</span></h1>
<?php if($activeEvent == true) { $disabled = "disabled"; } else { $disabled = "";} ?>
    <p> <button type="button" class = "btn btn-default <?=$disabled?>" <?=$disabled?> data-toggle="modal" data-target="#addModal"> <span class="fa fa-calendar-plus-o" aria-hidden="true"></span>
             Add Event
            </button>
    	</p>


			<table id="dataTables-example" class="table table-hover" >

    	 <thead>
    		<th>Event Name</th>
    		<th>Location</th>
    		<th>Start Date</th>
    		<th>End Date</th>
    		<th>Other Functions</th>
      </thead>
      <tbody>
    	    <tr>
						<?php
						if($latest_event->num_rows() > 0){
						$row2 = $latest_event->row(); ?>
    		<td><a href="<?php echo base_url();?>/admin/event_details/<?php echo $row2->event_id; ?>"><strong><?php echo $row2->name; ?></strong></a></td>
    		<td><?php echo $row2->location; ?></td>
    		<td><?php echo $row2->startdate; ?></td>
    		<td><?php echo $row2->enddate; ?></td>
    		<td>
    			<a href="<?php echo base_url(); ?>/admin/update_view/<?php echo $row2->event_id; ?>" class="fa fa-pencil-square-o" style="color:green;font-size: 14pt;" data-toggle="tooltip" title="Update Event"></a>
					<a href="<?php echo base_url(); ?>/admin/finish_event/<?php echo $row2->event_id; ?>" onClick="return confirm('Mark As Finished?')" class="fa fa-calendar-check-o" style="color:orange;font-size: 14pt;margin-left: 10px;" data-toggle="tooltip" title="Mark As Finished"></a>
    			<a href="<?php echo base_url(); ?>/admin/delete_event/<?php echo $row2->event_id; ?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="color:red;font-size: 14pt;margin-left: 10px;" data-toggle="tooltip" title="Delete Event"></a>
    		</td>
    		</tr>
				 <?php } ?>


    		</tbody>
    	</table>
			<br><br>
    	<table id="dataTables-example" class="table display" >

    	 <thead>
    		<th>Event Name</th>
    		<th>Location</th>
    		<th>Start Date</th>
    		<th>End Date</th>
    		<th>Other Functions</th>
      </thead>
      <tbody>
    		<?php
    		foreach ($getOldEvent->result()as $row ) {
    		?>
    	    <tr>
    		<td><a href="<?php echo base_url(); ?>/admin/event_details/<?php echo $row->event_id; ?>"><strong><?php echo $row->name; ?></strong></a></td>
    		<td><?php echo $row->location; ?></td>
    		<td><?php echo $row->startdate; ?></td>
    		<td><?php echo $row->enddate; ?></td>
    		<td>
    			<a href="#" class="fa fa-pencil-square-o" style="color:gray;font-size: 14pt;" data-toggle="tooltip" title="Update Event"></a>
    			<a href="#" class="fa fa-trash" style="color:gray;font-size: 14pt;margin-left: 10px;" data-toggle="tooltip" title="Delete Event"></a>



    		</td>
    		</tr>

    		<?php
    		}
    	    ?>

    		</tbody>
    	</table>
	</div>

<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class=" modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><label> Add User </label> </h4>
      </div>
      <div class="modal-body">
       <div class = "alert-msg"></div>
       <form class="form-horizontal" enctype='multipart/form-data' action="<?php echo base_url(); ?>/admin/add_event" method="POST">


                  <div class="form-group">
                  <label class="col-sm-2" for="name">Event Name:</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" name="event_name" id="name" placeholder="Event Name" required="required" >
                  </div>
                  </div>

                 <div class="form-group">
                 <label for="location" class="col-sm-2">Location:</label>
                 <div class="col-sm-10">
                <input type="text" class="form-control" name="location" id="location" placeholder="Location" required="required">
                 </div>
                 </div>
                    &nbsp;

                  <div class="form-group">
                  <label for="location" class="col-sm-2">Desciption:</label>
                  <div class="col-sm-10">
                  <textarea name="description" style="resize:none; width: 100%;" placeholder="Write a desciption...."></textarea>
                  </div>
                  </div>
                  &nbsp;

                  <div class="form-group">
                  <label for="startdate" class="col-sm-2">Start Date:</label>
                  <div class="col-sm-10">
                  <input type="date" class="form-control" name="start_date" id="startdate" required="required">
                  </div>
                  </div>
                  &nbsp;

                  <div class="form-group">
                  <label for="enddate" class="col-sm-2">End Date:</label>
                  <div class="col-sm-10">
                  <input type="date" class="form-control" name="end_date" id="enddate" required="required">
                  </div>
                  </div>

                  <div class="form-group">
                  <label for="starttime" class="col-sm-2">Start Time:</label>
                  <div class="col-sm-10">
                  <input type="time" class="form-control" name="start_time" id="enddate" required="required">
                  </div>
                  </div>

                  <div class="form-group">
                  <label for="endtime" class="col-sm-2">End Time:</label>
                  <div class="col-sm-10">
                  <input type="time" class="form-control" name="end_time" id="enddate" required="required">
                  </div>
                  </div>

                  <div class="form-group">
                  <label for="endtime" class="col-sm-2">Upload Photo Event:</label>
                  <div class="col-sm-10">
                  <!-- <input class="" type="file" name="picture" id="picture" required="required"> -->
                  <input type="file" name="picture" id="picture" required="required"/>
                  </div>
                  </div>


              <div class="modal-footer">
           <button type="submit" name="submit" value="Submit" class="btn btn-primary pull-right" style="margin-right: 15px;">Create</button>

      </div>


  </form>

    </div>
  </div>
</div>



<!-- Modal -->
<div id="addTicket" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Add Ticket</h4>
      </div>
      <div class="modal-body" style="height:300px;">
        <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/add_ticket/<?php echo $row->event_id; ?>" method="POST">
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
