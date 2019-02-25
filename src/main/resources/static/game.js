var stompClient = null;
var timePlayerOne = 0;
var timePlayerTwo = 0;
var playerOneActive = true;
var timer = null;

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
            hideAnswerLabels();
            showAnswerList(answerList);
            setTime(answerList);
            isPlayerOneActive();
        });
        stompClient.subscribe('/topic/next', function (response) {
            var answerList = JSON.parse(response.body);
            hideAnswerLabels();
            showAnswerList(answerList);
            isPlayerOneActive();
        });
        stompClient.subscribe('/topic/answerGiven', function (response) {
            var answer = JSON.parse(response.body);
            answerGiven(answer.answer);
        });
        stompClient.subscribe('/topic/start', function (response) {
            start();
        });
        stompClient.subscribe('/topic/stop', function (response) {
            stop();
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

function setTime(answerList){
    setTimePlayerOne(answerList.timePlayerOne);
    setTimePlayerTwo(answerList.timePlayerTwo);
}

function setTimePlayerOne(value){
    if(value < 0){
        value = 0;
    }
    $("#timePlayerOneLabel").val(value);
    timePlayerOne = value;
}

function setTimePlayerTwo(value){
    if(value < 0){
        value = 0;
    }
    $("#timePlayerTwoLabel").val(value);
    timePlayerTwo = value;
}

function isPlayerOneActive(){
    if(timePlayerOne < timePlayerTwo){
        playerOneActive = true;
    }else if(timePlayerOne > timePlayerTwo){
        playerOneActive = false;
    }
    return playerOneActive;
}

function showAnswerList(answerList) {
    $("#answer1Label").val(answerList.answer1);
    $("#answer2Label").val(answerList.answer2);
    $("#answer3Label").val(answerList.answer3);
    $("#answer4Label").val(answerList.answer4);
    $("#answer5Label").val(answerList.answer5);
}

function hideAnswerLabels() {
    $("#answer1Label").hide();
    $("#answer2Label").hide();
    $("#answer3Label").hide();
    $("#answer4Label").hide();
    $("#answer5Label").hide();
}

function answerGiven(answer){
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

    if(playerOneActive){
        setTimePlayerTwo(timePlayerTwo - 20);
    }else{
        setTimePlayerOne(timePlayerOne - 20);
    }
}

//STOPWATCH http://jsfiddle.net/qHL8Z/3/
function start(){
    if (playerOneActive) {
        if (timer !== null) return;
        timer = setInterval(function () {
            timePlayerOne = timePlayerOne - 1;
            $("#timePlayerOneLabel").val(timePlayerOne);
        }, 1000);
    } else {
        if (timer !== null) return;
        timer = setInterval(function () {
            timePlayerTwo = timePlayerTwo - 1;
            $("#timePlayerTwoLabel").val(timePlayerTwo);
        }, 1000);
    }
}

function stop(){
    playerOneActive = !playerOneActive;
    clearInterval(timer);
    timer = null;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    connect();
    hideAnswerLabels();
});