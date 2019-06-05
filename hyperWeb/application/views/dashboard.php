<div id="page-wrapper">


	<div class="row">
		<div class="col-lg-12">
            <h1 class="page-header">Dashboard</h1>
        </div>
    </div>
    <div class="row">
    	<div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-calendar fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><?php echo $events_count->num_rows(); ?></div>
                                    <div> Events </div>
                                </div>
                            </div>
                        </div>
                        <a href="<?php echo base_url('admin/events_list');?>">
                            <div class="panel-footer">
                                <span class="pull-left">View Events</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><?php echo $users_count->num_rows(); ?></div>
                                    <div>Users</div>
                                </div>
                            </div>
                        </div>
                        <a href="<?php echo base_url('admin/user_list');?>">
                            <div class="panel-footer">
                                <span class="pull-left">View Users</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-ticket fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><?php echo $overall_ticket->num_rows(); ?></div>
                                    <div>Overall Tickets Sold</div>
                                </div>
                            </div>
                        </div>
                        <a href="<?php echo base_url('admin/events_list');?>">
                            <div class="panel-footer">
                                <span class="pull-left">View Events</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-dollar fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><?php $row = $overall_sales->row(); echo $row->total;?> PHP</div>
                                    <div>Total Sales</div>
                                </div>
                            </div>
                        </div>
                        <a href="<?php echo base_url('admin/reports');?>">
                            <div class="panel-footer">
                                <span class="pull-left">View Reports</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
    </div>

	

</div>


<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
      url: "<?php echo base_url('admin/getData');?>",
      method: "GET",
      success: function(data){
        var names = [];
        var totals = [];
  

        for(var i in data){
          names.push(data[i].name)
          totals.push(data[i].total);
        }



        var chartdata = {
          labels: names,
          datasets: [
          {
            label: "Event",
            backgroundColor: "#26B99A",
            borderColor: "rgba(38, 185, 154, 0.7)",
            pointBorderColor: "rgba(38, 185, 154, 0.7)",
            pointBackgroundColor: "rgba(38, 185, 154, 0.7)",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointBorderWidth: 1,
            data: totals
          },
          ]

        };
        var ctx = $("#totalSales");

        var lineGraph = new Chart(ctx,{
          type: 'bar',
          data: chartdata
        });

      },
      error: function(data){
        console.log(data);
      }
    });
});
	
</script>
