<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>All Score Reports</title>

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
                <a class="navbar-brand page-scroll" href="index.html">
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
                        <a class="page-scroll" href="StudentMainWindow"><i class="fa fa-home"></i> Home</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about"><i class="fa fa-file-text"></i> Score Reports</a>
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
                <h1>All Score Reports</h1>
                </div>
                <table width="100%"  border="0" cellpadding="2" cellspacing="4" >
                  <tr>
                      <td><b>Id</b></td>
                      <td><b>Home Team</b></td>
                      <td><b>Home Points</b></td>
                      <td><b>Away Points</b></td>
                      <td><b>Away Team</b></td>
                      <td><b>Match Date</b></td>
                  </tr>
                 <#list reports as report>
                  <tr>
                      <td>${report[0]}</td>
                      <td>${report[1]}</td>
                      <td>${report[2]}</td>
                      <td>${report[3]}</td>
                      <td>${report[4]}</td>
                      <td>${report[5]}</td>
                  </tr>
                 </#list></table>
            </div>
            
        <hr>
        <p>Back to the <a href="ShowMainWindow"> main window</a></p>
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
