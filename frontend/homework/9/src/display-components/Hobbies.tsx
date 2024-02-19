import React from 'react'
import './Hobbies.css'

interface IHobby{
    id: number,
    hobby: string
}

interface IHobbies{
    hobbies: IHobby[]
}

export function Hobbies({hobbies}: IHobbies) {
  return (
    <div id='hobby-detail'>
        <h3 id='title'>Hobbies</h3>
    <ul>
        {
            hobbies.map((hobby) => {
                return(
                    <li id='list-item'>{hobby.hobby}</li>
                )
            })
        }
    </ul>
    </div>
  )
}
