const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
const trimmedDays = Array(7);

function trimDays()
{
    for(var i=0;i<days.length;i++)
    {
        trimmedDays[i] = days[i].slice(0, 3).toUpperCase();
    }
}

trimDays();
console.log(trimmedDays);