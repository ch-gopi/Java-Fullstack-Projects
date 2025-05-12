import React, { useEffect, useState, useContext } from 'react';
import { getBug, saveBug, updateBug } from '../services/Bugservice';
import { getAllUsers, getAllSprints } from '../services/Bugservice'; 
import { useNavigate, useParams } from 'react-router-dom';
import { NotificationContext } from '../components/NotificationContext'; // Import NotificationContext

const BugComponent = () => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [completed, setCompleted] = useState(false);
    const [fromDate, setFromDate] = useState('');
    const [toDate, setToDate] = useState('');
    const [severity, setSeverity] = useState('');
    const [users, setUsers] = useState([]);  
    const [selectedUser, setSelectedUser] = useState('');
    const [sprints, setSprints] = useState([]);
    const [selectedSprint, setSelectedSprint] = useState('');
    const [userEmail, setUserEmail] = useState('');

    const navigate = useNavigate();
    const { id } = useParams();
    const { addNotification } = useContext(NotificationContext); // Use context

    useEffect(() => {
        getAllUsers()
            .then(response => setUsers(response.data))
            .catch(error => console.error("Error fetching users:", error));

        getAllSprints()
            .then(response => setSprints(response.data))
            .catch(error => console.error("Error fetching sprints:", error));

        if (id) {
            getBug(id).then((response) => {
                setTitle(response.data.title || '');
                setDescription(response.data.description || '');
                setCompleted(response.data.completed || false);
                setFromDate(response.data.fromDate || '');
                setToDate(response.data.toDate || '');
                setSeverity(response.data.severity || '');
                setSelectedUser(response.data.userId || '');
                setSelectedSprint(response.data.sprintId || '');
                setUserEmail(response.data.userEmail || '');
            }).catch(error => console.error("Error fetching bug details:", error));
        }
    }, [id]);

    function saveOrUpdateBug(e) {
        e.preventDefault();  

        if (!title || !description || !fromDate || !toDate || !severity || !selectedUser || !selectedSprint) {
            alert("All fields are required.");
            return;
        }

        const selectedUserObject = users.find(user => user.id === selectedUser);
        const emailToSend = selectedUserObject?.email || userEmail;

        const bug = { title, description, completed, fromDate, toDate, severity: severity.toUpperCase(), userId: selectedUser, sprintId: selectedSprint, userEmail: emailToSend };

     if (id) {
    updateBug(id, bug)
        .then(() => {
            addNotification(`Bug updated successfully! Email sent to ${selectedUserObject.email}`, selectedUserObject.username); // Send notification
            navigate('/bugs'); 
        })
        .catch(error => {
            console.error("Error updating bug:", error);
            addNotification(`Failed to update bug. Could not send email to ${selectedUserObject.email}`, selectedUserObject.username);
        });
} else {
    saveBug(bug)
        .then(() => {
            addNotification(`New bug assigned to ${selectedUserObject.username}. Email notification sent to ${selectedUserObject.email}`, selectedUserObject.username);
            navigate('/bugs'); 
        })
        .catch(error => {
            console.error("Error saving bug:", error);
            addNotification(`Failed to add bug. No email sent to ${selectedUserObject.email}`, selectedUserObject.username);
        });
}

    }

    return (
        <div className='container' style={{ marginLeft: "7%", maxWidth: "85%", overflowY: "auto", height: "100vh", marginBottom: "50px"}}>
            <div className='row'>
                <div className='card col-md-6 offset-md-3' style={{ paddingBottom: "20px" }}>
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
                                <select className='form-control' value={completed} onChange={(e) => setCompleted(e.target.value === "true")}>
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
                                <select className='form-control' value={selectedUser} onChange={(e) => setSelectedUser(Number(e.target.value))}>
                                    <option value="">Select User</option>
                                    {users.map(user => (
                                        <option key={user.id} value={user.id}>{user.username}</option>
                                    ))}
                                </select>
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Assign Sprint:</label>
                                <select className='form-control' value={selectedSprint} onChange={(e) => setSelectedSprint(Number(e.target.value))}>
                                    <option value="">Select Sprint</option>
                                    {sprints.map(sprint => (
                                        <option key={sprint.id} value={sprint.id}>{sprint.sprintName}</option>
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
