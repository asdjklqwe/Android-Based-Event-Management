<div id="page-wrapper">


     <div class="row">
     <h1><span class="fa fa-users" aria-hidden="true">List of Card Numbers </span></h1>
     <!--<p> <button type="button" class = "btn btn-default" data-toggle="modal" data-target="#addModal"> <span class="fa fa-calendar-plus-o" aria-hidden="true"></span>
             Add Card Number
            </button>
     </p>-->
     <div class="panel-body">
               <table class="table table-hover display" id="dataTables-example">

           <thead>
               <th>Card Number</th>
               <th>Card Amount</th>
              <!-- <th>Status</th> -->
               <th>Other Functions</th>
          </thead>

          <tbody>
          <?php
          foreach ($card_details->result() as $row ) {
          ?>
               <tr id="cardID_<? echo $row->Card_No?>">
               <!-- new php card history -->
                    <td><a href="<?php echo base_url(); ?>/admin/card_history/<?php echo $row->Card_No; ?>"><strong><?php echo $row->Card_No; ?></strong></a></td>
                    <td><?php echo $row->Card_Amount; ?></td>
                     <!-- new update php -->
                   <!-- <td>
                     <?php
                      if($row->used==0){
                        echo "<p style='color:green;'>Not Used </p>";
                      }else{
                        echo "<p style='color:red;'>Used </p>";
                      }
                    ?>-->
                    </td>
                    <td>

                     <!--<a href="<?php echo base_url(); ?>/admin/update_card_views/<?php echo $row->Card_No; ?>" class="fa fa-pencil-square-o" style="color:green;font-size: 14pt;" data-toggle="tooltip" title="Update Event"></a>

                    <a href="<?php echo base_url(); ?>/admin/delete_card/<?php echo $row->Card_No; ?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="color:red;font-size: 14pt;margin-left: 10px;" data-toggle="tooltip" title="Delete Event"></a>-->

                    <a href="<?php echo base_url(); ?>/admin/load_card/<?php echo $row->Card_No; ?>" class = "btn btn-primary btn-sm update_btn"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                 Load Card
                    </button>
                    </td>
               </tr>
          <?php
          }
          ?>
          </tbody>
          </table>
          </div>
          </div>



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


</div>


</div>
</div>
-->

<!--<script>
$(document).ready(){
       $('.update_btn').click(function(){
           var $row = $(this).closest('tr');
           var cardNo = $row.attr('id').split('_')[1];
           $('#card_no').val(cardNo);
       });
     };
</script>
-->