var stompClient = null;
var clickCount = 0;

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
        stompClient.subscribe('/topic/loaded', function (response) {
            var answerList = JSON.parse(response.body);
            showAnswerList(answerList);
        });
        stompClient.subscribe('/topic/next', function (response) {
            var answerList = JSON.parse(response.body);
            showAnswerList(answerList);
        });
        stompClient.subscribe('/topic/next', function (response) {
            var answerList = JSON.parse(response.body);
            showAnswerList(answerList);
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

function load() {
    stompClient.send("/app/load", {}, JSON.stringify(
        {
            'timePlayerTwo': $("#timePlayerTwo").val(),
            'timePlayerOne': $("#timePlayerOne").val(),
            'namePlayerOne': $("#namePlayerOne").val(),
            'namePlayerTwo': $("#namePlayerTwo").val()
        }));
    clickCount = 0;
    $( "#load" ).prop('disabled', true);
    $( "#stop" ).prop('disabled', true);
    $( "#start" ).prop('disabled', false);
}

function next() {
    stompClient.send("/app/next", {});
    clickCount = 0;
    $( "#start" ).prop('disabled', false);
    $( "#stop" ).prop('disabled', true);
}

function start() {
    stompClient.send("/app/start", {});
    clickCount++;
    $( "#start" ).prop('disabled', true);
    $( "#stop" ).prop('disabled', false);
}

function stop() {
    stompClient.send("/app/stop", {});
    $( "#stop" ).prop('disabled', true);
    if(clickCount < 2){
        $( "#start" ).prop('disabled', false);
    }
}

function showAnswerList(answerList) {
    $("#answers").empty();
    $("#answers").append("<td><button id ='answer1Id' type='button'>" + answerList.answer1 + "</button></td>");
    $( "#answer1Id" ).click(function() {
        answerClick(answerList.answer1);
        $( "#answer1Id" ).prop('disabled', true);
    });
    $("#answers").append("<td><button id ='answer2Id' type='button'>" + answerList.answer2 + "</button></td>");
    $( "#answer2Id" ).click(function() {
        answerClick(answerList.answer2);
        $( "#answer2Id" ).prop('disabled', true);
    });
    $("#answers").append("<td><button id ='answer3Id' type='button'>" + answerList.answer3 + "</button></td>");
    $( "#answer3Id" ).click(function() {
        answerClick(answerList.answer3);
        $( "#answer3Id" ).prop('disabled', true);
    });
    $("#answers").append("<td><button id ='answer4Id' type='button'>" + answerList.answer4 + "</button></td>");
    $( "#answer4Id" ).click(function() {
        answerClick(answerList.answer4);
        $( "#answer4Id" ).prop('disabled', true);
    });
    $("#answers").append("<td><button id ='answer5Id' type='button'>" + answerList.answer5 + "</button></td>");
    $( "#answer5Id" ).click(function() {
        answerClick(answerList.answer5);
        $( "#answer5Id" ).prop('disabled', true);
    });
}

function answerClick(value){
    stompClient.send("/app/answerGiven", {},value);
    $("#answers").append("<tr><td>" + value + "</td></tr>");
}

$(function () {
    $( "#load" ).click(function() { load(); });
    $( "#next" ).click(function() { next(); });
    $( "#stop" ).click(function() { stop(); });
    $( "#start" ).click(function() { start(); });

    $( "#stop" ).prop('disabled', true);
    $( "#start" ).prop('disabled', true);
    connect();
});