<div class="form-signin">
    <div class="text-center">
        <img src="<?php echo base_url();?>assets/img/hyper.png" alt="Hyper Logo" height="100px" width="100px">
    </div>
    <hr>
    <div class="tab-content">
        <div id="login" class="tab-pane active">
            <form action="<?php echo base_url(); ?>/admin/login" method="POST">
                <p class="text-muted text-center">
                    Enter Username and Password
                </p>
                <input type="text" name="user" placeholder="Username" class="form-control top" required="required">
                <input type="password" name="pass" placeholder="Password" class="form-control bottom" required="required">
                <div class="checkbox">
		</div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
    </div>
    <hr>
  </div>