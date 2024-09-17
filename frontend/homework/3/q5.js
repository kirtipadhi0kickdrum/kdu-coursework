let player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
    country: "Spain",
    city: "Barcelona",
    },
    careerInfo: {
    fcBarcelona: {
    appearances: 780,
    goals: {
    premierLeagueGoals: 590,
    championsLeagueGoals: 50,
    },
    },
    },
    };
    

function displayKeys(obj)
{
    for(let key in obj)
    {
        if(typeof obj[key] == 'object')
        {
            displayKeys(obj[key]);
        }
        else{
            console.log(key);
        }
    }
}

function displayValues(obj)
{
    for(let key in obj)
    {
        if(typeof obj[key] == 'object')
        {
            displayValues(obj[key])
        }
        else{
            console.log(obj[key]);
        }
    }
}

displayKeys(player);
console.log(" -------------------------------------------- ")
displayValues(player);