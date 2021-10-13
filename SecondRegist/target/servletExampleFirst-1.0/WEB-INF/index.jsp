<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Register form</title>
    <style type="text/css">
        .login{
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -200px;
            margin-left: -150px;
            display: flex;
            -ms-flex-direction: column;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
        }
        input{
            margin:0 auto;
            background-color: #b9e5ff;
            border-radius: 25px;
            width: 305px;
            height: 63px;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 28px;
            font-style: normal;
            text-transform: lowercase;
            padding-left: 20px;
            margin-top: 20px;
            border: none;
        }
        input[type=text]:focus, input[type=password]:focus, input[type=]:focus{
            background-color: #6fbfff;
            outline: none;
        }
        input[type="submit"]{
            font-family: Arial, Helvetica, sans-serif;
            font-size:35px;
            width: 290px;
            height: 90px;
            border-radius: 30px;
            margin-top: 45px;

        }
    </style>
</head>

<body>
<div class="login" align = "center">
    <form method="post" action="register">
        <h1>${status}</h1>
        <input type="text" placeholder="name" name="name" required /><br/>
        <input type="email" placeholder="email" name="email" required /><br/>
        <input type="password" placeholder="password" name="pass" required /><br/>
        <input type="password" placeholder="repeat password" name="password_repeat" required> <br/>
        <input type="submit" value="register" />
    </form>
</div>
</body>
</html>