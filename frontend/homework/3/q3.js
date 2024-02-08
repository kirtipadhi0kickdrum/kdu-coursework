const shoe1 = {
    type: "sneakers",
    color: "white",
    size: 9,
    price: 5000
}

const shoe2 = {
    type: "sports",
    color: "blue",
    size: 10,
    price: 6000
}

const shirt1 = {
    type: "full shirt",
    color: "yellow",
    size: "XL",
    price: 1000
}

const shirt2 = {
    type: "full shirt",
    color: "blue",
    size: "XL",
    price: 1500
}


const shirt3 = {
    type: "half shirt",
    color: "black",
    size: "L",
    price: 3000
}


let shoes = [shoe1, shoe2];
let shirts = [shirt1, shirt2, shirt3];

let warehouse = Array();

for(let i =0;i<shoes.length; i++)
{
    warehouse.push(shoes[i]);
}
for(let i=0;i<shirts.length;i++)
{
    warehouse.push(shirts[i]);
}

console.log("warehous before sorting ");
console.log(warehouse);

let totalPrice = 0;
for(let i =0;i<warehouse.length;i++)
{
    totalPrice += warehouse[i].price;
}

console.log(totalPrice);
warehouse.sort((a,b) => b.price - a.price);
console.log("warehouse after sorting: ")
console.log(warehouse);


for(let i=0;i<warehouse.length;i++)
{
    if(warehouse[i].color == "blue")
    {
        console.log(warehouse[i]);
    }
}
