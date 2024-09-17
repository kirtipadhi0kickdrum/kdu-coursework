let inputString1 = "javascript is cool";
let inputString2 = "programming is fun";
let inputString3 = "become a coder";




function codedString(inputString)
{
    let correctedString= "";
    for(let i =0;i<inputString.length;i++)
    {
        if(inputString[i] == 'a' || inputString[i] == 'A')
        {
            correctedString += '4';    
        }
        else if(inputString[i] == 'e' || inputString[i] == 'E')
        {
            correctedString += '3';
        }
        else if(inputString[i] == 'i' || inputString[i] == 'I')
        {
            correctedString += '1';
        }
        else if(inputString[i] == 'o' || inputString[i] == 'O')
        {
            correctedString += '0';
        }
        else if(inputString[i] == 's' || inputString[i] == 'S')
        {
            correctedString += '5';
        }
        else{
            correctedString += inputString[i];
        }
    }
    return correctedString;
}


let correctedString1 = codedString(inputString1);
let correctedString2 = codedString(inputString2);
let correctedString3 = codedString(inputString3);

console.log(correctedString1);
console.log(correctedString2);
console.log(correctedString3);


