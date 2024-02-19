import React from 'react'
import './Header.css'

interface IHeader{
    name: string,
    fullName: string, 
    qualification: string
}

export function Header({name, fullName, qualification}: IHeader) {
  return (
    <div id='person-detail'>
        <h2>{name}</h2>
        <h4>{fullName}</h4>
        <h2>{qualification}</h2>
    </div>
  )
}
