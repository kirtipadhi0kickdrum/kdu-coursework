const operatingSystem = require('os');
const fileSystem = require('fs');
const path = require('path');

function systemInfoFunc()
{
    return{
        hostName: operatingSystem.hostname(),
        operatingSystem: operatingSystem.platform(),
        architecture: operatingSystem.arch(),
        osRelease: operatingSystem.release(),
        uptime: operatingSystem.uptime(),
        numberOfCpuCores: operatingSystem.cpus().length,
        totalMemory: operatingSystem.totalmem(),
        freeMemory: operatingSystem.freemem(),
        currentDirectory: process.cwd()

    };
}


function toJson(data, filename = 'system_info.json')
{
    const filePath = path.join(filename);
    fileSystem.writeFileSync(filePath, JSON.stringify(data, null, 4));
    console.log(`system info added to ${filename}`);
}

const systemInfo = systemInfoFunc();
toJson(systemInfo);


