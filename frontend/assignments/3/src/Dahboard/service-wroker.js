/* eslint-disable @typescript-eslint/no-unused-vars */
self.addEventListener('message', (event) => {
    const result = performComputeTask(event.data)
    self.postMessage(result)
})

function performComputeTask(data)
{
    let result = 0
    for(let i = 0;i< 100000000;i++)
    {
        result += Math.sqrt(i)
    }
    return result
}