import React, { useEffect, useState } from 'react';
import { getBug, saveBug, updateBug } from '../services/Bugservice';
import { getAllUsers } from '../services/Bugservice'; 
import { useNavigate, useParams } from 'react-router-dom';

const BugComponent = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [completed, setCompleted] = useState(false);
    const [fromDate, setFromDate] = useState('');
    const [toDate, setToDate] = useState('');
    const [severity, setSeverity] = useState('');
    const [users, setUsers] = useState([]);  
    const [selectedUser, setSelectedUser] = useState('');  

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        
        getAllUsers().then(response => setUsers(response.data)).catch(error => console.error("Error fetching users:", error));

        if (id) {
            getBug(id).then((response) => {
                setTitle(response.data.title);
                setDescription(response.data.description);
                setCompleted(response.data.completed);
                setFromDate(response.data.fromDate);
                setToDate(response.data.toDate);
                setSeverity(response.data.severity);
                setSelectedUser(response.data.userId); // Load user ID if editing
            }).catch(error => console.error(error));
        }
    }, [id]);

    function saveOrUpdateBug(e) {
        e.preventDefault();  
        
        if (!title || !description || !fromDate || !toDate || !severity || !selectedUser) {
            alert("All fields are required. Please fill in missing information.");
            return;
        }

        
        const bug = { title, description, completed, fromDate, toDate, severity: severity.toUpperCase(),  userId: selectedUser };
        
        if (id) {
            updateBug(id, bug)
                .then(() => {
                    console.log("Bug updated successfully!");
                    navigate('/bugs'); 
                })
                .catch(error => {
                    console.error("Error updating bug:", error);
                    alert("Failed to update bug. Please try again.");
                });
        } else {
            saveBug(bug)
                .then(() => {
                    console.log("Bug saved successfully!");
                    navigate('/bugs'); 
                })
                .catch(error => {
                    console.error("Error saving bug:", error);
                    alert("Failed to save bug. Please try again.");
                });
        }
    }
    

    return (
        <div className='container' style={{ marginLeft: "7%", maxWidth: "85%", overflowY: "auto", height: "100vh",marginBottom: "50px"}}>
            <br /><br />
            <div className='row'>
                <div className='card col-md-6 offset-md-3' style={{ marginRight: "-100px" , paddingBottom: "20px"  }}>
                    <h2 className='text-center'>{id ? 'Update Bug' : 'Add Bug'}</h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Bug Title:</label>
                                <input type='text' className='form-control' placeholder='Enter Bug Title' value={title} onChange={(e) => setTitle(e.target.value)} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Bug Description:</label>
                                <input type='text' className='form-control' placeholder='Enter Bug Description' value={description} onChange={(e) => setDescription(e.target.value)} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Bug Completed:</label>
                                <select className='form-control' value={completed} onChange={(e) => setCompleted(e.target.value)}>
                                    <option value="false">No</option>
                                    <option value="true">Yes</option>
                                </select>
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>From Date:</label>
                                <input type='date' className='form-control' value={fromDate} onChange={(e) => setFromDate(e.target.value)} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>To Date:</label>
                                <input type='date' className='form-control' value={toDate} onChange={(e) => setToDate(e.target.value)} />
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Bug Severity:</label>
                                <select className='form-control' value={severity} onChange={(e) => setSeverity(e.target.value)}>
                                    <option value="">Select Severity</option>
                                    <option value="Low">Low</option>
                                    <option value="Medium">Medium</option>
                                    <option value="High">High</option>
                                    <option value="Critical">Critical</option>
                                </select>
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Assign User:</label>
                                <select className='form-control' value={selectedUser} onChange={(e) => setSelectedUser(e.target.value)}>
                                    <option value="">Select User</option>
                                    {users.map(user => (
                                        <option key={user.id} value={user.id}>{user.username}</option>
                                    ))}
                                </select>
                            </div>

                            <button className='btn btn-success' onClick={(e) => saveOrUpdateBug(e)}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default BugComponent;
