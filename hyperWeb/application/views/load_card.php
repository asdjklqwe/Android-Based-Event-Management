<?php $row = $load->row(); ?>

<div id="page-wrapper">


  <div class="row">


  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Update Amount</h4>
      </div>
      <div class="modal-body" style="height:300px;">
        <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/update_amount" method="POST">
            <div class="form-group">
                  <label class="col-sm-2" for="name">Amount:</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" name="card_amount" id="name" placeholder="Amount" required="required" >
                  <input type="hidden" name="card_no" id="card_no" value="<?=$row->CardNo?>"/>
                  </div>
                  </div>

                <button type="submit" class="btn btn-warning pull-right" style="margin-right: 15px;">Update</button>


      </form>
      </div>



  </div>


</div>
</div>
</div>




<!--<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class=" modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><label> Load User </label> </h4>
      </div>
      <div class="modal-body">
       <div class = "alert-msg"></div>
       <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/load_card" method="POST">


                  <div class="form-group">
                  <label class="col-sm-2" for="name">Amount:</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" name="card_amount" id="name" placeholder="Amount" required="required" >
                  <input type="hidden" name="card_no" id="card_no"/>
                  </div>
                  </div>

               <div class="modal-footer">
           <button type="submit" class="btn btn-primary pull-right" style="margin-right: 15px;">Submit</button>

                </div>

      </form>

    </div>


</div>


</div>
</div>
</div>
-->
