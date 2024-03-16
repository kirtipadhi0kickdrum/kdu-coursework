import React from 'react'
import './Skills.css'


interface ISkill{
    id: number,
    skill: string
}

interface ISkills{
    skills: ISkill[]
}

export function Skills({skills}:ISkills) {
  return (
    <div id='skill-details'>
        <h3 id='title'>Skills</h3>
        <ul>
            {
                skills.map((skill) => {
                    return(
                        <li id='list-item'>{skill.skill}</li>
                    )
                })
            }
        </ul>
    </div>
  )
}
