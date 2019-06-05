<div id="page-wrapper">
  <div class="row">
    <div class="col-lg-12">
            <h1 class="page-header">Reports</h1>
        </div>
    </div>
  <div class="row">
    <table class="table table-bordered" style="width:80%; margin: 0 auto;">
      <thead>
        <tr>
          <th>Event Name</th>
          <th>Total Tickets</th>
          <th>Remaining Tickets</th>
          <th>Tickets Sold</th>
          <th>Unused Tickets</th>
          <th>Sales</th>
        </tr>
      </thead>
      <tbody>
       <?php
          foreach ($event_list->result() as $row ) {
        ?>
          <tr>
            <td><?php echo $row->name; ?></td>
            <td><?php echo $this->event_model->getTotalTickets($row->event_id)->num_rows(); ?></td>
            <td><?php echo $this->event_model->getRemainingTickets($row->event_id)->num_rows(); ?></td>
            <td><?php echo $this->event_model->getSoldTickets($row->event_id)->num_rows(); ?></td>
            <td><?php echo $this->event_model->getUnusedTickets($row->event_id)->num_rows(); ?></td>
            <?php $row2 = $this->event_model->getSales($row->event_id)->row(); ?>
            <td><?php 
            if($row2->total == 0){
              echo 0;
            }else{
            echo $row2->total; 
          }
            ?></td>
          </tr>  
          <?php } ?>           
      </tbody>
    </table>
  </div>
</div>