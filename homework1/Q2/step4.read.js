var fs = require('fs');
var async = require('async');


var taskAll = 0;
var taskCompleted = 0;

/*
    get connection
*/
var mysql = require('mysql2');
var connection = mysql.createConnection({
   user: 'root',
   password: '',
   host: 'localhost',
   database:'AmazonMovies'
});

var queue = async.queue(function (id, callback) {

  connection.query('INSERT INTO movies SET id=?', id, function(err, result) {
    taskCompleted++;
    if(err){
      console.log("fail");
      callback();
      return;
    }
    console.log("[%d/%d]",taskCompleted,taskAll);
    callback();
  });
}, 40);


var lines = fs.readFileSync('/Users/fowafolo/Desktop/URLbyId.txt').toString().split('\n');

lines.forEach(function (line) {
	if (line.length > 0) {
    queue.push(line);
    taskAll++;
	}
});
