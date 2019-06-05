<?php $row = $ticket_details->row(); ?>

<div id="page-wrapper">


	<div class="row">


  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Update Ticket</h4>
      </div>
      <div class="modal-body" style="height:300px;">
        <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/update_ticket/<?php echo $row->ticket_no; ?>" method="POST">
            <div class="form-group">
                  
                  <label class="col-sm-2" for="name">Type:</label>
                  <div class="col-sm-10">
                

                    <input type="text" class="form-control" name="type" id="name" placeholder="VIP,Gold,Silver,GA" required="required" value="<?php echo $row->type; ?>">
                  </div>
                    
                
              &nbsp;
            </div>
                 <div class="form-group">
                  <label for="location" class="col-sm-2">Price:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="price" id="location" placeholder="Price" required="required" value="<?php echo $row->price; ?>">
                    </div>
                    
              
                  </div>
                    &nbsp;
                    <div class="form-group">
                  <label for="location" class="col-sm-2">Quantity:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="qty" id="location" placeholder="Quantity" required="required" value="<?php echo $row->quantity; ?>">
                    </div>
                    
              
                  </div>
               
     
        
                
                <button type="submit" class="btn btn-warning pull-right" style="margin-right: 15px;">Update</button>                
             
          
      </form>
      </div>
     
 

  </div>
	

</div>
