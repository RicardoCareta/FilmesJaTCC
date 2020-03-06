var http = require('http'),
    fs = require('fs'),
    qs = require('querystring'),
    WebTorrent = require('webtorrent'),
    url = require('url');


var magnetURI = 'magnet:?xt=urn:btih:08ada5a7a6183aae1e09d831df6748d566095a10&dn=Sintel&tr=udp%3A%2F%2Fexplodie.org%3A6969&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969&tr=udp%3A%2F%2Ftracker.empire-js.us%3A1337&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=wss%3A%2F%2Ftracker.btorrent.xyz&tr=wss%3A%2F%2Ftracker.fastcast.nz&tr=wss%3A%2F%2Ftracker.openwebtorrent.com&ws=https%3A%2F%2Fwebtorrent.io%2Ftorrents%2F&xs=https%3A%2F%2Fwebtorrent.io%2Ftorrents%2Fsintel.torrent';

function createServerTorrent(resp){
	//console.log(magnetURI);
	if(magnetURI == 'undefined'){
		console.log('sas')
		return;
	}

	var client = new WebTorrent();

	client.add(magnetURI, function(torrent){
		var server = torrent.createServer();
		
		server.listen(0, onListen);
		//resp.write("retorno" );
		//console.log(server.address().address.toString() + ":" + server.address().port.toString());
		function onListen(){
			var files = torrent.files;
			//console.log(server.address().port);
			
			var f = files[0];
    		var n = 0
    		var nF = 0;

    		var file = torrent.files[0];
  			torrent.files.forEach(function(outro){
    			if(file.length <= outro.length){
    				console.log(file.name);

    				file = outro;
    				nF = n;
  				}
  				n = n + 1;
  			});

			resp.write("http://localhost:" + server.address().port + "/" + nF);
			console.log('open');

			resp.end();
		}

		
	});
}

fs.readFile('./index.html', function (err, html) {
    if (err) {
        throw err; 
    }       
    http.createServer(function(request, response) {  
		var body = '';
		var idTorrent = '';

		if (request.method == 'POST') {

    		request.on('data', function(data){
    			body += data;
    		});

    		request.on('end', function(){
    			var post = qs.parse(body);
    			magnetURI = post['id'];
				//console.log(magnetURI);

				if(magnetURI != null){
					createServerTorrent(response);
				}
    		});
    	}

    		/*var client = new WebTorrent();
    		createServerTorrent(client, idTorrent);
    	}


		var url_parts = url.parse(request.url, true);
		var query = url_parts.query;


		if(query.id != null){
		magnetURI = query.id;
		
		magnetURI = magnetURI.replace('(MUDAROE)', '&');
		magnetURI = magnetURI.replace('(MUDARELA)', '?');

		createServerTorrent();
		}else{
			console.log('nulo');
		}
		


/*
    	if (request.method == 'POST') {

    		request.on('data', function(data){
    			body += data;
    		});

    		request.on('end', function(){
    			var post = qs.parse(body);
    			idTorrent = post['id'];
				//console.log(post['id']);
    		});

    		/*var client = new WebTorrent();
    		createServerTorrent(client, idTorrent);
    	}

*/

    	/*
        response.writeHeader(200, {"Content-Type": "text/html"});  
        response.write("<input type='hidden' value='"+idTorrent+"' id='hdnValor'>");
        response.write(html);

       */
    }).listen(80);
});
