<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>All Leagues</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/grayscale.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="MainWindow">
                    <i class="fa fa-soccer-ball-o"></i>  <span class="light">Rec</span>Dawgs
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="MainWindow"><i class="fa fa-home"></i> Home</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="FindAllLeagues"><i class="fa fa-soccer-ball-o"></i> Leagues</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="FindAllTeams"><i class="fa fa-group"></i> Teams</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="FindAllVenues"><i class="fa fa-building"></i> Sports Venues</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="Logout"><i class="fa fa-sign-out"></i> Sign Out</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
    <section id="about" class="container content-section text-center">
        <form method=get action="http://localhost:8080/recdawgs_team9/ViewTeamsOfLeague">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>Current Active Leagues</h1>
                <p>Click a league name to see league teams</p>
            </div>
                <table width="100%"  border="0" cellpadding="2" cellspacing="4" >
                  <tr>
                      <td><b>Id</b></td>
                      <td><b>Name</b></td>
                      <td><b>Winner Team</b></td>
                      <td><b>Played Indoor</b></td>
                      <td><b>Minimum Teams</b></td>
                      <td><b>Maximum Teams</b></td>
                      <td><b>Minimum Members</b></td>
                      <td><b>Maximum Members</b></td>
                      <td><b>Match Rules</b></td>
                      <td><b>League Rules</b></td>
                  </tr>
                 <#list leagues as league>
                  <tr>
                      <td>${league[0]}</td>
                      <td><input type=submit name="league" value='${league[1]}' style="text-transform: none;background-color:transparent;color:rgba(66,220,163,1);"></td>
                      <td>${league[2]}</td>
                      <td>${league[3]}</td>
                      <td>${league[4]}</td>
                      <td>${league[5]}</td>
                      <td>${league[6]}</td>
                      <td>${league[7]}</td>
                      <td>${league[8]}</td>
                      <td>${league[9]}</td>
                  </tr>
                 </#list></table>
            </div>
        </form>
        <hr>
        <p>Back to the <a href="MainWindow"> main window</a></p>
    </section>
  

  
    <!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; Team 9</p>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/grayscale.js"></script>
    
</body>
</html>
