var WebTorrent = require('webtorrent')
var fs = require('fs')

var client = new WebTorrent();
var torrentId = '';

setInterval(
	function(){ 
		var event = JSON.parse(fs.readFileSync('./event.json', 'utf8'))
		
	}, 3000);

createServerTorrent();

function createServerTorrent(){

	readJsonTorrent();

	client.add(torrentId, function (torrent){
		var files = torrent.files;
		const server = torrent.createServer(0)
  		server.listen(0, onListening)
  		function onListening(){
  			const port = server.address().port
    		const url = 'http://localhost:' + port;
    		var f = files[0];
    		var n = 0;
    		for (var i = 0; i <= files; i++) {
    			if(f.length <= files[i].length){
    				f = files[i];
    				n = i;
    			}		
    		}

    		var s = readFile('./returns.json');
			s.stats = 'play';
			s.url = url + '/' + n;
			fs.writeFile('./returns.json', JSON.stringify(s), function (err){
				if (err) {console.log('azedou' + err);}
			});

			console.log('deu boa ' + port);
    	}
	})
}

function readJsonTorrent(){
	var t = readFile('./torrent.json');
	torrentId = t.mTorrent;
}

function status (valor){

}

function readFile(file){
	return JSON.parse(fs.readFileSync(file, 'utf8'))
}