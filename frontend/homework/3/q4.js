let inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

const jsonObject = JSON.parse(inputString);


for(let key in jsonObject)
{
    if(key !== "email")
    {
        jsonObject[key] = String(jsonObject[key]).toUpperCase();
    }
}
console.log(jsonObject);


const convertedJson = JSON.stringify(jsonObject);
console.log(convertedJson);

