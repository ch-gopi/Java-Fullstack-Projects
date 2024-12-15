import React, { useEffect } from 'react'
import { useState } from 'react'
import { getBug, saveBug, updateBug } from '../services/Bugservice'
import { useNavigate, useParams } from 'react-router-dom'

const Bugcomponent = () => {

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [completed, setCompleted] = useState(false)
    const navigate = useNavigate()
    const { id } = useParams()


    function saveOrUpdateBug(e){
        e.preventDefault()

        const bug = {title, description, completed}
        console.log(bug);

        if(id){

            updateBug(id, bug).then((response) => {
                navigate('/bugs')
            }).catch(error => {
                console.error(error);
            })

        }else{
            saveBug(bug).then((response) => {
                console.log(response.data)
                navigate('/bugs')
            }).catch(error => {
                console.error(error);
            })
        }
    }

    function pageTitle(){
        if(id) {
            return <h2 className='text-center'>Update Bug</h2>
        }else {
            return <h2 className='text-center'>Add Bug</h2>
        }
    }

    useEffect( () => {

        if(id){
            getBug(id).then((response) => {
                console.log(response.data)
                setTitle(response.data.title)
                setDescription(response.data.description)
                setCompleted(response.data.completed)
            }).catch(error => {
                console.error(error);
            })
        }

    }, [id])

  return (
    <div className='container'>
        <br /> <br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                { pageTitle() }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Bug Title:</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Bug Title'
                                name='title'
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                            >
                            </input>
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Bug Description:</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Bug Description'
                                name='description'
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            >
                            </input>
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Bug Completed:</label>
                            <select
                                className='form-control'
                                value={completed}
                                onChange={(e) => setCompleted(e.target.value)}
                            >
                                <option value="false">No</option>
                                <option value="true">Yes</option>

                            </select>
                        </div>

                        <button className='btn btn-success' onClick={ (e) => saveOrUpdateBug(e)}>Submit</button>
                    </form>

                </div>
            </div>

        </div>
    </div>
  )
}

export default Bugcomponent