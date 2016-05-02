<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Create Score Report</title>

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
    <div class="signedin">
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
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>Create a Score Report</h1>
                <form method=post action="http://uml.cs.uga.edu:8080/recdawgs_team9/CreateScoreReport">
                    <h4>Match between: </h4>
                    <select name="team">
                        <#list teams as team>
                        <option value="${team[0]}">${team[1]}</option>
                        </#list>
                    </select>
                    <h4>Home Team Score: </h4><INPUT NAME="homeScore" TYPE=text value="${homeScore}" size=50 required></INPUT>
                    <h4>Away Team Score: </h4><INPUT NAME="awayScore" TYPE=text value="${awayScore}" size=50 required></INPUT>
                    <br>
                    <INPUT type=submit></INPUT>
                </form>
            </div>
        </div>
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
    </div>
    
</body>
</html>
