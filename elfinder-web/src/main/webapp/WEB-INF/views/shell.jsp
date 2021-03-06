<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="/sam/jquery.min.js"></script>
    <script src="/sam/sockjs.js"></script>
    <script src="/sam/terminal/js/jquery.mousewheel-min.js"></script>
    <script src="/sam/terminal/js/jquery.terminal-min.js"></script>
    <link href="/sam/terminal/css/jquery.terminal.css" rel="stylesheet"/>
    <style>
        body {
            background-color: #000000;
        }

        #title {
            color: green;
        }
    </style>
</head>
<body>
<h1 id="title">IPM HPC Cluster Web Shell</h1>

<div id="terminal"></div>
<script>

    $(document).ready(function ($) {

        var ws = new SockJS("http://" + location.host + "/shell");
        var sshConnected = false;
        var passwordPhase = false;
        var user, host, port = 22;
        
        
        
        ws.onopen = function () {
            console.log("Baglandi");
        };

        var _term;

        ws.onmessage = function (evt) {
            var message = evt.data;
            if (message.indexOf("You are connected") == 0) {
                sshConnected = true;
                _term.set_mask(false);
                _term.set_prompt('>');
            }
            if (_term)
                _term.echo(message);
        }

        $('#terminal').terminal(function (command, term) {
                    _term = term;
                    if (command == "exit") {
                        ws.close();
                        return;
                    }

                    /* if (passwordPhase) {
                        passwordPhase = false;
                        ws.send("connect::" + user + "|" + host + "|" + port + "|" + command);
                        return;
                    } */

                    if (!sshConnected) {
                        var trimmedCommand = command.trim();
                        ws.send("connect:: lol");
                        return;
                        /* if (trimmedCommand.indexOf("ssh") == 0) {
                            var splits = trimmedCommand.split(" ");
                            for (var i = 0; i < splits.length; i++) {
                                var split = splits[i];
                                if (split.indexOf("@") != -1) {
                                    user = split.split("@")[0];
                                    host = split.split("@")[1];
                                }
                                else if (split == "-p") {

                                    port = splits[i + 1] || 22;
                                    console.log("-p", port);
                                }
                            }
                            passwordPhase = true;
                            term.set_mask(true);
                            term.set_prompt('Password>>>: ');
                            return;
                        } */

                    }

                    ws.send(command);

                }, {
                    prompt: '>',
                    name: 'IPM HPC Cluster Web Shell',
                    greetings: "Press Enter to connect"
                }
        );


    });


</script>
</body>
</html>
    