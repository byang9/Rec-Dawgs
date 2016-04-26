<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>RecDawgs - Sign In</title>

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

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="StudentMainWindow">
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
                        <a class="page-scroll" href=""><i class="fa fa-user"></i> Modify Account</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Sign in Section -->
    <section id="signin" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <form method=post action="http://localhost:8080/recdawgs_team9/ModifyAccountResult" autocomplete="off" spellcheck="false">
                    <h4>First Name: </h4><INPUT NAME="firstname" TYPE=text value="${firstname}" size=50 required></INPUT>
                    <h4>Last Name: </h4><INPUT NAME="lastname" TYPE=text value="${lastname}" size=50 required></INPUT>
                    <h4>Username: </h4><INPUT NAME="username" TYPE=text value="${username}" size=50 required></INPUT>
                    <h4>Password: </h4><INPUT NAME="password" TYPE=password value="${password}" size=50 required></INPUT>
                    <h4>Email: </h4><INPUT NAME="email" TYPE=email value="${email}" size=50 required></INPUT>
                    <h4>Student ID: </h4><INPUT NAME="studentid" TYPE=number value="${studentId}" size=50 required></INPUT>
                    <h4>Major: </h4><INPUT NAME="major" TYPE=text value="${major}" size=50></INPUT>
                    <h4>Address: </h4><INPUT NAME="address" TYPE=text value="${address}" size=50></INPUT>
                    <br>
                    <INPUT type=submit></INPUT>
                </form>
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
