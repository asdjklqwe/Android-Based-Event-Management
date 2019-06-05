<div id="page-wrapper">

<?php $date_today = date("Y-m-d"); ?>
<?php $row = $event_details->row(); ?>
<div class="row">
<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Update Event</h4>
      </div>
      <div class="modal-body" style="height:550px;">
      <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/update_event/<?php echo $row->event_id;?>" method="POST">
            <div class="form-group">
                  
                  <label class="col-sm-2" for="name">Event Name:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="event_name" id="name" placeholder="Event Name" required="required" value="<?php echo $row->name; ?>">
                  </div>
                    
                
              &nbsp;
            </div>
                 <div class="form-group">
                  <label for="location" class="col-sm-2">Location:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="location" id="location" placeholder="Location" required="required" value="<?php echo $row->location; ?>">
                    </div>
                    
              
                  </div>
                    &nbsp;
                  <div class="form-group">
                  <label for="location" class="col-sm-2">Description:</label>
                    <div class="col-sm-10">
                      <textarea name="description" style="resize:none; width: 100%;" value=""><?php echo $row->description; ?></textarea>
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
                    <input type="time" class="form-control" name="start_time" id="enddate" required="required" value="<?php echo $row->starttime; ?>">
                  </div>    
                  </div>

                  <div class="form-group">
                  <label for="endtime" class="col-sm-2">End Time:</label>
                  <div class="col-sm-10">
                    <input type="time" class="form-control" name="end_time" id="enddate" required="required" value="<?php echo $row->endtime; ?>">
                  </div>    
                  </div>
                    
                
                
                <button type="submit" class="btn btn-warning pull-right" style="margin-right: 15px;">Update</button>                
             
          
      </form>

                </div>