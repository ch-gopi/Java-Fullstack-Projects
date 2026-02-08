import React, { useEffect, useState } from "react";
import {
  completeBug,
  deleteBug,
  getAllBugs,
  inCompleteBug,
  getBugImages,
} from "../services/Bugservice";
import { getAllUsers, getAllSprints } from "../services/Bugservice";
import { useNavigate } from "react-router-dom";
import { isAdminUser } from "../services/AuthService";
import ExportReport from "../components/ExportReport";

const ListBugComponent = () => {
  const [bugs, setBugs] = useState([]);
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedSeverity, setSelectedSeverity] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("");
  const [selectedUser, setSelectedUser] = useState("");
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [filteredBugs, setFilteredBugs] = useState([]);
  const [currentPage, setCurrentPage] = useState(1); // Number of items per page
  const [sprints, setSprints] = useState([]);
  const [selectedSprint, setSelectedSprint] = useState("");
  const [previewImage, setPreviewImage] = useState(null);


  const navigate = useNavigate();
  const isAdmin = isAdminUser();

  useEffect(() => {
    listBugs();
    fetchUsers();
    fetchSprints();
  }, []);

function listBugs(page = 0, size = 8) {
    getAllBugs(page, size)
        .then(response => {
            console.log("Bug List Data:", response.data);

            const bugsWithImages = response.data.content.map(bug => ({
                ...bug,
                imagePaths: [] 
            }));

            setBugs(bugsWithImages);
            setFilteredBugs(bugsWithImages);
            setCurrentPage(page);

         
   Promise.all(
    bugsWithImages.map(bug =>
        getBugImages(bug.id)
            .then(imageResponse => {
                console.log(`Extracted image paths for Bug ${bug.id}:`, imageResponse); 
                return {
                    ...bug,
                    imagePaths: Array.isArray(imageResponse) ? imageResponse : [] 
                };
            })
            .catch(error => {
                console.error(`Error fetching images for bug ${bug.id}:`, error);
                return { ...bug, imagePaths: [] };
            })
    )
).then(updatedBugs => {
    setBugs(updatedBugs);
    setFilteredBugs(updatedBugs);

    console.log("Updated Bugs with Images:", updatedBugs); 
});

        })
        .catch(error => console.error(error));
}


  function fetchUsers() {
    getAllUsers()
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => console.error("Error fetching users:", error));
  }
  function fetchSprints() {
    getAllSprints()
      .then((response) => {
        console.log("Bug Sprint Data:", response.data);
        setSprints(response.data);
      })
      .catch((error) => console.error("Error fetching sprints:", error));
  }

  function addNewBug() {
    navigate("/add-bug");
  }
  function updateBug(id) {
    navigate(`/update-bug/${id}`);
  }
  function removeBug(id) {
    deleteBug(id)
      .then(() => listBugs())
      .catch((error) => console.error(error));
  }
  function markCompleteBug(id) {
    completeBug(id)
      .then(() => listBugs())
      .catch((error) => console.error(error));
  }
  function markInCompleteBug(id) {
    inCompleteBug(id)
      .then(() => listBugs())
      .catch((error) => console.error(error));
  }

  function searchBugs() {
    const filtered = bugs.filter(
      (bug) =>
        (searchTerm
          ? bug.title.toLowerCase().includes(searchTerm.toLowerCase())
          || bug.id.toString() === searchTerm
          : true) &&
        (selectedSeverity
          ? bug.severity?.toLowerCase() === selectedSeverity.toLowerCase()
          : true) &&
        (selectedStatus
          ? selectedStatus === "Completed"
            ? Boolean(bug.completed) === true
            : Boolean(bug.completed) === false
          : true) &&
        (selectedUser ? bug.userId === Number(selectedUser) : true) &&
        (selectedSprint ? bug.sprintId === Number(selectedSprint) : true) &&
        (fromDate ? new Date(bug.fromDate) >= new Date(fromDate) : true) &&
        (toDate ? new Date(bug.toDate) <= new Date(toDate) : true)
    );
    setFilteredBugs(filtered);
    setCurrentPage(1);
  }
  function clearFilters() {
    setSearchTerm("");
    setSelectedSeverity("");
    setSelectedStatus("");
    setSelectedUser("");
    setSelectedSprint("");
    setFromDate("");
    setToDate("");
    setFilteredBugs(bugs); // Reset to full bug list
    setCurrentPage(1);
  }

  return (
    <div className="container" style={{ maxWidth: "100%", marginLeft: "10px" }}>
      <h2 className="text-center">Register of Bugs and Features</h2>

      {isAdmin && (
        <button className="btn btn-primary mb-2" onClick={addNewBug}>
          Add Bug
        </button>
      )}

      <div className="d-flex gap-3 mb-3">
        <input
          type="text"
          className="form-control"
          placeholder="Ticket No.. or Title..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <select
          className="form-control"
          value={selectedSeverity}
          onChange={(e) => setSelectedSeverity(e.target.value)}
        >
          <option value="">All Severities</option>
          <option value="Low">Low</option>
          <option value="Medium">Medium</option>
          <option value="High">High</option>
          <option value="Critical">Critical</option>
        </select>
        <select
          className="form-control"
          value={selectedStatus}
          onChange={(e) => setSelectedStatus(e.target.value)}
        >
          <option value="">All Status</option>
          <option value="Completed">Completed</option>
          <option value="Not Completed">Not Completed</option>
        </select>
        <select
          className="form-control"
          value={selectedUser}
          onChange={(e) => setSelectedUser(e.target.value)}
        >
          <option value="">All Users</option>
          {users.map((user) => (
            <option key={user.id} value={user.id}>
              {user.username}
            </option>
          ))}
        </select>
        <select
          className="form-control"
          value={selectedSprint}
          onChange={(e) => setSelectedSprint(e.target.value)}
        >
          <option value="">All Sprints</option>
          {sprints.map((sprint) => (
            <option key={sprint.id} value={sprint.id}>
              {sprint.sprintName}
            </option>
          ))}
        </select>
        <label>From Date</label>
        <input
          type="date"
          className="form-control"
          value={fromDate}
          onChange={(e) => setFromDate(e.target.value)}
        />

        <label>To Date</label>
        <input
          type="date"
          className="form-control"
          value={toDate}
          onChange={(e) => setToDate(e.target.value)}
        />

        <button className="btn btn-info" onClick={searchBugs}>
          Search
        </button>

        <button className="btn btn-light " onClick={clearFilters}>
          Clear
        </button>

        <ExportReport
          filters={{
            searchTerm,
            selectedSeverity,
            selectedStatus,
            selectedUser,
            selectedSprint,
            fromDate,
            toDate,
          }}
        />
      </div>

      <table className="table table-bordered table-striped">
        <thead>
          <tr>
             <th style={{ fontSize: "15px", width: "20px" }}>No..</th> 
            <th style={{ fontSize: "15px", width: "100px" }}>Bug Title</th>
            <th style={{ fontSize: "15px", width: "100px" }}>Description</th>
            <th style={{ fontSize: "15px", width: "1px" }}>Completed</th>
            <th style={{ fontSize: "15px", width: "80px" }}>From Date</th>
            <th style={{ fontSize: "15px", width: "80px" }}>To Date</th>
            <th style={{ fontSize: "15px", width: "10px" }}>Severity</th>
            <th style={{ fontSize: "15px", width: "10px" }}>Assigned User</th>
            <th style={{ fontSize: "15px", width: "10px" }}>Sprint</th>
            <th style={{ fontSize: "15px", width: "150px" }}>Images</th>

            <th style={{ fontSize: "15px", width: "210px" }}>Actions</th>
            
          </tr>
        </thead>
        <tbody>
          {filteredBugs.map((bug) => (
            <tr key={bug.id}>
               <td style={{ fontSize: "15px" }}>{bug.id}</td> 
              <td style={{ fontSize: "15px" }}>{bug.title}</td>
              <td style={{ fontSize: "15px" }}>{bug.description}</td>
              <td style={{ fontSize: "15px" }}>
                {bug.completed ? "YES" : "NO"}
              </td>
              <td style={{ fontSize: "15px" }}>
                {bug.fromDate
                  ? new Date(bug.fromDate).toLocaleDateString("en-IN", {
                      day: "2-digit",
                      weekday: "short",
                      month: "short",
                      year: "numeric",
                    })
                  : "N/A"}
              </td>
              <td style={{ fontSize: "15px" }}>
                {bug.toDate
                  ? new Date(bug.toDate).toLocaleDateString("en-IN", {
                      day: "2-digit",
                      weekday: "short",
                      month: "short",
                      year: "numeric",
                    })
                  : "N/A"}
              </td>
              <td style={{ fontSize: "15px" }}>{bug.severity || "Not Set"}</td>
              <td style={{ fontSize: "15px" }}>
                {users.find((user) => user.id === bug.userId)?.username ||
                  "Unassigned"}
              </td>
              <td style={{ fontSize: "15px" }}>
                {sprints.find((sprint) => sprint.id === bug.sprintId)
                  ?.sprintName || "No Sprint Assigned"}
              </td>
<td>
    {bug.imagePaths && bug.imagePaths.length > 0 ? (
        <div className="image-preview-container">
            {bug.imagePaths.map((path, index) => (
                <img 
                    key={index} 
                    src={path.startsWith("http") ? path : `http://localhost:8082/api/images/uploads/images/${path}`} 
                    alt="Bug Image" 
                    className="thumbnail"
                    onClick={() => setPreviewImage(path)} 
                    onError={(e) => {
                        console.error(`Image failed to load: ${path}`);
                        e.target.src = "/default-placeholder.png"; 
                    }}
                />
            ))}
        </div>
    ) : (
        <p>No Images</p>
    )}
</td>



              <td>
                
                  <button
                    className="btn btn-info btn-sm"
                    onClick={() => updateBug(bug.id)}
                    style={{ marginLeft: "10px", fontSize: "11px" }}
                  >
                    Update
                  </button>
                
                {isAdmin && (
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => removeBug(bug.id)}
                    style={{ marginLeft: "10px", fontSize: "11px" }}
                  >
                    Delete
                  </button>
                )}
                <button
                  className="btn btn-success btn-sm"
                  onClick={() => markCompleteBug(bug.id)}
                  style={{ marginLeft: "10px", fontSize: "11px" }}
                >
                  Resolved
                </button>
                <button
                  className="btn btn-secondary btn-sm"
                  onClick={() => markInCompleteBug(bug.id)}
                  style={{ marginLeft: "10px", fontSize: "11px" }}
                >
                  In Progress
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {previewImage && (
    <div className="image-modal" onClick={() => setPreviewImage(null)}>
        <img src={previewImage} alt="Preview" className="preview-image" />
    </div>
)}




      <div className="d-flex justify-content-end">
        <button
          className="btn btn-primary mb-5"
          onClick={() => listBugs(currentPage - 1)}
          disabled={currentPage === 0}
        >
          Previous
        </button>
        <span className="mx-3 position-relative" style={{ top: "5px" }}>
          Page {currentPage + 1}
        </span>
        <button
          className="btn btn-primary mb-5"
          onClick={() => listBugs(currentPage + 1)}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default ListBugComponent;
