<div id="page-wrapper">


     <div class="row">
    <?php if(isset($card_history['cardInfo'])) { ?>
      <h1><span class="fa fa-users" aria-hidden="true">HISTORY OF <?=strtoupper($card_history['cardInfo']->CardHolderName)?> (Card Number: <?=$card_history['cardInfo']->CardNumber?>)</span></h1>

     <div class="panel-body">
               <table class="table table-hover display" id="dataTables-example">

           <thead>
              <!-- <th>CardHolder Name</th>
              <th>Card Number</th> -->
               <th>Event Name</th>
               <th>QR CODE </th>
               <th>Type</th>
               <th>Date of Purchased</th>
               
          </thead>

          <tbody>
            <?php
            if(isset($card_history['transactions'])) {
                foreach ($card_history['transactions'] as $row ) {
          ?>
               <tr>
               <!-- new php card history -->
                    <!-- <td><?php echo $card_history['cardInfo']->CardHolderName; ?></a></td>
                    <td><?php echo $card_history['cardInfo']->CardNumber; ?></a></td> -->
                    <td><?php echo $row->EventName; ?></a></td>
                    <td><?php echo $row->qrcode; ?></td>
                    <td><?php echo $row->Type; ?></td>
                    <td><?php echo date('M d Y',strtotime($row->DatePurchased)); ?></td>


               </tr>
          <?php
        } } else { ?>
          <td rowspan="2" colspan="2">NO TRANSACTIONS YET!</td>
    <?php  } ?>
          </tbody>
          </table>
          </div>
          <?php  } else { ?>
            <h1><span class="fa fa-users" aria-hidden="true"> CARD IS NOT ACTIVE YET!</span></h1>
        <?php  } ?>



<!-- Modal
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class=" modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><label> Add User </label> </h4>
      </div>
      <div class="modal-body">
       <div class = "alert-msg"></div>
       <form class="form-horizontal" action="<?php echo base_url(); ?>/admin/add_card" method="POST">


                  <div class="form-group">
                  <label class="col-sm-2" for="name">Card Number:</label>
                  <div class="col-sm-10">
                  <input type="text" class="form-control" name="card_num" id="name" placeholder="Card Number" required="required" >
                  </div>
                  </div>

               <div class="modal-footer">
           <button type="submit" class="btn btn-primary pull-right" style="margin-right: 15px;">Submit</button>

      </div>

      </form>

    </div>


</div>-->
