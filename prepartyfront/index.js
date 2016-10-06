
var path = require('path');

const express = require('express');
var request = require('request');
var cors = require('cors')

var app = express()

app.use(cors())

app.get('/',function(req,res){
  res.sendFile(path.join(__dirname+'/assets/templates/index.html'));
});

app.get('/login',function(req,res){
  res.sendFile(path.join(__dirname+'/assets/templates/login.html'));
});



app.listen(3000, function() {
  console.log('listening on 3000')
})
