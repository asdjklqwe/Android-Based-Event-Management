<div id="page-wrapper">

<?php $date_today = date("M-y-d"); ?>

	<div class="row">
		<h1><span class="fa fa-calendar" aria-hidden="true">List of Events</span></h1>
		<p> <button type="button" class = "btn btn-default" data-toggle="modal" data-target="#addModal"> <span class="fa fa-calendar-plus-o" aria-hidden="true"></span> 
             Add Event 
            </button>
    	</p>

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
    		foreach ($event_list->result()as $row ) {
    		?>
    	    <tr>
    		<td><a href="<?php echo base_url(); ?>/admin/event_details/<?php echo $row->event_id; ?>"><strong><?php echo $row->name; ?></strong></a></td>
    		<td><?php echo $row->location; ?></td>
    		<td><?php echo $row->startdate; ?></td>
    		<td><?php echo $row->enddate; ?></td>
    		<td>
    			<a href="<?php echo base_url(); ?>/admin/update_view/<?php echo $row->event_id; ?>" class="fa fa-pencil-square-o" style="color:green;font-size: 14pt;" data-toggle="tooltip" title="Update Event"></a>
    			<a href="<?php echo base_url(); ?>/admin/delete_event/<?php echo $row->event_id; ?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="color:red;font-size: 14pt;margin-left: 10px;" data-toggle="tooltip" title="Delete Event"></a>
          


    		</td>
    		</tr>

    		<?php
    		}
    	    ?>
    		
    		</tbody>
    	</table>
	</div>
	
<!-- Modal -->
<div id="addModal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Create Event</h4>
      </div>
      <div class="modal-body" style="height:500px;">
      <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/add_event" method="POST">
      	    <div class="form-group">
                  
                  <label class="col-sm-2" for="name">Event Name:</label>
                  <div class="col-sm-10">
                

                    <input type="text" class="form-control" name="event_name" id="name" placeholder="Event Name" required="required" >
                  </div>
                    
                
       				&nbsp;
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
                		<input type="date" name="start_date" id="startdate" min="<?php echo $date_today; ?>" required="required" value="<?php echo $row->startdate; ?>">
                	</div>		
                    
                
               </div>
                	&nbsp;
                  <div class="form-group">
                	<label for="enddate" class="col-sm-2">End Date:</label>
                	<div class="col-sm-10">
                		<input type="date" name="end_date" id="enddate" min="<?php echo $date_today; ?>" required="required" value="<?php echo $row->enddate; ?>">
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
                    
                
                
                <button type="submit" class="btn btn-primary pull-right" style="margin-right: 15px;">Create</button>                
             
          
      </form>
                
      
    
    </div>

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


