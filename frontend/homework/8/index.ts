
interface IRecipe
{
    image: string,
    name: string,
    rating: number,
    cuisine: string,
    ingredients: string[],
    difficulty: string,
    timeTaken: number,
    calorieCount: number
}

let recipes:IRecipe [] = [];
let specificRecipes: IRecipe [] = [];


async function fetchRecipesFromAPI(): Promise<void>
{
    const url = 'https://dummyjson.com/recipes';

    try{
        const response = await fetch(url);
        if(!response.ok)
        {
            throw new Error(`Http status is ${response.status}`)
        }

        const data= await response.json();
        const allRecipes = data.recipes;
        for(let eachRecipe of allRecipes)
        {
            
            let thisRecipe:IRecipe = {
                image: eachRecipe.image,
                name: eachRecipe.name,
                rating: eachRecipe.rating,
                cuisine: eachRecipe.cuisine,
                ingredients: eachRecipe.ingredients,
                difficulty: eachRecipe.difficulty,
                timeTaken: eachRecipe.prepTimeMinutes + eachRecipe.cookTimeMinutes,
                calorieCount: eachRecipe.caloriesPerServing
            }
            recipes.push(thisRecipe);
        }
        console.log(recipes);
        
    }
    catch
    {
        console.error('error fetching data');
    }
}


printAllRecipes();
function printAllRecipes(): void
{
    fetchRecipesFromAPI();
}




async function searchRecipes(inputValue: string): Promise<void>
{
    

    var dish: string = inputValue;
    var dishUrl: string = `https://dummyjson.com/recipes/search?q=${dish}`;
    try{
        const response = await fetch(dishUrl);
        if(!response.ok)
        {
            throw new Error(`Http status is ${response.status}`)
        }

        const data= await response.json();
       
        const allRecipes = data.recipes;
        for(let eachRecipe of allRecipes)
        {
        
            let thisRecipe:IRecipe = {
                image: eachRecipe.image,
                name: eachRecipe.name,
                rating: eachRecipe.rating,
                cuisine: eachRecipe.cuisine,
                ingredients: eachRecipe.ingredients,
                difficulty: eachRecipe.difficulty,
                timeTaken: eachRecipe.prepTimeMinutes + eachRecipe.cookTimeMinutes,
                calorieCount: eachRecipe.caloriesPerServing
                
            }
            specificRecipes.push(thisRecipe);  
        }
        
    }
    catch
    {
        console.error('error fetching data!!');
    }
}








