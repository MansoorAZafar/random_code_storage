const compression = require('compression');
const express = require('express')
const cors = require('cors');
const app = express();

const fs = require('fs');
const path = require('path');
const zlib = require('zlib');

const port = process.env.PORT || 8000
app.use(compression());
app.use(cors());

const { hostname } = require('os');
app.get('/', (req, res) => {
	console.log(`Host: ${hostname()}`);
	return res.status(200).end('hi');
})

app.post('/test', express.json(), (req, res) => {
	console.log(req.body);
	return res.status(200).end('hi');
})

// Client will tell server to keep conn alive
app.post('/', (req, res) => {
	return res.status(200).end();
})


app.post('/upload', express.raw({type: ('*/*'), limit: '5mb'}), (req, res) => {
	console.log(req.body);
	console.log('');
	
	// TEMPORARY 
	const rawBuffer = req.body;
	zlib.inflate(rawBuffer, (err, decompressedBuffer) => {
		if(err) {
			console.log(`failed to decompress buffer`);
			return res.status(400).end('failed to compress buffer');
		}
		
		const base64 = decompressedBuffer.toString('base64')
		fs.writeFileSync('example.txt', base64);
		save_file_to_disk(decompressedBuffer);
	});
	
	return res.status(200).end('hi');
})

const save_file_to_disk = (data) => {
	const fileName = 'file.jpg';
	const savePath = path.join(__dirname, fileName)
	
	fs.writeFile(savePath, data, (err) => {
		if(err) {
			console.log(err);
			throw new Error("couldn't save file")
		}
	})
}

app.listen(port, () => {
	console.log(`TCP App listening on port: ${port}`);
})

module.exports = app;
