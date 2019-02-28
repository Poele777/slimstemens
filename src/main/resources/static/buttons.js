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
        stompClient.subscribe('/topic/loaded', function (response) {
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
    stompClient.send("/app/load", {}, JSON.stringify({'timePlayerTwo': $("#timePlayerTwo").val(), 'timePlayerOne': $("#timePlayerOne").val()}));
}

function next() {
    stompClient.send("/app/next", {});
}

function start() {
    stompClient.send("/app/start", {});
}

function stop() {
    stompClient.send("/app/stop", {});
}

function showAnswerList(answerList) {
    $("#answers").empty();
    $("#answers").append("<td><button id ='answer1Id' type='button'>" + answerList.answer1 + "</button></td>");
    $( "#answer1Id" ).click(function() { answerClick(answerList.answer1); });
    $("#answers").append("<td><button id ='answer2Id' type='button'>" + answerList.answer2 + "</button></td>");
    $( "#answer2Id" ).click(function() { answerClick(answerList.answer2); });
    $("#answers").append("<td><button id ='answer3Id' type='button'>" + answerList.answer3 + "</button></td>");
    $( "#answer3Id" ).click(function() { answerClick(answerList.answer3); });
    $("#answers").append("<td><button id ='answer4Id' type='button'>" + answerList.answer4 + "</button></td>");
    $( "#answer4Id" ).click(function() { answerClick(answerList.answer4); });
    $("#answers").append("<td><button id ='answer5Id' type='button'>" + answerList.answer5 + "</button></td>");
    $( "#answer5Id" ).click(function() { answerClick(answerList.answer5); });
}

function answerClick(value){
    stompClient.send("/app/answerGiven", {},value);
    $("#answers").append("<tr><td>" + value + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#load" ).click(function() { load(); });
    $( "#next" ).click(function() { next(); });
    $( "#stop" ).click(function() { stop(); });
    $( "#start" ).click(function() { start(); });

    connect();
});