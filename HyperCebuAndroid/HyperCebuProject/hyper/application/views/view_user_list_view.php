<div id="page-wrapper">


	<div class="row">
     <h1><span class="fa fa-users" aria-hidden="true">List of Users</span></h1>
          <div class="panel-body">
               <table class="table table-hover display" id="dataTables-example">
               <thead>
                    <th>ID</th>
                    <th>Card Number</th>
                    <th>Card Amount</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Function</th>
               </thead>
                  
               <tbody>
            <?php
          foreach ($user_lists->result() as $row ) {
          ?>
               <tr>
                    <td><?php echo $row->id; ?></td>
                    <td><?php echo $row->username; ?></td>
                    <td><?php echo $row->firstname; ?></td>
                    <td><?php echo $row->lastname; ?></td>
                    <td><a href="<?php echo base_url(); ?>/admin/delete_user/<?php echo $row->id; ?>" onClick="return confirm('Are you sure you want to delete?')" class="fa fa-trash" style="color:red;font-size: 14pt;" data-toggle="tooltip" title="Delete Event"></a></td>
               </tr>
          <?php
          } 
          ?>
          </tbody>
          </table>
          </div>
		

    </div>
	

</div>
