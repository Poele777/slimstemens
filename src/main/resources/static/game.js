var stompClient = null;
var timePlayerOne = 0;
var timePlayerTwo = 0;
var playerOneActive = true;
var timer = null;
var gameStarted = false;

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
            hideAnswerLabels();
            showAnswerList(answerList);
            setQuestion(answerList.question);
            setTime(answerList);
            calculatePlayerOneActive();
            setActivePlayerButtons();
        });
        stompClient.subscribe('/topic/next', function (response) {
            var answerList = JSON.parse(response.body);
            hideAnswerLabels();
            showAnswerList(answerList);
            setQuestion(answerList.question);
            calculatePlayerOneActive();
            setActivePlayerButtons();
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

function setQuestion(value) {
    $("#questionLabel").text(value);
    $("#questionLabel").hide();
}

function showQuestion() {
    $("#questionLabel").show();
}

function setActivePlayerButtons() {
    if(playerOneActive){
        setPlayerOneActive();
    }else{
        setPlayerTwoActive();
    }
}

function setPlayerOneActive(){
    $("#playerOneBlueLabel").show();
    $("#playerOneRedLabel").hide();
    $("#playerTwoBlueLabel").hide();
    $("#playerTwoRedLabel").show();
}

function setPlayerTwoActive(){
    $("#playerOneBlueLabel").hide();
    $("#playerOneRedLabel").show();
    $("#playerTwoBlueLabel").show();
    $("#playerTwoRedLabel").hide();
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function setTime(answerList){
    setTimePlayerOne(answerList.timePlayerOne);
    setTimePlayerTwo(answerList.timePlayerTwo);
}

function setTimePlayerOne(value){
    if(value < 0){
        value = 0;
    }
    $("#timePlayerOneLabel").text(value);
    timePlayerOne = value;
}

function setTimePlayerTwo(value){
    if(value < 0){
        value = 0;
    }
    $("#timePlayerTwoLabel").text(value);
    timePlayerTwo = value;
}

function calculatePlayerOneActive(){
    if(timePlayerOne < timePlayerTwo){
        playerOneActive = true;
    }else if(timePlayerOne > timePlayerTwo){
        playerOneActive = false;
    }
}

function showAnswerList(answerList) {
    $("#answer1Label").text(answerList.answer1);
    $("#answer2Label").text(answerList.answer2);
    $("#answer3Label").text(answerList.answer3);
    $("#answer4Label").text(answerList.answer4);
    $("#answer5Label").text(answerList.answer5);
}

function hideAnswerLabels() {
    $("#answer1Label").hide();
    $("#answer2Label").hide();
    $("#answer3Label").hide();
    $("#answer4Label").hide();
    $("#answer5Label").hide();
    $("#btn1").hide();
    $("#btn2").hide();
    $("#btn3").hide();
    $("#btn4").hide();
    $("#btn5").hide();
}

function answerGiven(answer){
    if($("#answer1Label").text() == answer){
        $("#answer1Label").show();
        $("#btn1").show();
    }

    if($("#answer2Label").text() == answer){
        $("#answer2Label").show();
        $("#btn2").show();
    }

    if($("#answer3Label").text() == answer){
        $("#answer3Label").show();
        $("#btn3").show();
    }

    if($("#answer4Label").text() == answer){
        $("#answer4Label").show();
        $("#btn4").show();
    }

    if($("#answer5Label").text() == answer){
        $("#answer5Label").show();
        $("#btn5").show();
    }

    if (gameStarted) {
        if (playerOneActive) {
            setTimePlayerTwo(timePlayerTwo - 20);
        } else {
            setTimePlayerOne(timePlayerOne - 20);
        }
    }
}

//STOPWATCH http://jsfiddle.net/qHL8Z/3/
function start(){
    gameStarted=true;
    showQuestion();
    if (playerOneActive) {
        if (timer !== null) return;
        timer = setInterval(function () {
            timePlayerOne = timePlayerOne - 1;
            $("#timePlayerOneLabel").text(timePlayerOne);
        }, 1000);
    } else {
        if (timer !== null) return;
        timer = setInterval(function () {
            timePlayerTwo = timePlayerTwo - 1;
            $("#timePlayerTwoLabel").text(timePlayerTwo);
        }, 1000);
    }
}

function stop(){
    gameStarted=false;
    playerOneActive = !playerOneActive;
    if(playerOneActive){
        setPlayerOneActive();
    }else{
        setPlayerTwoActive();
    }
    clearInterval(timer);
    timer = null;
}

$(function () {
    connect();
    hideAnswerLabels();
});