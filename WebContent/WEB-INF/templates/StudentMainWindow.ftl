<html>
    <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
        
    <title>RecDawgs</title>

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
        <div class="signedin">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="MainWindow">
                    <span style="color: black"><i class="fa fa-soccer-ball-o"></i> <span class="light">Rec</span>Dawgs</span>
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
        </div>
    </nav>
    
    <!-- Intro Header -->
    <header class="intro">
        <div class="signedin">
            <div class="intro-body">
                <div class="container">
                    <div class="row">
                        <h1>Welcome ${firstname}</h1>
                        <h3>You are logged in as ${username}</h3>
                        <div class="col-md-8 col-md-offset-2">
                            <h1 class="brand-heading"></h1>
                            <p class="intro-text"></p>
                            <a href="#about" class="btn btn-circle page-scroll">
                                <i class="fa fa-angle-double-down animated"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>


    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <i class="fa fa-soccer-ball-o"></i>
                <h3>View all <a href="FindAllLeagues">Leagues</a></h3>
                
                <hr>

                <i class="fa fa-group"></i>
                <h3>View all <a href="FindAllTeams">Teams</a></h3>
                <i class="fa fa-user"></i> <i class="fa fa-group"></i>
                <h3>View my <a href="FindMyTeams">Teams</a></h3>
                <i class="fa fa-group"></i>
                <h3>Report a <a href="CreateScoreReport">Match</a></h3>

                <hr>
                
                <i class="fa fa-building"></i>
                <h3>View all <a href="FindAllVenues">Sports Venues</a></h3>
                
                <hr>

                <i class="fa fa-file-text"></i>
                <h3>View all <a href="FindAllReports">Score Reports</a></h3>
                
                <hr>

                <i class="fa fa-user"></i>
                <h3>Modify <a href="ModifyAccount">Account</a></h3>
                
                <hr>
                
                <i class="fa fa-sign-out"></i>
                <h3 id="signout"><a href="Logout">Sign Out</a></h3>
                
                <hr>
            </div>
        </div>
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
