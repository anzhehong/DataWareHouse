var request = require('request');
var async = require('async');
var cheerio = require('cheerio');

var fs = require('fs');

var taskAll = 0;
var taskCompleted = 0;


//建立数据库连接
var mysql = require('mysql2');
var connection = mysql.createConnection({
   user: 'root',
   password: '',
   host: 'localhost',
   database:'AmazonMovies'
});

//创建一个工作队列
var queue = async.queue(function (elem, callback) {
	request({
		url: elem.id,
		gzip: true
	}, function (err, res, body) {
		taskCompleted++;
		if (err) {
			console.log('Failed: %s', elem.id);
			callback();
			return;
		}
		var $ = cheerio.load(body);
		var dom = $('title');
    var movieName = filteProductTitle(dom);
    elem.name = movieName
		updateWhenSuccess(elem);
		console.log('%s [%d/%d] %s', (taskCompleted / taskAll).toFixed(2), taskCompleted, taskAll, movieName);
		callback();
	});

}, 40);

function updateWhenSuccess(elem){
  connection.query('UPDATE movies SET ? WHERE ?', [{name: elem.name, state: 1}, {id: elem.id}]);
}

function filteProductTitle(dom){
  var movieName = '';
  if (dom.length > 0) {
    if(dom.text().substr(0,10) == 'Amazon.com'){
      for(var i in dom.text()){
        if(i >= 12){
          if(dom.text()[i] == ':') break;
          movieName += dom.text()[i];
        }
      }
    } else {
      movieName = dom.text();
    }
  }
  return movieName;
}


function removeRepeatMovieName(movieName){
  for(var i in noRepeatMovieNames){
    if(noRepeatMovieNames[i] == movieName) return true;
  }
  noRepeatMovieNames.push(movieName);
  return false
}

connection.query("select * from movies where name=?", ['robot check'], function (error, results, fields) {
  results.forEach(function (elem) {
    // body...
    queue.push(elem);
    taskAll++;
  });
  // error will be an Error if one occurred during the query
  // results will contain the results of the query
  // fields will contain information about the returned results fields (if any)
});