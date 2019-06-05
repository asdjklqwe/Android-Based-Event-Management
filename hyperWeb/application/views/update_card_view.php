<?php $row = $card_list->row(); ?>

<div id="page-wrapper">
		<div class="row">
	  	<div class="modal-dialog">
	    <!-- Modal content-->
	    	<div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Update User</h4>
		      </div>
		      <div class="modal-body" style="height:300px;">
		        <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/update_card/<?php echo $row->Card_No; ?>" method="POST">
		            <div class="form-group">

		                  <label class="col-sm-2" for="name">Card Number:</label>
		                  <div class="col-sm-10">

		                    <input type="text" class="form-control" name="card_num" id="name" required="required" value="<?php echo $row->Card_No; ?>">
		                  </div>


		                  <button type="submit" class="btn btn-warning pull-right" style="margin-right: 15px;">Update</button>
		      </form>
		      	</div>
		     </div>
		 		</div>
			</div>
		</div>
</div>
