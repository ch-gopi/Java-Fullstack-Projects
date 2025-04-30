import React, { useEffect, useState } from 'react'
import { completeBug, deleteBug, getAllBugs, inCompleteBug } from '../services/Bugservice'
import { useNavigate } from 'react-router-dom'
import { isAdminUser } from '../services/AuthService'

const Listbugcomponent = () => {
    const [bugs, setBugs] = useState([])
    const navigate = useNavigate()
    const isAdmin = isAdminUser();
    useEffect(() => {
        listBugs();
    }, [])

    function listBugs(){
        getAllBugs().then((response) => {
            setBugs(response.data );
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewBug(){
        navigate('/add-bug')

    }
    
    function updateBug(id){
        console.log(id)
        navigate(`/update-bug/${id}`)
    }
    
    function removeBug(id){
        deleteBug(id).then((response) => {
            listBugs();
        }).catch(error => {
            console.error(error)
        })
    }

    function markCompleteBug(id){
        completeBug(id).then((response) => {
            listBugs()
        }).catch(error => {
            console.error(error)
        })
    }

    function markInCompleteBug(id){
        inCompleteBug(id).then((response) => {
            listBugs();
        }).catch(error => {
            console.error(error)
        })
    }
    
    return (
        <div className='container'>
            <h2 className='text-center'>Register of Software Bugs and Feature Requests</h2>
            { isAdmin &&
                <button className='btn btn-primary mb-2' onClick={addNewBug}>Add Bug</button>
            }
            
            <div>
                <table className='table table-bordered table-striped'>
                    <thead>
                        <tr>
                            <th>Bug Title</th>
                            <th>Bug Description</th>
                            <th>Bug Completed</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            bugs.map(bug => 
                                <tr key={bug.id}>
                                    <td>{bug.title}</td>
                                    <td>{bug.description}</td>
                                    <td>{bug.completed ? 'YES': 'NO'}</td>
                                    <td>
                                    {
                                        isAdmin &&
                                        <button className='btn btn-info' onClick={() => updateBug(bug.id)}>Update</button>
                                      } 
                                      {
                                        isAdmin && <button className='btn btn-danger' onClick={() => removeBug(bug.id)} style={ { marginLeft: "10px" }} >Delete</button>
                                       } {
                                        isAdmin && <button className='btn btn-success' onClick={() => markCompleteBug(bug.id)} style={ { marginLeft: "10px" }} >Complete</button>
                                         } {
                                        isAdmin &&  <button className='btn btn-info' onClick={() => markInCompleteBug(bug.id)} style={ { marginLeft: "10px" }} >In Complete</button>
                                           }  </td>
                                </tr>
                            )
                        }
    
                    </tbody>
                </table>
            </div>
    
        </div>
      )
}

export default Listbugcomponent
