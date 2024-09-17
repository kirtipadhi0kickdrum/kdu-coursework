const bills = [140, 45, 280];
var check = Array(3);
var tip = Array(3);

function tipCalculator(){
    for(let i=0;i<bills.length;i++)
    {
        if(bills[i] < 50)
        {
            tip[i] = (0.2 * bills[i]);
            check[i] = (bills[i] + tip[i]);
        }
        else if(bills[i] >= 50 && bills[i] <=200)
        {
            tip[i] = (0.15 * bills[i]);
            check[i] = (bills[i] + tip[i]);
        }
        else{
            tip[i] = (0.1 * bills[i]);
            check[i] = (bills[i] + tip[i]);
        }
    }
}

tipCalculator();

console.log(tip);
console.log(check);