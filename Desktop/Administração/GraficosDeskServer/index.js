var http = require('http');
var qs = require('querystring');

http.createServer(function(req, res){
	if (req.method == 'POST') {
		var body = '';
		req.on('data', function(data){
			body += data;	
		});
		req.on('end', function(){
			var POST = qs.parse(body);
			//console.log(POST);
			var jsons = JSON.parse(POST);
		console.log(jsons);
		});

		res.end('teste');

	}
}).listen(8080);