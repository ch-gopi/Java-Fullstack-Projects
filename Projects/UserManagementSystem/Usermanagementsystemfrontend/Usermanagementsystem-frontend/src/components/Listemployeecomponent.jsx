import React, { useEffect, useState } from 'react'

import { useNavigate } from "react-router-dom";
import { deleteUser, listUsers } from '../services/Employeeservice';

const Listemployeecomponent = () => {
    const[employees,setEmployees]=useState([])
    const navigator= useNavigate();
    useEffect(()=>{
      getAllUsers();
   },[])
  function getAllUsers(){
    listUsers().then((response)=>{
      setEmployees(response.data);
    }).catch(error=>{console.error(error);})
  }
    function addNewUser(){
       navigator('/Add-User')
    }
    function updateUser(id){
      navigator(`/Edit-User/${id}`)
   }
    function removeUser(id){
      console.log(id);
      deleteUser(id).then((response)=>{console.log(response.data);
        getAllUsers();  
        }).catch(error=>{console.error(error);
        })
    }
 
  
  return (
    <div className="container">
      <h2 className="text-center">List Of Users</h2>
      <button  className="btn btn-primary mb-2" onClick={addNewUser}>Add User</button>
     <table className="table table-striped table-bordered">
     <thead>
     <tr>
     <th>User Id</th>
     <th>User First Name</th>
     <th>User Last Name</th>
     <th>User Email Id</th> 
     <th>Actions</th> 
     </tr>
     </thead>
     
      <tbody>{  employees.map(employee => <tr key= {employee.id}>
        <td>{employee.id}</td>
        <td>{employee.firstName}</td>
        <td>{employee.lastName}</td>
        <td>{employee.email}</td>
        <td> <button  className="btn btn-info" onClick={()=>updateUser(employee.id)}>Update</button>
        <button  className="btn btn-danger" onClick={()=>removeUser(employee.id)}>Delete</button>
        </td>
        </tr>)
        } 
      </tbody>
      </table>
    </div>
  )
}

export default Listemployeecomponent
