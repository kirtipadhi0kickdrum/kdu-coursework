import React from 'react'
import {Header} from './Header'
import { Skills } from './Skills'
import { Hobbies } from './Hobbies'
import './ProfileDisplay.css'

export function ProfileDisplay() {
    const data = [
        {
            "name": "Amey",
            "fullName": "Amey Aditya",
            "qualification": "SSE",
            "skills": [
                {
                    "id": 1,
                    "skill": "Python"
                },
                {
                    "id": 2,
                    "skill": "React"
                }
            ],
            "hobbies": [
                {
                    "id": 1,
                    "hobby": "Cricket"
                }
            ]
        }
    ]
  return (
    <div id='main-container'>
        {data.map((data, index) => (
            <div key={index}>
                <div id='header-container'>
                    <Header name={data.name} fullName={data.fullName} qualification={data.qualification} />
                </div>
                
                <div id='list-details'>
                    <Skills skills={data.skills} />
                    <Hobbies hobbies={data.hobbies} />
                </div>
                
            </div>
        ))}
    </div>
  )
}
