import React, { useEffect, useState } from 'react';
import { completeBug, deleteBug, getAllBugs, inCompleteBug } from '../services/Bugservice';
import { getAllUsers } from '../services/Bugservice'; 
import { useNavigate } from 'react-router-dom';
import { isAdminUser } from '../services/AuthService';

const ListBugComponent = () => {
    const [bugs, setBugs] = useState([]);
    const [users, setUsers] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedSeverity, setSelectedSeverity] = useState('');
    const [selectedStatus, setSelectedStatus] = useState('');
    const [selectedUser, setSelectedUser] = useState('');
    const [filteredBugs, setFilteredBugs] = useState([]);

    const navigate = useNavigate();
    const isAdmin = isAdminUser();

    useEffect(() => {
        listBugs();
        fetchUsers();
    }, []);

    function listBugs() {
        getAllBugs().then(response => {
            setBugs(response.data);
            setFilteredBugs(response.data);
        }).catch(error => console.error(error));
    }

    function addNewBug() { navigate('/add-bug'); } 
    function updateBug(id) { navigate(`/update-bug/${id}`); }
    function removeBug(id) { deleteBug(id).then(() => listBugs()).catch(error => console.error(error)); } 
    function markCompleteBug(id) { completeBug(id).then(() => listBugs()).catch(error => console.error(error)); } 
    function markInCompleteBug(id) { inCompleteBug(id).then(() => listBugs()).catch(error => console.error(error)); }

    function fetchUsers() {
        getAllUsers().then(response => {
            setUsers(response.data);
        }).catch(error => console.error("Error fetching users:", error));
    }

    function searchBugs() {
        const filtered = bugs.filter(bug =>
            (searchTerm ? bug.title.toLowerCase().includes(searchTerm.toLowerCase()) : true) &&
            (selectedSeverity ? bug.severity?.toLowerCase() === selectedSeverity.toLowerCase() : true) &&
            (selectedStatus ? (selectedStatus === 'Completed' ? Boolean(bug.completed) === true : Boolean(bug.completed) === false) : true) &&
            (selectedUser ? bug.userId === Number(selectedUser) : true)
        );
        setFilteredBugs(filtered);
    }

    return (
        <div className='container' style={{ maxWidth: "90%", marginLeft: "5%" }}>
            <h2 className='text-center'>Register of Bugs and Features</h2>

            {isAdmin && <button className='btn btn-primary mb-2' onClick={addNewBug}>Add Bug</button>}

            {/* Filters */}
            <div className="d-flex gap-3 mb-3">
                <input 
                    type="text" 
                    className="form-control" 
                    placeholder="Search bugs by title..." 
                    value={searchTerm} 
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
                <select className="form-control" value={selectedSeverity} onChange={(e) => setSelectedSeverity(e.target.value)}>
                    <option value="">All Severities</option>
                    <option value="Low">Low</option>
                    <option value="Medium">Medium</option>
                    <option value="High">High</option>
                    <option value="Critical">Critical</option>
                </select>
                <select className="form-control" value={selectedStatus} onChange={(e) => setSelectedStatus(e.target.value)}>
                    <option value="">All Status</option>
                    <option value="Completed">Completed</option>
                    <option value="Not Completed">Not Completed</option>
                </select>
                <select className="form-control" value={selectedUser} onChange={(e) => setSelectedUser(e.target.value)}>
                    <option value="">All Users</option>
                    {users.map(user => (
                        <option key={user.id} value={user.id}>{user.username}</option>
                    ))}
                </select>
                <button className="btn btn-info" onClick={searchBugs}>Search</button> {/* Search button retained */}
            </div>

            {/* Bug List */}
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>Bug Title</th>
                        <th>Description</th>
                        <th>Completed</th>
                        <th>From Date</th>
                        <th>To Date</th>
                        <th>Severity</th>
                        <th>Assigned User</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredBugs.map(bug => (
                        <tr key={bug.id}>
                            <td>{bug.title}</td>
                            <td>{bug.description}</td>
                            <td>{bug.completed ? 'YES' : 'NO'}</td>
                            <td>{bug.fromDate || 'N/A'}</td>
                            <td>{bug.toDate || 'N/A'}</td>
                            <td>{bug.severity || 'Not Set'}</td>
                            <td>{users.find(user => user.id === bug.userId)?.username || 'Unassigned'}</td>
                            <td>
                                {isAdmin && <button className='btn btn-info btn-sm' onClick={() => updateBug(bug.id)}>Update</button>}
                                {isAdmin && <button className='btn btn-danger btn-sm' onClick={() => removeBug(bug.id)} style={{ marginLeft: "10px" }}>Delete</button>}
                                <button className='btn btn-success btn-sm' onClick={() => markCompleteBug(bug.id)} style={{ marginLeft: "10px" }}>Complete</button>
                                <button className='btn btn-secondary btn-sm' onClick={() => markInCompleteBug(bug.id)} style={{ marginLeft: "10px" }}>In Complete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListBugComponent;
