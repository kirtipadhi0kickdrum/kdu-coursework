const http = require('http');
const fileSystem = require('fs');
const path = require('path');
const name = 'kirti';

const server = http.createServer((req, res) => {
    if(req.url=== '/')
    {
        const filePath = path.join('system_info.json');
        const systemInfo = JSON.parse(fileSystem.readFileSync(filePath, 'utf-8'));
        const response = `Hello, my name is ${name}!\n\nHere is my system info:\n\n${JSON.stringify(systemInfo, null, 4)}`;
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.end(response);
    }
    else
    {
        res.writeHead(404, {'Content-Type': 'text/plain'});
        res.end('Not Found');
    }
});

const port = 8000;
server.listen(port, () => {
    console.log(`Server running in port ${port}`);
})