<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hyper Cebu</title>

    <!-- Bootstrap Core CSS -->
    <link href="<?php echo base_url('dash_template/vendor/bootstrap/css/bootstrap.min.css'); ?>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<?php echo base_url('dash_template/vendor/metisMenu/metisMenu.min.css'); ?>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<?php echo base_url('dash_template/dist/css/sb-admin-2.css');?>" rel="stylesheet">


    <!-- Custom Fonts -->
    <link href="<?php echo base_url('dash_template/vendor/font-awesome/css/font-awesome.min.css');?>" rel="stylesheet" type="text/css">

    <link rel="stylesheet"  href="<?php echo base_url('dash_template/vendor/datatables-plugins/dataTables.bootstrap.css'); ?>">
    <link rel="stylesheet"  href="<?php echo base_url('dash_template/vendor/datatables-responsive/dataTables.responsive.css'); ?>">


     <script type="text/javascript" src="<?php echo base_url('dash_template/vendor/jquery/jquery3.min.js');?>"></script>

    
 



    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<?php echo base_url('admin/dashboard');?>"><font size = "6"><b>Hyper Cebu</b></font></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
               
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li class="divider"></li>
                        <li><a href="<?php echo base_url('/admin');?>"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">

                        <li>
                            <a href="<?php echo base_url('admin/dashboard');?>"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                       
                        <li>
                            <a href="<?php echo base_url('admin/events_list');?>"><i class="fa fa-calendar fa-fw"></i> Events</a>
                        </li>
                        <li>
                            <a href="<?php echo base_url('admin/user_list');?>"><i class="fa fa-users fa-fw"></i> Manage Users</a>
                        </li>
                        <li>
                            <a href="<?php echo base_url('admin/reports');?>"><i class="fa fa-bar-chart fa-fw"></i> Reports</a>
                        </li>

                        <li>
                            <a href="<?php echo base_url('admin/card_detail');?>"><i class="fa fa-cc-discover"></i> Card Holders</a>
                        </li>

                        <li>
                            <a href="<?php echo base_url('admin/ticket_history');?>"><i class="fa fa-ticket"></i> Ticket History </a>
                        </li>

                       
        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>