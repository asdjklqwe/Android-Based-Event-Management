<?php $row = $user_list->row(); ?>

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
        <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/update_user/<?php echo $row->id; ?>" method="POST">
            <div class="form-group">

                  <label class="col-sm-2" for="name">Username:</label>
                  <div class="col-sm-10">


                    <input type="text" class="form-control" name="username" id="name" required="required" value="<?php echo $row->username; ?>">
                  </div>


              &nbsp;
            </div>
                 <div class="form-group">
                  <label for="location" class="col-sm-2">Last Name:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="lname" id="location"  required="required" value="<?php echo $row->lastname; ?>">
                    </div>


                  </div>
                    &nbsp;
                    <div class="form-group">
                  <label for="location" class="col-sm-2">First Name:</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="fname" id="fname" required="required" value="<?php echo $row->firstname; ?>">
                    </div>


                  </div>
						 

                <button type="submit" class="btn btn-warning pull-right" style="margin-right: 15px;">Update</button>


      </form>
      </div>



  </div>


</div>
