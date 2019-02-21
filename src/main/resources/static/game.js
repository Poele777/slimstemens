var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/answer', function (greeting) {
            showAnswer(JSON.parse(greeting.body).answer);
        });
        stompClient.subscribe('/topic/loaded', function (response) {
            var answerList = JSON.parse(response.body);
            showAnswerList(answerList);
        });
        stompClient.subscribe('/topic/answerGiven', function (response) {
            var answer = JSON.parse(response.body);
            answerGiven(answer.answer);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function showAnswer(answer) {
    $("#answers").append("<tr><td>" + answer + "</td></tr>");
}

function showAnswerList(answerList) {
    $("#timePlayerOneLabel").val(answerList.timePlayerOne);
    $("#timePlayerTwoLabel").val(answerList.timePlayerTwo);
    $("#answer1Label").val(answerList.answer1);
    $("#answer2Label").val(answerList.answer2);
    $("#answer3Label").val(answerList.answer3);
    $("#answer4Label").val(answerList.answer4);
    $("#answer5Label").val(answerList.answer5);
    $("#answer1Label").hide();
    $("#answer2Label").hide();
    $("#answer3Label").hide();
    $("#answer4Label").hide();
    $("#answer5Label").hide();
}

function answerGiven(answer){
    alert(answer);

    if($("#answer1Label").val() == answer){
        $("#answer1Label").show();
    }

    if($("#answer2Label").val() == answer){
        $("#answer2Label").show();
    }

    if($("#answer3Label").val() == answer){
        $("#answer3Label").show();
    }

    if($("#answer4Label").val() == answer){
        $("#answer4Label").show();
    }

    if($("#answer5Label").val() == answer){
        $("#answer5Label").show();
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    connect();
});