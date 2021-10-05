var Script = function () {

    //$.validator.setDefaults({
    //    submitHandler: function() { alert("submitted!"); }
    //});

    $().ready(function() {
        // validate the comment form when it is submitted
        $("#feedback_form").validate();

        // validate signup form on keyup and submit
        $("#register_form").validate({
            rules: {
                userFname: {
                    required: true,
                    minlength: 3
                },
                userLname: {
                    required: true,
                    minlength: 3
                },
                userDescription: {
                    required: true,
                    minlength: 10
                },
                userUserName: {
                    required: true,
                    minlength: 5
                },
                userPassword: {
                    required: true,
                    minlength: 6
                },
                userCPassword: {
                    required: true,
                    minlength: 6,
                    equalTo: "#password"
                },
                userEmail: {
                    required: true,
                    email: true
                },
                userImage: {
                    required: true,
                    minlength: 10
                },
                topic: {
                    required: "#newsletter:checked",
                    minlength: 2
                },
                agree: "required"
            },
            messages: {                
                userFname: {
                    required: "Please enter a Firts Name.",
                    minlength: "Your First Name must consist of at least 3 characters long."
                },
                userLname: {
                    required: "Please enter a Last Name.",
                    minlength: "Your Last Name must consist of at least 3 characters long."
                },
                userDescription: {
                    required: "Please enter a Address.",
                    minlength: "Your Description must consist of at least 10 characters long."
                },
                userUserName: {
                    required: "Please enter a Username.",
                    minlength: "Your username must consist of at least 5 characters long."
                },
                userPassword: {
                    required: "Please provide a password.",
                    minlength: "Your password must be at least 6 characters long."
                },
                userCPassword: {
                    required: "Please provide a password.",
                    minlength: "Your password must be at least 6 characters long.",
                    equalTo: "Please enter the same password as above."
                },
                userEmail: "Please enter a valid email address.",
                userImage: "Please enter a valid Image URL.",
                agree: "Please accept our terms & condition."
            }
        });

        // propose username by combining first- and lastname
        $("#username").focus(function() {
            var firstname = $("#firstname").val();
            var lastname = $("#lastname").val();
            if(firstname && lastname && !this.value) {
                this.value = firstname + "." + lastname;
            }
        });

        //code to hide topic selection, disable for demo
        var newsletter = $("#newsletter");
        // newsletter topics are optional, hide at first
        var inital = newsletter.is(":checked");
        var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
        var topicInputs = topics.find("input").attr("disabled", !inital);
        // show when newsletter is checked
        newsletter.click(function() {
            topics[this.checked ? "removeClass" : "addClass"]("gray");
            topicInputs.attr("disabled", !this.checked);
        });
    });


}();